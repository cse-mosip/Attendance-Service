package uom.mosip.attendanceservice.dao.lms;

import org.springframework.data.repository.CrudRepository;
import uom.mosip.attendanceservice.models.lms.Course;

public interface CourseRepository extends CrudRepository<Course,String> {
}
