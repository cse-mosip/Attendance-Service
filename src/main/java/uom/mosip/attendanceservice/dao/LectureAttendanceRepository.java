package uom.mosip.attendanceservice.dao;

import org.springframework.data.repository.CrudRepository;
import uom.mosip.attendanceservice.models.Lecture;
import uom.mosip.attendanceservice.models.LectureAttendance;

import java.util.Optional;

public interface LectureAttendanceRepository extends CrudRepository<LectureAttendance,Long> {
    Optional<LectureAttendance> findByStudentIdAndLecture(String studentId, Lecture lecture);
}
