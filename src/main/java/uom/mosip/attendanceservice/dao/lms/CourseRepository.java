package uom.mosip.attendanceservice.dao.lms;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uom.mosip.attendanceservice.models.lms.Course;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String> {

    @Query("SELECT c FROM Course c")
    List<Course> fetchAllCourses();
}
