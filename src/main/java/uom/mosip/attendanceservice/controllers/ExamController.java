package uom.mosip.attendanceservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uom.mosip.attendanceservice.dto.CreateExamRequestDTO;
import uom.mosip.attendanceservice.dto.ExamDTO;
import uom.mosip.attendanceservice.dto.GetExamsRequestDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.dto.auth.UserDetails;
import uom.mosip.attendanceservice.helpers.AuthHelper;
import uom.mosip.attendanceservice.services.ExamService;

import java.util.List;

@RestController
@RequestMapping("admin/exam")
public class ExamController {

    private final ExamService examService;
    private final AuthHelper authHelper;

    public ExamController(ExamService examService, AuthHelper authHelper) {
        this.examService = examService;
        this.authHelper = authHelper;
    }

    //Create exam method like exam create method
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createExam(@RequestBody CreateExamRequestDTO createExamRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(examService.createExam(createExamRequestDTO));
    }

    @GetMapping("/get-exam/{examId}")
    public ResponseEntity<ResponseDTO> getExamById(@PathVariable long examId) {
        //validate the examId is valid or not
        if (examId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("INVALID_DATA", "Exam ID is invalid."));
        }

        ExamDTO examDTO = examService.getExamById(examId);
        if (examDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO("EXAM_NOT_FOUND", "Exam ID is not found."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO("OK", "Exams Fetched.", examDTO));
    }

    @PostMapping("/all-exams")
    public ResponseEntity<ResponseDTO> getAllExams(@RequestBody GetExamsRequestDTO getExamsRequestDTO) {
        UserDetails userDetails = authHelper.getCurrentUserDetails();

        List<ExamDTO> examDTOList = examService.getAllExams(userDetails.getUserID(), getExamsRequestDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO("OK", "Exams Fetched.", examDTOList));
    }

    @DeleteMapping("/delete/{examId}")
    public ResponseDTO deleteExam(@PathVariable long examId) {
        return examService.deleteExam(examId);
    }



}
