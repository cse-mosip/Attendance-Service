package uom.mosip.attendanceservice.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import uom.mosip.attendanceservice.dao.*;
import uom.mosip.attendanceservice.dao.lms.CourseRepository;
import uom.mosip.attendanceservice.dao.lms.StudentRepository;
import uom.mosip.attendanceservice.models.*;
import uom.mosip.attendanceservice.models.lms.Course;
import uom.mosip.attendanceservice.models.lms.Student;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DBSeedHelper {

    private final UserRepository userRepository;
    private final HallRepository hallRepository;
    private final LectureRepository lectureRepository;
    private final ExamRepository examRepository;
    private final LectureAttendanceRepository lectureAttendanceRepository;
    private final ExamAttendanceRepository examAttendanceRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


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
        hallSeed();
        studentSeed();
        courseSeed();
        examSeed();
        lectureSeed();
        lectureAttendanceSeed();
        examAttendanceSeed();
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
        User adminUser2 = createUserObject("Admin User 2", "admin2@mosip.com", "pass", "ID3", 1);
        userRepository.save(adminUser2);
        User lecturerUser2 = createUserObject("Lecturer User 2", "lecturer2@mosip.com", "pass", "ID4", 2);
        userRepository.save(lecturerUser2);
        User adminUser3 = createUserObject("Admin User 3", "admin3@mosip.com", "pass", "ID5", 1);
        userRepository.save(adminUser3);
        User lecturerUser3 = createUserObject("Lecturer User 3", "lecturer3@mosip.com", "pass", "ID6", 2);
        userRepository.save(lecturerUser3);
        User adminUser4 = createUserObject("Admin User 4", "admin4@mosip.com", "pass", "ID7", 1);
        userRepository.save(adminUser4);
        User lecturerUser4 = createUserObject("Lecturer User 4", "lecturer4@mosip.com", "pass", "ID8", 2);
        userRepository.save(lecturerUser4);
        User adminUser5 = createUserObject("Admin User 5", "admin5@mosip.com", "pass", "ID9", 1);
        userRepository.save(adminUser5);
        User lecturerUser5 = createUserObject("Lecturer User 5", "lecturer5@mosip.com", "pass", "ID10", 2);
        userRepository.save(lecturerUser5);
    }

    private void hallSeed() {
        // If the table is not empty, do not seed
        if (hallRepository.count() > 0) return;

        // Add halls
        Hall hall1 = createHallObject("L1 lab", "2nd Floor Sumanadasa Building", 75);
        hallRepository.save(hall1);
        Hall hall2 = createHallObject("L2 lab", "3rd Floor Sumanadasa Building", 75);
        hallRepository.save(hall2);
        Hall hall3 = createHallObject("Advanced lab", "3rd Floor Sumanadasa Building", 50);
        hallRepository.save(hall3);
        Hall hall4 = createHallObject("Seminar Room", "2nd Floor Sumanadasa Building", 130);
        hallRepository.save(hall4);
        Hall hall5 = createHallObject("Tutorial Room 1", "2nd Floor Sumanadasa Building", 100);
        hallRepository.save(hall5);
        Hall hall6 = createHallObject("Old GYM", "Old GYM", 250);
        hallRepository.save(hall6);
        Hall hall7 = createHallObject("Studio 1", "Civil Department", 50);
        hallRepository.save(hall7);
        Hall hall8 = createHallObject("James George Hall", "James George Hall", 150);
        hallRepository.save(hall8);
        Hall hall9 = createHallObject("NA1", "New Auditorium", 250);
        hallRepository.save(hall9);
        Hall hall10 = createHallObject("Civil Auditorium", "Civil Department", 100);
        hallRepository.save(hall10);
    }

    private void studentSeed() {
        // If the table is not empty, do not seed
        if (studentRepository.count() > 0) return;

        // Add students
        Student student1 = createStudentObject("S1");
        studentRepository.save(student1);
        Student student2 = createStudentObject("S2");
        studentRepository.save(student2);
        Student student3 = createStudentObject("S3");
        studentRepository.save(student3);
        Student student4 = createStudentObject("S4");
        studentRepository.save(student4);
        Student student5 = createStudentObject("S5");
        studentRepository.save(student5);

        Student student6 = createStudentObject("S6");
        studentRepository.save(student6);
        Student student7 = createStudentObject("S7");
        studentRepository.save(student7);
        Student student8 = createStudentObject("S8");
        studentRepository.save(student8);
        Student student9 = createStudentObject("S9");
        studentRepository.save(student9);
        Student student10 = createStudentObject("S10");
        studentRepository.save(student10);

        Student student11 = createStudentObject("S11");
        studentRepository.save(student11);
        Student student12 = createStudentObject("S12");
        studentRepository.save(student12);
        Student student13 = createStudentObject("S13");
        studentRepository.save(student13);
        Student student14 = createStudentObject("S14");
        studentRepository.save(student14);
        Student student15 = createStudentObject("S15");
        studentRepository.save(student15);

        Student student16 = createStudentObject("190623V");
        studentRepository.save(student16);
        Student student17 = createStudentObject("190415K");
        studentRepository.save(student17);
        Student student18 = createStudentObject("190241X");
        studentRepository.save(student18);
        Student student19 = createStudentObject("190601D");
        studentRepository.save(student19);
        Student student20 = createStudentObject("190365F");
        studentRepository.save(student20);

        Student student21 = createStudentObject("190211G");
        studentRepository.save(student21);
        Student student22 = createStudentObject("1900456J");
        studentRepository.save(student22);
        Student student23 = createStudentObject("190488J");
        studentRepository.save(student23);
    }

    private void courseSeed() {
        // If the table is not empty, do not seed
        if (courseRepository.count() > 0) return;

        List<Student> studentList = (List<Student>) studentRepository.findAll();

        // Add courses
        Course course1 = createCourseObject("CS1032", "Programming Fundamentals", 22, studentList.subList(0, 5));
        courseRepository.save(course1);

        Course course2 = createCourseObject("CS1032", "Programming Fundamentals", 21, studentList.subList(5, 10));
        courseRepository.save(course2);
        Course course3 = createCourseObject("CS2012", "Principles of Object Oriented Programming", 21, studentList.subList(5, 10));
        courseRepository.save(course3);
        Course course4 = createCourseObject("CS2042", "Operating Systems", 21, studentList.subList(5, 10));
        courseRepository.save(course4);

        Course course5 = createCourseObject("CS1032", "Programming Fundamentals", 20, studentList.subList(10, 15));
        courseRepository.save(course5);
        Course course6 = createCourseObject("CS2012", "Principles of Object Oriented Programming", 20, studentList.subList(10, 15));
        courseRepository.save(course6);
        Course course7 = createCourseObject("CS2042", "Operating Systems", 20, studentList.subList(10, 15));
        courseRepository.save(course7);
        Course course8 = createCourseObject("CS3022", "Software Engineering", 20, studentList.subList(10, 15));
        courseRepository.save(course8);
        Course course9 = createCourseObject("MN3042", "Business Economics and Financial Accounting", 20, studentList.subList(10, 15));
        courseRepository.save(course9);

        Course course10 = createCourseObject("CS1032", "Programming Fundamentals", 19, studentList.subList(15, 20));
        courseRepository.save(course10);
        Course course11 = createCourseObject("CS2012", "Principles of Object Oriented Programming", 19, studentList.subList(15, 20));
        courseRepository.save(course11);
        Course course12 = createCourseObject("CS2042", "Operating Systems", 19, studentList.subList(15, 20));
        courseRepository.save(course12);
        Course course13 = createCourseObject("CS3022", "Software Engineering", 19, studentList.subList(15, 20));
        courseRepository.save(course13);
        Course course14 = createCourseObject("MN3042", "Business Economics and Financial Accounting", 19, studentList.subList(15, 20));
        courseRepository.save(course14);
        Course course15 = createCourseObject("CS4012", "Professional Practice", 19, studentList.subList(15, 20));
        courseRepository.save(course15);
        Course course16 = createCourseObject("CS4622", "Machine Learning", 19, studentList.subList(15, 20));
        courseRepository.save(course16);

        Course course17 = createCourseObject("CS1032", "Programming Fundamentals", 18, studentList.subList(20, 23));
        courseRepository.save(course17);
        Course course18 = createCourseObject("CS2012", "Principles of Object Oriented Programming", 18, studentList.subList(20, 23));
        courseRepository.save(course18);
        Course course19 = createCourseObject("CS2042", "Operating Systems", 18, studentList.subList(20, 23));
        courseRepository.save(course19);
        Course course20 = createCourseObject("CS3022", "Software Engineering", 18, studentList.subList(20, 23));
        courseRepository.save(course20);
        Course course21 = createCourseObject("MN3042", "Business Economics and Financial Accounting", 18, studentList.subList(20, 23));
        courseRepository.save(course21);
        Course course22 = createCourseObject("CS4012", "Professional Practice", 18, studentList.subList(20, 23));
        courseRepository.save(course22);
        Course course23 = createCourseObject("CS4622", "Machine Learning", 18, studentList.subList(20, 23));
        courseRepository.save(course23);
        Course course24 = createCourseObject("MA4053", "Numerical Analysis for Scientific Computing ", 18, studentList.subList(20, 23));
        courseRepository.save(course24);
    }

    private void examSeed() {
        // If the table is not empty, do not seed
        if (examRepository.count() > 0) return;

        List<Hall> halls = (List<Hall>) hallRepository.findAll();
        List<User> users = (List<User>) userRepository.findAll();

        // Add exams
        Exam exam1 = createExamObject(
                1,
                LocalDateTime.of(2023, 8, 31, 13, 0),
                LocalDateTime.of(2023, 8, 31, 15, 0),
                250,
                halls.get(0),
                users.get(0));
        examRepository.save(exam1);

        Exam exam2 = createExamObject(
                2,
                LocalDateTime.of(2023, 4, 30, 13, 0),
                LocalDateTime.of(2023, 4, 30, 15, 0),
                250,
                halls.get(1),
                users.get(1));
        examRepository.save(exam2);
        Exam exam3 = createExamObject(
                3,
                LocalDateTime.of(2023, 8, 29, 13, 0),
                LocalDateTime.of(2023, 8, 29, 15, 0),
                200,
                halls.get(2),
                users.get(2));
        examRepository.save(exam3);
        Exam exam4 = createExamObject(
                4,
                LocalDateTime.of(2023, 12, 28, 13, 0),
                LocalDateTime.of(2023, 12, 28, 15, 0),
                200,
                halls.get(3),
                users.get(3));
        examRepository.save(exam4);

        Exam exam5 = createExamObject(
                5,
                LocalDateTime.of(2021, 3, 27, 13, 0),
                LocalDateTime.of(2021, 3, 27, 15, 0),
                250,
                halls.get(4),
                users.get(4));
        examRepository.save(exam5);
        Exam exam6 = createExamObject(
                6,
                LocalDateTime.of(2021, 7, 26, 13, 0),
                LocalDateTime.of(2021, 7, 26, 15, 0),
                200,
                halls.get(5),
                users.get(5));
        examRepository.save(exam6);
        Exam exam7 = createExamObject(
                7,
                LocalDateTime.of(2021, 12, 25, 13, 0),
                LocalDateTime.of(2021, 12, 25, 15, 0),
                200,
                halls.get(6),
                users.get(6));
        examRepository.save(exam7);
        Exam exam8 = createExamObject(
                8,
                LocalDateTime.of(2022, 4, 24, 13, 0),
                LocalDateTime.of(2022, 4, 24, 15, 0),
                200,
                halls.get(7),
                users.get(7));
        examRepository.save(exam8);
        Exam exam9 = createExamObject(
                9,
                LocalDateTime.of(2023, 7, 23, 13, 0),
                LocalDateTime.of(2023, 7, 23, 15, 0),
                200,
                halls.get(8),
                users.get(8));
        examRepository.save(exam9);

        Exam exam10 = createExamObject(
                10,
                LocalDateTime.of(2020, 5, 22, 13, 0),
                LocalDateTime.of(2020, 5, 22, 15, 0),
                250,
                halls.get(9),
                users.get(9));
        examRepository.save(exam10);
        Exam exam11 = createExamObject(
                11,
                LocalDateTime.of(2021, 4, 21, 13, 0),
                LocalDateTime.of(2021, 4, 21, 15, 0),
                128,
                halls.get(0),
                users.get(0));
        examRepository.save(exam11);
        Exam exam12 = createExamObject(
                12,
                LocalDateTime.of(2021, 12, 20, 13, 0),
                LocalDateTime.of(2021, 12, 20, 15, 0),
                128,
                halls.get(1),
                users.get(1));
        examRepository.save(exam12);
        Exam exam13 = createExamObject(
                13,
                LocalDateTime.of(2022, 6, 19, 13, 0),
                LocalDateTime.of(2022, 6, 19, 15, 0),
                128,
                halls.get(2),
                users.get(2));
        examRepository.save(exam13);
        Exam exam14 = createExamObject(
                14,
                LocalDateTime.of(2022, 12, 18, 13, 0),
                LocalDateTime.of(2022, 12, 18, 15, 0),
                128,
                halls.get(3),
                users.get(3));
        examRepository.save(exam14);
        Exam exam15 = createExamObject(
                15,
                LocalDateTime.of(2023, 6, 17, 13, 0),
                LocalDateTime.of(2023, 6, 17, 15, 0),
                128,
                halls.get(2),
                users.get(2));
        examRepository.save(exam15);
        Exam exam16 = createExamObject(
                16,
                LocalDateTime.of(2023, 12, 16, 13, 0),
                LocalDateTime.of(2023, 12, 16, 15, 0),
                128,
                halls.get(3),
                users.get(3));
        examRepository.save(exam16);

        Exam exam17 = createExamObject(
                17,
                LocalDateTime.of(2019, 5, 15, 13, 0),
                LocalDateTime.of(2019, 5, 15, 15, 0),
                250,
                halls.get(4),
                users.get(4));
        examRepository.save(exam17);
        Exam exam18 = createExamObject(
                18,
                LocalDateTime.of(2019, 11, 14, 13, 0),
                LocalDateTime.of(2019, 11, 14, 15, 0),
                128,
                halls.get(5),
                users.get(5));
        examRepository.save(exam18);
        Exam exam19 = createExamObject(
                19,
                LocalDateTime.of(2020, 8, 13, 13, 0),
                LocalDateTime.of(2020, 8, 13, 15, 0),
                128,
                halls.get(6),
                users.get(6));
        examRepository.save(exam19);
        Exam exam20 = createExamObject(
                20,
                LocalDateTime.of(2021, 6, 12, 13, 0),
                LocalDateTime.of(2021, 6, 12, 15, 0),
                128,
                halls.get(7),
                users.get(7));
        examRepository.save(exam20);
        Exam exam21 = createExamObject(
                21,
                LocalDateTime.of(2022, 2, 11, 13, 0),
                LocalDateTime.of(2022, 2, 11, 15, 0),
                128,
                halls.get(8),
                users.get(8));
        examRepository.save(exam21);
        Exam exam22 = createExamObject(
                22,
                LocalDateTime.of(2022, 6, 10, 13, 0),
                LocalDateTime.of(2022, 6, 10, 15, 0),
                128,
                halls.get(9),
                users.get(9));
        examRepository.save(exam22);
        Exam exam23 = createExamObject(
                23,
                LocalDateTime.of(2022, 12, 9, 13, 0),
                LocalDateTime.of(2022, 12, 9, 15, 0),
                128,
                halls.get(0),
                users.get(0));
        examRepository.save(exam23);
        Exam exam24 = createExamObject(
                24,
                LocalDateTime.of(2023, 5, 8, 13, 0),
                LocalDateTime.of(2023, 5, 8, 15, 0),
                128,
                halls.get(1),
                users.get(1));
        examRepository.save(exam24);

        Exam exam25 = createExamObject(
                14,
                LocalDateTime.of(2023, 8, 15, 8, 0),
                LocalDateTime.of(2023, 8, 15, 10, 0),
                128,
                halls.get(2),
                users.get(1));
        examRepository.save(exam25);
        Exam exam26 = createExamObject(
                15,
                LocalDateTime.of(2023, 8, 16, 8, 30),
                LocalDateTime.of(2023, 8, 16, 22, 30),
                128,
                halls.get(3),
                users.get(3));
        examRepository.save(exam26);
    }

    private void lectureSeed() {
        // If the table is not empty, do not seed
        if (lectureRepository.count() > 0) return;

        List<Hall> halls = (List<Hall>) hallRepository.findAll();
        List<User> users = (List<User>) userRepository.findAll();

        // Add lectures
        Lecture lecture1 = createLectureObject(
                1,
                LocalDateTime.of(2023, 8, 17, 8, 0),
                LocalDateTime.of(2023, 8, 17, 10, 0),
                250,
                halls.get(0),
                users.get(0));
        lectureRepository.save(lecture1);

        Lecture lecture2 = createLectureObject(
                2,
                LocalDateTime.of(2023, 4, 16, 8, 0),
                LocalDateTime.of(2023, 4, 16, 10, 0),
                250,
                halls.get(1),
                users.get(1));
        lectureRepository.save(lecture2);
        Lecture lecture3 = createLectureObject(
                3,
                LocalDateTime.of(2023, 8, 15, 8, 0),
                LocalDateTime.of(2023, 8, 15, 10, 0),
                200,
                halls.get(2),
                users.get(2));
        lectureRepository.save(lecture3);
        Lecture lecture4 = createLectureObject(
                4,
                LocalDateTime.of(2023, 12, 14, 8, 0),
                LocalDateTime.of(2023, 12, 14, 10, 0),
                200,
                halls.get(3),
                users.get(3));
        lectureRepository.save(lecture4);

        Lecture lecture5 = createLectureObject(
                5,
                LocalDateTime.of(2021, 3, 13, 10, 0),
                LocalDateTime.of(2021, 3, 13, 12, 0),
                250,
                halls.get(4),
                users.get(4));
        lectureRepository.save(lecture5);
        Lecture lecture6 = createLectureObject(
                6,
                LocalDateTime.of(2021, 7, 12, 8, 0),
                LocalDateTime.of(2021, 7, 12, 10, 0),
                200,
                halls.get(5),
                users.get(5));
        lectureRepository.save(lecture6);
        Lecture lecture7 = createLectureObject(
                7,
                LocalDateTime.of(2021, 12, 11, 13, 0),
                LocalDateTime.of(2021, 12, 11, 15, 0),
                200,
                halls.get(6),
                users.get(6));
        lectureRepository.save(lecture7);
        Lecture lecture8 = createLectureObject(
                8,
                LocalDateTime.of(2022, 4, 10, 11, 0),
                LocalDateTime.of(2022, 4, 10, 13, 0),
                200,
                halls.get(7),
                users.get(7));
        lectureRepository.save(lecture8);
        Lecture lecture9 = createLectureObject(
                9,
                LocalDateTime.of(2023, 7, 9, 13, 0),
                LocalDateTime.of(2023, 7, 9, 15, 0),
                200,
                halls.get(8),
                users.get(8));
        lectureRepository.save(lecture9);

        Lecture lecture10 = createLectureObject(
                10,
                LocalDateTime.of(2020, 5, 8, 8, 0),
                LocalDateTime.of(2020, 5, 8, 10, 0),
                250,
                halls.get(9),
                users.get(9));
        lectureRepository.save(lecture10);
        Lecture lecture11 = createLectureObject(
                11,
                LocalDateTime.of(2021, 4, 7, 10, 0),
                LocalDateTime.of(2021, 4, 7, 12, 0),
                128,
                halls.get(0),
                users.get(0));
        lectureRepository.save(lecture11);
        Lecture lecture12 = createLectureObject(
                12,
                LocalDateTime.of(2021, 12, 6, 15, 0),
                LocalDateTime.of(2021, 12, 6, 17, 0),
                128,
                halls.get(1),
                users.get(1));
        lectureRepository.save(lecture12);
        Lecture lecture13 = createLectureObject(
                13,
                LocalDateTime.of(2022, 6, 5, 15, 0),
                LocalDateTime.of(2022, 6, 5, 17, 0),
                128,
                halls.get(2),
                users.get(2));
        lectureRepository.save(lecture13);
        Lecture lecture14 = createLectureObject(
                14,
                LocalDateTime.of(2022, 12, 4, 8, 0),
                LocalDateTime.of(2022, 12, 4, 10, 0),
                128,
                halls.get(3),
                users.get(3));
        lectureRepository.save(lecture14);
        Lecture lecture15 = createLectureObject(
                15,
                LocalDateTime.of(2023, 6, 3, 10, 0),
                LocalDateTime.of(2023, 6, 3, 12, 0),
                128,
                halls.get(2),
                users.get(2));
        lectureRepository.save(lecture15);
        Lecture lecture16 = createLectureObject(
                16,
                LocalDateTime.of(2023, 12, 2, 13, 0),
                LocalDateTime.of(2023, 12, 2, 15, 0),
                128,
                halls.get(3),
                users.get(3));
        lectureRepository.save(lecture16);

        Lecture lecture17 = createLectureObject(
                17,
                LocalDateTime.of(2019, 5, 1, 10, 0),
                LocalDateTime.of(2019, 5, 1, 12, 0),
                250,
                halls.get(4),
                users.get(4));
        lectureRepository.save(lecture17);
        Lecture lecture18 = createLectureObject(
                18,
                LocalDateTime.of(2019, 10, 31, 15, 0),
                LocalDateTime.of(2019, 10, 31, 17, 0),
                128,
                halls.get(5),
                users.get(5));
        lectureRepository.save(lecture18);
        Lecture lecture19 = createLectureObject(
                19,
                LocalDateTime.of(2020, 7, 30, 8, 0),
                LocalDateTime.of(2020, 7, 30, 10, 0),
                128,
                halls.get(6),
                users.get(6));
        lectureRepository.save(lecture19);
        Lecture lecture20 = createLectureObject(
                20,
                LocalDateTime.of(2021, 5, 29, 9, 0),
                LocalDateTime.of(2021, 5, 29, 11, 0),
                128,
                halls.get(7),
                users.get(7));
        lectureRepository.save(lecture20);
        Lecture lecture21 = createLectureObject(
                21,
                LocalDateTime.of(2022, 1, 28, 7, 0),
                LocalDateTime.of(2022, 1, 28, 9, 0),
                128,
                halls.get(8),
                users.get(8));
        lectureRepository.save(lecture21);
        Lecture lecture22 = createLectureObject(
                22,
                LocalDateTime.of(2022, 5, 27, 11, 0),
                LocalDateTime.of(2022, 5, 27, 13, 0),
                128,
                halls.get(9),
                users.get(9));
        lectureRepository.save(lecture22);
        Lecture lecture23 = createLectureObject(
                23,
                LocalDateTime.of(2022, 11, 26, 8, 0),
                LocalDateTime.of(2022, 11, 26, 10, 0),
                128,
                halls.get(0),
                users.get(0));
        lectureRepository.save(lecture23);
        Lecture lecture24 = createLectureObject(
                24,
                LocalDateTime.of(2023, 3, 25, 10, 0),
                LocalDateTime.of(2023, 3, 25, 12, 0),
                128,
                halls.get(1),
                users.get(1));
        lectureRepository.save(lecture24);

        Lecture lecture25 = createLectureObject(
                13,
                LocalDateTime.of(2023, 8, 15, 15, 0),
                LocalDateTime.of(2023, 8, 15, 17, 0),
                128,
                halls.get(4),
                users.get(1));
        lectureRepository.save(lecture25);
        Lecture lecture26 = createLectureObject(
                16,
                LocalDateTime.of(2023, 8, 16, 8, 0),
                LocalDateTime.of(2023, 8, 16, 23, 0),
                128,
                halls.get(5),
                users.get(1));
        lectureRepository.save(lecture26);
    }

    private void lectureAttendanceSeed() {

        // If the table is not empty, do not seed
        if (lectureAttendanceRepository.count() > 0) return;

        List<Lecture> lectureList = (List<Lecture>) lectureRepository.findAll();

        // Add lecture attendance
        LectureAttendance lectureAttendance1 = createLectureAttendanceObject(
                "190623V",
                LocalDateTime.of(2021, 4, 7, 10, 10),
                lectureList.get(10)
        );
        lectureAttendanceRepository.save(lectureAttendance1);

        updateLecture(lectureList.get(10), true, true, 1);

        LectureAttendance lectureAttendance2 = createLectureAttendanceObject(
                "190623V",
                LocalDateTime.of(2023, 8, 15, 15, 1),
                lectureList.get(24)
        );
        lectureAttendanceRepository.save(lectureAttendance2);

        LectureAttendance lectureAttendance3 = createLectureAttendanceObject(
                "190415K",
                LocalDateTime.of(2023, 8, 15, 15, 2),
                lectureList.get(24)
        );
        lectureAttendanceRepository.save(lectureAttendance3);

        updateLecture(lectureList.get(24), true, true, 2);

        LectureAttendance lectureAttendance4 = createLectureAttendanceObject(
                "190241X",
                LocalDateTime.of(2023, 8, 16, 10, 0),
                lectureList.get(25)
        );
        lectureAttendanceRepository.save(lectureAttendance4);

        updateLecture(lectureList.get(25), true, false, 1);
    }

    private void examAttendanceSeed() {

        // If the table is not empty, do not seed
        if (examAttendanceRepository.count() > 0) return;

        List<Exam> examList = (List<Exam>) examRepository.findAll();

        // Add exam attendance
        ExamAttendance examAttendance1 = createExamAttendanceObject(
                "190415K",
                LocalDateTime.of(2021, 4, 21, 12, 50),
                true,
                LocalDateTime.of(2021, 4, 21, 15, 3),
                examList.get(10)
        );
        examAttendanceRepository.save(examAttendance1);

        ExamAttendance examAttendance2 = createExamAttendanceObject(
                "190241X",
                LocalDateTime.of(2021, 4, 21, 12, 55),
                true,
                LocalDateTime.of(2021, 4, 21, 15, 1),
                examList.get(10)
        );
        examAttendanceRepository.save(examAttendance2);

        updateExam(examList.get(10), true, true, 2);

        ExamAttendance examAttendance3 = createExamAttendanceObject(
                "190623V",
                LocalDateTime.of(2023, 8, 15, 7, 55),
                true,
                LocalDateTime.of(2023, 8, 15, 10, 5),
                examList.get(24)
        );
        examAttendanceRepository.save(examAttendance3);

        ExamAttendance examAttendance4 = createExamAttendanceObject(
                "190415K",
                LocalDateTime.of(2023, 8, 15, 7, 56),
                true,
                LocalDateTime.of(2023, 8, 15, 10, 1),
                examList.get(24)
        );
        examAttendanceRepository.save(examAttendance4);

        ExamAttendance examAttendance5 = createExamAttendanceObject(
                "190241X",
                LocalDateTime.of(2023, 8, 15, 7, 57),
                true,
                LocalDateTime.of(2023, 8, 15, 10, 15),
                examList.get(24)
        );
        examAttendanceRepository.save(examAttendance5);

        updateExam(examList.get(24), true, true, 3);

        ExamAttendance examAttendance6 = createExamAttendanceObject(
                "190241X",
                LocalDateTime.of(2023, 8, 16, 7, 59),
                false,
                null,
                examList.get(25)
        );
        examAttendanceRepository.save(examAttendance6);

        updateExam(examList.get(25), true, false, 1);
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

    private Hall createHallObject(String name, String location, int capacity) {
        Hall hall = new Hall();
        hall.setName(name);
        hall.setLocation(location);
        hall.setCapacity(capacity);
        return hall;
    }

    private Course createCourseObject(String moduleCode, String moduleName, int intake, List<Student> students) {
        Course course = new Course();
        course.setModuleCode(moduleCode);
        course.setModuleName(moduleName);
        course.setIntake(intake);
        course.setEnrolledStudents(students);
        return course;
    }

    private Student createStudentObject(String MOSIPId) {
        Student student = new Student();
        student.setStudentId(MOSIPId);
        return student;
    }

    private Exam createExamObject(long courseId, LocalDateTime startTime, LocalDateTime endTime, int expectedAttendance, Hall hall, User invigilator) {
        Exam exam = new Exam();
        exam.setCourseId(courseId);
        exam.setStartTime(startTime);
        exam.setEndTime(endTime);
        exam.setExpectedAttendance(expectedAttendance);
        exam.setHall(hall);
        exam.setInvigilator(invigilator);
        return exam;
    }

    private Lecture createLectureObject(long courseId, LocalDateTime startTime, LocalDateTime endTime, int expectedAttendance, Hall hall, User lecturer) {
        Lecture lecture = new Lecture();
        lecture.setCourseId(courseId);
        lecture.setStartTime(startTime);
        lecture.setEndTime(endTime);
        lecture.setExpectedAttendance(expectedAttendance);
        lecture.setHall(hall);
        lecture.setLecturer(lecturer);
        return lecture;
    }

    private LectureAttendance createLectureAttendanceObject(String studentId, LocalDateTime arrivalTime, Lecture lecture) {
        LectureAttendance lectureAttendance = new LectureAttendance();
        lectureAttendance.setStudentId(studentId);
        lectureAttendance.setArrivalTime(arrivalTime);
        lectureAttendance.setLecture(lecture);
        return lectureAttendance;
    }

    private ExamAttendance createExamAttendanceObject(String studentId, LocalDateTime markedTime, boolean isValidated, LocalDateTime validatedTime, Exam exam) {
        ExamAttendance examAttendance = new ExamAttendance();
        examAttendance.setStudentId(studentId);
        examAttendance.setMarkedTime(markedTime);
        examAttendance.setValidated(isValidated);
        examAttendance.setValidatedTime(validatedTime);
        examAttendance.setExam(exam);
        return examAttendance;
    }

    private String encodePassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    private void updateLecture(Lecture lecture, boolean isStarted, boolean isEnded, int attendance) {
        lecture.setStarted(isStarted);
        lecture.setEnded(isEnded);
        lecture.setAttendance(attendance);

        lectureRepository.save(lecture);
    }

    private void updateExam(Exam exam, boolean isStarted, boolean isEnded, int attendance) {
        exam.setStarted(isStarted);
        exam.setEnded(isEnded);
        exam.setAttendance(attendance);

        examRepository.save(exam);
    }

}
