package uom.mosip.attendanceservice.dao;

import org.springframework.data.repository.CrudRepository;
import uom.mosip.attendanceservice.models.Exam;

public interface ExamRepository extends CrudRepository<Exam,Long> {
}
