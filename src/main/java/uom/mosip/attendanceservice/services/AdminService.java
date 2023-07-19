package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.LectureAttendanceRepository;
import uom.mosip.attendanceservice.dao.LectureRepository;
import uom.mosip.attendanceservice.dao.lms.CourseRepository;
import uom.mosip.attendanceservice.models.Lecture;
import uom.mosip.attendanceservice.models.LectureAttendance;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final LectureAttendanceRepository lectureAttendanceRepository;

    @Autowired
    public AdminService(LectureAttendanceRepository lectureAttendanceRepository) {
        this.lectureAttendanceRepository = lectureAttendanceRepository;
    }

    public List<LectureAttendance> getStudentAttendance(String studentId) {
        return lectureAttendanceRepository.getLectureAttendanceByStudentId(studentId);
    }
}
