package uom.mosip.attendanceservice.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import uom.mosip.attendanceservice.dao.*;
import uom.mosip.attendanceservice.dao.lms.CourseRepository;
import uom.mosip.attendanceservice.dao.lms.StudentRepository;
import uom.mosip.attendanceservice.dto.lms.CourseDTO;
import uom.mosip.attendanceservice.models.User;
import uom.mosip.attendanceservice.models.lms.Course;
import uom.mosip.attendanceservice.models.lms.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DBSeedHelper {

    private UserRepository userRepository;
    private HallRepository hallRepository;
    private LectureRepository lectureRepository;
    private ExamRepository examRepository;
    private LectureAttendanceRepository lectureAttendanceRepository;
    private ExamAttendanceRepository examAttendanceRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;


    @Autowired
    public DBSeedHelper(UserRepository userRepository, HallRepository hallRepository,
                        LectureRepository lectureRepository, ExamRepository examRepository,
                        LectureAttendanceRepository lectureAttendanceRepository,
                        ExamAttendanceRepository examAttendanceRepository,
                        StudentRepository studentRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.hallRepository = hallRepository;
        this.lectureRepository = lectureRepository;
        this.examRepository = examRepository;
        this.lectureAttendanceRepository = lectureAttendanceRepository;
        this.examAttendanceRepository = examAttendanceRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        runSeed();
    }

    public void runSeed() {
        usersSeed();
        courseSeed();
    }



    // Seeders
    private void usersSeed() {
        // If the table is not empty, do not seed
        if (userRepository.count() > 0) return;

        // Add users
        User adminUser1 = createUserObject("Admin User 1", "admin1@mosip.com", "pass", "ID1", 1);
        userRepository.save(adminUser1);
        User lecturerUser1 = createUserObject("Lecturer User 1", "lecturer1@mosip.com", "pass", "ID2", 2);
        userRepository.save(lecturerUser1);
    }

    private void courseSeed() {
        // If the table is not empty, do not seed
        if (courseRepository.count() > 0) return;

        // Add courses
        Course course1 = createCourseObjects("CS4242", "Human Computer Interaction", 19);
        courseRepository.save(course1);
        Course course2 = createCourseObjects("CS4012", "Professional Practice", 19);
        courseRepository.save(course2);
        Course course3 = createCourseObjects("CS3962", "Research and Report Writing", 19);
        courseRepository.save(course3);
    }

    // Builders
    private User createUserObject(String name, String email, String password, String mosipId, int userType) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encodePassword(password));
        user.setMosipId(mosipId);
        user.setUserType(userType);
        return user;
    }

    private String encodePassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    private Course createCourseObjects(String moduleCode, String moduleName, int intake) {
        Course course = new Course();
        course.setModuleCode(moduleCode);
        course.setModuleName(moduleName);
        course.setIntake(intake);
        return course;
    }

    private Student createStudentObjects(String studentId, List<Course> courses) {
        Student student = new Student();
        student.setStudentId(studentId);
        student.setEnrolledCourses(courses);
        return student;
    }
}
