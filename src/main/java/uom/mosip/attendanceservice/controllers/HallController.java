package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uom.mosip.attendanceservice.dto.HallDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.services.HallService;

@RestController
@RequestMapping("admin/hall")
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
        responseDTO.setStatus("OK");
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
    public ResponseEntity<ResponseDTO> deleteHall(@PathVariable("hallId") long hallId) {
        ResponseDTO responseDTO = hallService.deleteHallById(hallId);

        if (responseDTO.getStatus().equals("HALL_NOT_FOUND")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    //get hall by hallId
    @GetMapping("/getHall/{hallId}")
    public ResponseEntity<ResponseDTO> getHallById(@PathVariable("hallId") long hallId) {
        //validate id is valid
        if (hallId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("INVALID_DATA", "Invalid hall id."));
        }

        ResponseDTO responseDTO = hallService.getHallById(hallId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }


}