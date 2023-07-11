package uom.mosip.attendanceservice.dao;

import org.springframework.data.repository.CrudRepository;
import uom.mosip.attendanceservice.models.Lecture;

public interface LectureRepository extends CrudRepository<Lecture,Long> {
}
