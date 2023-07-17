package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import uom.mosip.attendanceservice.dto.LectureDTO;
import uom.mosip.attendanceservice.dto.LectureUpdateRequestDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.services.LectureService;
import uom.mosip.attendanceservice.models.Lecture;

import java.util.Objects;

@RestController
@RequestMapping(path = "/admin/lecture")
public class LectureController {
    private final LectureService lectureService;

    @Autowired
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
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
    public Object getLectureById(@PathVariable long lectureId) {
        Lecture lecture = lectureService.getLectureById(lectureId);
        if (lecture == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO("LECTURE_NOT_FOUND", "Lecture ID is not found."));
        }
        return new ResponseDTO("OK", "Lecture Fetched.", lecture);
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createLecture(@RequestBody LectureDTO lectureDTO) {
        return new ResponseEntity<>(lectureService.createLecture(lectureDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/getAllLectures", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getAllLectures() {
        ResponseDTO responseDTO = lectureService.getAllLectures();
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    }

    @PutMapping(path = "/update")
    public ResponseDTO markLectureAttendance(@RequestBody LectureUpdateRequestDTO lectureUpdateRequestDTO) {
        return lectureService.updateLecture(lectureUpdateRequestDTO);
    }

}
