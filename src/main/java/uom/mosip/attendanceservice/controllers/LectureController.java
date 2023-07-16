package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.models.Lecture;
import uom.mosip.attendanceservice.services.LectureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("admin/lecture")
@CrossOrigin
public class LectureController {
    private final LectureService lectureService;
    private static final Logger logger = LoggerFactory.getLogger(LectureController.class);

    @Autowired
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping(path = "/getAllLectures")
    public ResponseEntity<ResponseDTO> getAllLectures(){
        ResponseDTO responseDTO = lectureService.getAllLectures();
        logger.info(responseDTO.toString());
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
