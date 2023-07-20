package uom.mosip.attendanceservice.dao.lms;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uom.mosip.attendanceservice.models.lms.Student;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, String> {

    @Query("SELECT s FROM Student s INNER JOIN s.enrolledCourses e WHERE e.id=?1")
    List<Student> fetchCourseByModuleCodeWithStudents(long courseId);
}
