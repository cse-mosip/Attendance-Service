package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uom.mosip.attendanceservice.dto.HallDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.models.Hall;
import uom.mosip.attendanceservice.services.HallService;

@RestController
@RequestMapping("admin/hall")
@CrossOrigin
public class HallController {
    private final HallService hallService;

    @Autowired
    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    // get all lecture halls
    @GetMapping("/getAllHalls")
    public ResponseDTO getAllHalls() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(hallService.getAllHalls());
        responseDTO.setMessage("Get all lecture halls successfully!");
        responseDTO.setStatus("200");
        return responseDTO;
    }

    // Create lecture hall

    // update lecture hall
    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateHall(@RequestBody HallDTO hall) {
        return new ResponseEntity<>(hallService.updateHall(hall), HttpStatus.OK);
    }

    // delete lecture hall
    @DeleteMapping("/deleteHall/{hallId}")
    public void deleteHall(@PathVariable("hallId") long hallId) {
        hallService.deleteHallById(hallId);
    }

    //get hall by hallId
    @GetMapping("/getHall/{hallId}")
    public ResponseEntity<ResponseDTO> getHallById(@PathVariable("hallId") long hallId) {
        ResponseDTO responseDTO = hallService.getHallById(hallId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }


}