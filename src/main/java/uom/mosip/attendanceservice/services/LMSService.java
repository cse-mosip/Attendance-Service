package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.lms.CourseRepository;
import uom.mosip.attendanceservice.dao.lms.StudentRepository;
import uom.mosip.attendanceservice.dto.lms.CourseDTO;
import uom.mosip.attendanceservice.models.lms.Student;

import java.util.List;

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

}
