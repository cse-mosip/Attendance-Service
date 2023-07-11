package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.LectureAttendanceRepository;
import uom.mosip.attendanceservice.dao.LectureRepository;
import uom.mosip.attendanceservice.dto.MarkAttendanceRequestDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.models.Lecture;
import uom.mosip.attendanceservice.models.LectureAttendance;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LectureAttendanceService {
    private final LectureRepository lectureRepository;
    private final LectureAttendanceRepository lectureAttendanceRepository;

    @Autowired
    public LectureAttendanceService(LectureRepository lectureRepository, LectureAttendanceRepository lectureAttendanceRepository) {
        this.lectureRepository = lectureRepository;
        this.lectureAttendanceRepository = lectureAttendanceRepository;
    }

    public ResponseDTO markLectureAttendance(MarkAttendanceRequestDTO markAttendanceRequestDTO) {
        Object fingerprint = markAttendanceRequestDTO.getFingerprint();
        Long lecture_id = markAttendanceRequestDTO.getLecture_id();

        if (fingerprint == null || lecture_id == null) {
            return new ResponseDTO("400", "Invalid data", "Invalid data");
        }

        // TODO  - call authentication service and get the student id
        String student_id = "S-123";

        if (student_id != null) {
            Optional<Lecture> lecture = lectureRepository.findById(lecture_id);

            if (lecture.isPresent()) {
                Lecture validLecture = lecture.get();

                if (!validLecture.isStarted()) {
                    return new ResponseDTO("400", "Invalid data", "Lecture is not yet started");
                } else if (validLecture.isEnded()) {
                    return new ResponseDTO("400", "Invalid data", "Lecture has already ended");
                } else {
                    // TODO - validate if the student can attend the lecture
                    boolean canAttend = true;

                    if (canAttend) {
                        Optional<LectureAttendance> studentLectureAttendance = lectureAttendanceRepository.findByStudentIdAndLecture(student_id, validLecture);

                        if (studentLectureAttendance.isPresent()) {
                            return new ResponseDTO("400", "Invalid data", "Attendance already marked");
                        }

                        LectureAttendance lectureAttendance = new LectureAttendance();
                        lectureAttendance.setStudentId(student_id);
                        lectureAttendance.setLecture(validLecture);
                        lectureAttendance.setArrivalTime(LocalDateTime.now());
                        lectureAttendanceRepository.save(lectureAttendance);

                        validLecture.setAttendance(validLecture.getAttendance() + 1);
                        lectureRepository.save(validLecture);

                        return new ResponseDTO("200", "Attendance marked successfully", student_id);
                    } else {
                        return new ResponseDTO("400", "Invalid data", "Student does not have access to this lecture");
                    }
                }
            } else {
                return new ResponseDTO("400", "Invalid data", "Invalid lecture");
            }
        } else {
            return new ResponseDTO("400", "Invalid data", "No Match");
        }

    }
}
