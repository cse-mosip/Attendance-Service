package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uom.mosip.attendanceservice.dto.LectureAttendanceDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.dto.StudentDTO;
import uom.mosip.attendanceservice.dto.StudentLectureAttendanceDTO;
import uom.mosip.attendanceservice.models.Lecture;
import uom.mosip.attendanceservice.models.LectureAttendance;
import uom.mosip.attendanceservice.services.AdminService;
import uom.mosip.attendanceservice.services.LMSService;
import uom.mosip.attendanceservice.services.LectureService;
import uom.mosip.attendanceservice.services.RegistrationService;

import java.util.*;

@RestController
public class AdminController {

    private final AdminService adminService;
    private final LectureService lectureService;
    private final LMSService lmsService;
    private final RegistrationService registrationService;



    @Autowired
    public AdminController(AdminService adminService,LectureService lectureService,LMSService lmsService,RegistrationService registrationService) {
        this.adminService = adminService;
        this.lectureService = lectureService;
        this.lmsService = lmsService;
        this.registrationService = registrationService;
    }

    @GetMapping("/admin/lecture-attendance/student/{studentId}")
    public ResponseEntity<ResponseDTO> getStudentAttendance(@PathVariable String studentId) {
        List<LectureAttendance> attendanceList = adminService.getStudentAttendance(studentId);
        List<StudentLectureAttendanceDTO> studentLectureAttendanceDTOS = new LinkedList<>();
        for (LectureAttendance la:attendanceList) {
            studentLectureAttendanceDTOS.add(new StudentLectureAttendanceDTO(
                    lectureService.getLectureById(la.getLecture().getId()),la.getArrivalTime(),la.getId()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDTO("OK","Student Attendance Fetched",studentLectureAttendanceDTOS));
    }

    @GetMapping("/admin/lecture-attendance/lecture/{lectureId}")
    public ResponseEntity<ResponseDTO> getAttendanceForLecture(@PathVariable("lectureId") long lectureId) {
        if (lectureId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("INVALID_DATA", "Lecture ID is invalid."));
        }
        Optional<Lecture> lectureOptional = lectureService.getAttendanceForLectureById(lectureId);
        if (lectureOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO("LECTURE_ID_NOT_FOUND", "Lecture ID is not found."));
        }

        Lecture lecture = lectureOptional.get();
        List<LectureAttendance> lectureAttendances = lecture.getAttendees();
        Map<String,LectureAttendance> lectureAttendanceMap = new HashMap<>();
        for (LectureAttendance la:lectureAttendances){
            lectureAttendanceMap.put(la.getStudentId(),la);
        }
        List<String> enrolledStudentIds = lmsService.getStudentsForACourse(lecture.getCourseId());

        Map<String, StudentDTO> studentDetailsMap = registrationService.getStudentDetailsMap(enrolledStudentIds);

        List<LectureAttendaneDTO> attendanceDTOS = new LinkedList<>();

        for (String studentId: enrolledStudentIds) {
            LectureAttendance la = null;
            StudentDTO studentDTO = null;
            if (lectureAttendanceMap.containsKey(studentId)) la = lectureAttendanceMap.get(studentId);
            if (studentDetailsMap.containsKey(studentId)) studentDTO = studentDetailsMap.get(studentId);

            attendanceDTOS.add(new LectureAttendanceDTO(studentId, la, studentDTO));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("OK","Lecture Attendance Fetched By Lecture ID",attendanceDTOS));
    }

}
