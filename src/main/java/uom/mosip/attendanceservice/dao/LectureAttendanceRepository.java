package uom.mosip.attendanceservice.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uom.mosip.attendanceservice.models.LectureAttendance;

import java.util.List;

@Repository
public interface LectureAttendanceRepository extends CrudRepository<LectureAttendance,Long> {
    List<LectureAttendance> getLectureAttendanceByStudentId(String studentId);
}
