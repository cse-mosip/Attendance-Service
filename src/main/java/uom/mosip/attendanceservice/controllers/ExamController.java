package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uom.mosip.attendanceservice.dto.ExamAttendanceDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.models.Exam;
import uom.mosip.attendanceservice.models.ExamAttendance;
import uom.mosip.attendanceservice.services.ExamService;
import uom.mosip.attendanceservice.services.LMSService;

import java.util.*;

@RestController
@RequestMapping("admin/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private LMSService lmsService;

    @GetMapping("/exam-attendance/{examId}")
    public Object getAttendanceForAnExam(@PathVariable long examId) {
        Optional<Exam> examOptional = examService.getAttendanceForAnExamById(examId);

        if (examOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO("EXAM_NOT_FOUND", "Exam ID is not found."));

        // Get Attendance
        Exam exam = examOptional.get();
        List<ExamAttendance> examAttendance = exam.getAttendees();
        Map<String, ExamAttendance> attendanceMap = new HashMap<>();
        for (ExamAttendance ea: examAttendance) {
            attendanceMap.put(ea.getStudentId(), ea);
        }

        // Get all enrolled Students and map their attendance
        List<String> enrolledStudentIds = lmsService.getStudentsForACourse(exam.getModuleCode(), exam.getIntake());
        List<ExamAttendanceDTO> attendanceDTOS = new LinkedList<>();
        for (String studentId: enrolledStudentIds) {
            ExamAttendance ea = null;
            if (attendanceMap.containsKey(studentId)) {
                ea = attendanceMap.get(studentId);
            }
            attendanceDTOS.add(new ExamAttendanceDTO(studentId, ea));
        }

        return new ResponseDTO("OK", "Attendance Fetched.", attendanceDTOS);
    }

    @GetMapping("/get-exam/{examId}")
    public Object getExamById(@PathVariable long examId){
        Exam exam = examService.getExamById(examId);
        if (exam == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO("EXAM_NOT_FOUND", "Exam ID is not found."));
        }
        return new ResponseDTO("OK", "Exam Fetched.", exam);
    }

    @GetMapping("/all-exams")
    public Object getAllExams(){
        Iterable<Exam> exams = examService.getAllExams();
        Iterator<Exam> iterator = exams.iterator();
        if (iterator.hasNext()) {
            return new ResponseDTO("OK", "Exams Fetched.", exams);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO("EXAMS_NOT_FOUND", "Exams are not found."));
        }
    }
}
