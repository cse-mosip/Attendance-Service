package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uom.mosip.attendanceservice.dto.CreateLectureRequestDTO;
import uom.mosip.attendanceservice.dto.LectureDTO;
import uom.mosip.attendanceservice.dto.LectureUpdateRequestDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.dto.auth.UserDetails;
import uom.mosip.attendanceservice.helpers.AuthHelper;
import uom.mosip.attendanceservice.services.LectureService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/admin/lecture")
public class LectureController {
    private final LectureService lectureService;
    private final AuthHelper authHelper;

    @Autowired
    public LectureController(LectureService lectureService, AuthHelper authHelper) {
        this.lectureService = lectureService;
        this.authHelper = authHelper;
    }

    @GetMapping(path = "/start-lecture/{lectureId}")
    public ResponseEntity<ResponseDTO> startLecture(@PathVariable("lectureId") long lectureId) {
        ResponseDTO responseDTO = lectureService.startLecture(lectureId);

        if (Objects.equals(responseDTO.getStatus(), "INVALID_DATA")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping(path = "/end-lecture/{lectureId}")
    public ResponseEntity<ResponseDTO> endLecture(@PathVariable("lectureId") long lectureId) {
        ResponseDTO responseDTO = lectureService.endLecture(lectureId);

        if (Objects.equals(responseDTO.getStatus(), "INVALID_DATA")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping(path = "/get-lecture/{lectureId}")
    public ResponseEntity<ResponseDTO> getLectureById(@PathVariable long lectureId) {
        if (lectureId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("INVALID_DATA", "Lecture ID is invalid."));
        }

        LectureDTO lectureDTO = lectureService.getLectureById(lectureId);

        if (lectureDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO("LECTURE_NOT_FOUND", "Lecture ID is not found."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO("OK", "Lecture Fetched.", lectureDTO));
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createLecture(@RequestBody CreateLectureRequestDTO createLectureRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(lectureService.createLecture(createLectureRequestDTO));
    }

    @GetMapping(path = "/getAllLectures", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getAllLectures() {
        UserDetails userDetails = authHelper.getCurrentUserDetails();

        List<LectureDTO> lectureDTOList = lectureService.getAllLectures(userDetails.getUserID());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO("OK", "Lectures Fetched.", lectureDTOList));

    }

    @PutMapping(path = "/update")
    public ResponseDTO markLectureAttendance(@RequestBody LectureUpdateRequestDTO lectureUpdateRequestDTO) {
        return lectureService.updateLecture(lectureUpdateRequestDTO);
    }

    @DeleteMapping("/deleteLecture/{lectureId}")
    public ResponseDTO deleteLecture(@PathVariable("lectureId") long hallId) {
        return lectureService.deleteLectureByID(hallId);
    }
}
