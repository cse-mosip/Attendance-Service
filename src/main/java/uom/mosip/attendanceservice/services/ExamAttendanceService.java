package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.ExamAttendanceRepository;
import uom.mosip.attendanceservice.dao.ExamRepository;
import uom.mosip.attendanceservice.dto.MarkAttendanceRequestDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.dto.StudentDTO;
import uom.mosip.attendanceservice.models.Exam;
import uom.mosip.attendanceservice.models.ExamAttendance;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExamAttendanceService {
    private final ExamRepository examRepository;
    private final ExamAttendanceRepository examAttendanceRepository;
    private final LMSService lmsService;
    private final AuthenticationService authenticationService;
    private final RegistrationService registrationService;

    @Autowired
    public ExamAttendanceService(ExamRepository examRepository, ExamAttendanceRepository examAttendanceRepository, LMSService lmsService, AuthenticationService authenticationService, RegistrationService registrationService) {
        this.examRepository = examRepository;
        this.examAttendanceRepository = examAttendanceRepository;
        this.lmsService = lmsService;
        this.authenticationService = authenticationService;
        this.registrationService = registrationService;
    }

    public ResponseDTO markExamAttendance(MarkAttendanceRequestDTO markAttendanceRequestDTO) {
        Object fingerprint = markAttendanceRequestDTO.getFingerprint();
        Long exam_id = markAttendanceRequestDTO.getEventId();

        if (fingerprint == null || exam_id == null) {
            return new ResponseDTO("INVALID_DATA", "Fingerprint or Exam id is not set");
        }

        String student_id = authenticationService.authenticate(fingerprint);

        if (student_id != null) {
            Optional<Exam> exam = examRepository.findById(exam_id);

            if (exam.isPresent()) {
                Exam validExam = exam.get();

                if (validExam.getStartTime().minusMinutes(30).isAfter(LocalDateTime.now())) {
                    return new ResponseDTO("INVALID_DATA", "Exam attendance marking is not yet started");
                } else if (validExam.getEndTime().plusMinutes(30).isBefore(LocalDateTime.now())) {
                    return new ResponseDTO("INVALID_DATA", "Exam attendance marking is finished");
                } else {
                    List<String> studentList = lmsService.getStudentsForACourse(validExam.getCourseId());

                    boolean canAttend = studentList.contains(student_id);

                    if (canAttend) {
                        List<ExamAttendance> studentExamAttendance = examAttendanceRepository.getExamAttendanceByStudentIdAndExam(student_id, validExam);

                        StudentDTO student = registrationService.getStudentDetails(student_id);

                        if (studentExamAttendance.isEmpty()) {
                            ExamAttendance newExamAttendance = new ExamAttendance();
                            newExamAttendance.setStudentId(student_id);
                            newExamAttendance.setExam(validExam);
                            newExamAttendance.setMarkedTime(LocalDateTime.now());
                            newExamAttendance.setValidated(false);
                            examAttendanceRepository.save(newExamAttendance);

                            validExam.setAttendance(validExam.getAttendance() + 1);
                            examRepository.save(validExam);

                            return new ResponseDTO("OK", "Attendance marked successfully", student);

                        } else if (studentExamAttendance.size() == 1 && !studentExamAttendance.get(0).isValidated()) {
                            ExamAttendance notValidatedExamAttendance = studentExamAttendance.get(0);
                            notValidatedExamAttendance.setValidated(true);
                            notValidatedExamAttendance.setValidatedTime(LocalDateTime.now());
                            examAttendanceRepository.save(notValidatedExamAttendance);

                            return new ResponseDTO("OK", "Attendance verified successfully", student);

                        } else if (studentExamAttendance.size() == 1 && studentExamAttendance.get(0).isValidated()) {
                            return new ResponseDTO("INVALID_DATA", "Attendance already marked and validated");
                        } else {
                            return new ResponseDTO("INVALID_DATA", "Server Error");
                        }
                    } else {
                        return new ResponseDTO("INVALID_DATA", "Student does not have access to this exam");
                    }
                }
            } else {
                return new ResponseDTO("INVALID_DATA", "Invalid exam");
            }
        } else {
            return new ResponseDTO("INVALID_DATA", "No Match");
        }

    }

}
