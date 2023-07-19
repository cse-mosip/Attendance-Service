package uom.mosip.attendanceservice.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import uom.mosip.attendanceservice.dao.*;
import uom.mosip.attendanceservice.dao.lms.CourseRepository;
import uom.mosip.attendanceservice.dao.lms.StudentRepository;
import uom.mosip.attendanceservice.models.User;

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
}
