package uom.mosip.attendanceservice.dao;

import org.springframework.data.repository.CrudRepository;
import uom.mosip.attendanceservice.models.ExamAttendance;

public interface ExamAttendanceRepository extends CrudRepository<ExamAttendance,Long> {
}
