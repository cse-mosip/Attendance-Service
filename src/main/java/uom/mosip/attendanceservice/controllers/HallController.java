package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.services.HallService;


@RestController
@RequestMapping("lectureHall")
@CrossOrigin
public class HallController {
    private final HallService hallService;

    @Autowired
    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

//    get all lecture halls
    @GetMapping("/getAllHalls")
    public ResponseDTO getAllHalls(){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(hallService.getAllHalls());
        responseDTO.setMessage("Get all lecture halls successfully!");
        responseDTO.setStatus("200");
        return responseDTO;
    }

//    Create lecture hall

//    update lecture hall

//    delete lecture hall

}