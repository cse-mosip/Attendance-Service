package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.models.Exam;
import uom.mosip.attendanceservice.services.ExamService;

import java.util.Iterator;

@RestController
@RequestMapping("admin/exam")
public class ExamController {

    @Autowired
    private ExamService examService;


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
