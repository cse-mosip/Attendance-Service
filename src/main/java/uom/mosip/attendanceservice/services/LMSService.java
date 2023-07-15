package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.lms.StudentRepository;
import uom.mosip.attendanceservice.models.lms.Student;

import java.util.List;

@Service
public class LMSService {

    @Autowired
    private StudentRepository studentRepository;

    public List<String> getStudentsForACourse(String moduleCode, int intake) {
        return studentRepository.fetchCourseByModuleCodeWithStudents(moduleCode, intake)
                .stream().map(Student::getStudentId)
                .toList();
    }
}