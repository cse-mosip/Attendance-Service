package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.lms.CourseRepository;
import uom.mosip.attendanceservice.dao.lms.StudentRepository;
import uom.mosip.attendanceservice.dto.lms.CourseDTO;
import uom.mosip.attendanceservice.models.lms.Course;
import uom.mosip.attendanceservice.models.lms.Student;

import java.util.List;
import java.util.Optional;

@Service
public class LMSService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<String> getStudentsForACourse(long courseId) {
        return studentRepository.fetchCourseByModuleCodeWithStudents(courseId)
                .stream().map(Student::getStudentId)
                .toList();
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.fetchAllCourses()
                .stream().map(CourseDTO::new)
                .toList();
    }

    public List<CourseDTO> getCoursesByIntake(long intake) {
        return courseRepository.fetchCoursesByIntake(intake)
                .stream().map(CourseDTO::new)
                .toList();
    }

    public CourseDTO getCourseByID(String courseId) {
        Optional<Course> c = courseRepository.findById(courseId);
        return c.map(CourseDTO::new).orElse(null);
    }

}
