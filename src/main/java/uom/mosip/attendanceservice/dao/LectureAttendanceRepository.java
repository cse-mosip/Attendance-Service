package uom.mosip.attendanceservice.dao;

import org.springframework.data.repository.CrudRepository;
import uom.mosip.attendanceservice.models.Lecture;
import org.springframework.stereotype.Repository;
import uom.mosip.attendanceservice.models.LectureAttendance;

import java.util.Optional;

import java.util.List;

@Repository
public interface LectureAttendanceRepository extends CrudRepository<LectureAttendance,Long> {
    Optional<LectureAttendance> findByStudentIdAndLecture(String studentId, Lecture lecture);
    List<LectureAttendance> getLectureAttendanceByStudentId(String studentId);
    List<LectureAttendance> getLectureAttendanceByLectureId(long studentId);
}
