package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.services.LectureService;

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

    @GetMapping(path = "/getAllLectures", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getAllLectures(){
        ResponseDTO responseDTO = lectureService.getAllLectures();
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
