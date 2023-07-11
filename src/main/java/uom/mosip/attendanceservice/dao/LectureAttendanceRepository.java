package uom.mosip.attendanceservice.dao;

import org.springframework.data.repository.CrudRepository;
import uom.mosip.attendanceservice.models.LectureAttendance;

public interface LectureAttendanceRepository extends CrudRepository<LectureAttendance,Long> {
}
