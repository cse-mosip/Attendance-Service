package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.LectureAttendanceRepository;
import uom.mosip.attendanceservice.dao.LectureRepository;
import uom.mosip.attendanceservice.dto.MarkAttendanceRequestDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.dto.StudentDTO;
import uom.mosip.attendanceservice.models.Lecture;
import uom.mosip.attendanceservice.models.LectureAttendance;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LectureAttendanceService {
    private final LectureRepository lectureRepository;
    private final LectureAttendanceRepository lectureAttendanceRepository;
    private final LMSService lmsService;

    @Autowired
    public LectureAttendanceService(LectureRepository lectureRepository, LectureAttendanceRepository lectureAttendanceRepository, LMSService lmsService) {
        this.lectureRepository = lectureRepository;
        this.lectureAttendanceRepository = lectureAttendanceRepository;
        this.lmsService = lmsService;
    }

    public ResponseDTO markLectureAttendance(MarkAttendanceRequestDTO markAttendanceRequestDTO) {
        Object fingerprint = markAttendanceRequestDTO.getFingerprint();
        Long lecture_id = markAttendanceRequestDTO.getEventId();

        if (fingerprint == null || lecture_id == null) {
            return new ResponseDTO("INVALID_DATA", "Fingerprint or Lecture id is not set");
        }

        // TODO  - call authentication service and get the student id
        String student_id = "S-123";

        if (student_id != null) {
            Optional<Lecture> lecture = lectureRepository.findById(lecture_id);

            if (lecture.isPresent()) {
                Lecture validLecture = lecture.get();

                if (!validLecture.isStarted()) {
                    return new ResponseDTO("INVALID_DATA", "Lecture is not yet started");
                } else if (validLecture.isEnded()) {
                    return new ResponseDTO("INVALID_DATA", "Lecture has already ended");
                } else {
                    List<String> studentList = lmsService.getStudentsForACourse(validLecture.getModuleCode(), validLecture.getIntake());

                    boolean canAttend = studentList.contains(student_id);

                    if (canAttend) {
                        List<LectureAttendance> studentLectureAttendance = lectureAttendanceRepository.getLectureAttendanceByStudentIdAndLecture(student_id, validLecture);

                        if (!studentLectureAttendance.isEmpty()) {
                            return new ResponseDTO("INVALID_DATA", "Attendance already marked");
                        }

                        LectureAttendance lectureAttendance = new LectureAttendance();
                        lectureAttendance.setStudentId(student_id);
                        lectureAttendance.setLecture(validLecture);
                        lectureAttendance.setArrivalTime(LocalDateTime.now());
                        lectureAttendanceRepository.save(lectureAttendance);

                        validLecture.setAttendance(validLecture.getAttendance() + 1);
                        lectureRepository.save(validLecture);

                        // TODO - get full details of the student from registration service
                        StudentDTO student = new StudentDTO();

                        return new ResponseDTO("OK", "Attendance marked successfully", student);
                    } else {
                        return new ResponseDTO("INVALID_DATA", "Student does not have access to this lecture");
                    }
                }
            } else {
                return new ResponseDTO("INVALID_DATA", "Invalid lecture");
            }
        } else {
            return new ResponseDTO("INVALID_DATA", "No Match");
        }

    }
}
