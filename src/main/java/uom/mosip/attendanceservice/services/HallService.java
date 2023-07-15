package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.HallRepository;
import uom.mosip.attendanceservice.dto.HallDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.models.Hall;

@Service
public class HallService {

    @Autowired
    private HallRepository hallRepository;

//    get all lecture halls
    public Iterable<Hall>  getAllHalls() {
        return hallRepository.findAll();
    }

//    Create lecture hall

//    update lecture hall
    public ResponseDTO updateHall(HallDTO hall) {

        Hall updatedHall = hallRepository.findById(hall.getId()).get();
        ResponseDTO responseDTO = new ResponseDTO();
        if (updatedHall == null) {
            responseDTO.setMessage("Error occur when loading existing hall data!");
            responseDTO.setStatus("HALL_NOT_FOUND");
        } else{
            String errorMessage = validateHallInputs(hall);
            if (errorMessage != null) {
                responseDTO.setMessage(errorMessage);
                responseDTO.setStatus("INVALID_INPUTS");
            } else {
                updatedHall.setName(hall.getName());
                updatedHall.setLocation(hall.getLocation());
                updatedHall.setCapacity(hall.getCapacity());
                updatedHall = hallRepository.save(updatedHall);
                responseDTO.setData(hall);
                responseDTO.setMessage("Updated lecture hall successfully!");
                responseDTO.setStatus("HALL_UPDATED_SUCCESSFULLY");

            }
        }
        return responseDTO;
    }

    private String validateHallInputs(HallDTO hall) {
        String message = null;
        if (hall.getCapacity() <= 0){
            message = "Capacity must be a positive number";
        } else if (hallRepository.findMaxExpectedAttendanceAssignedForSelectedHall(hall.getId()) != null
                && hall.getCapacity() < hallRepository.findMaxExpectedAttendanceAssignedForSelectedHall(hall.getId())) {
            message = "Issue occur in updating capacity. Need higher capacity for update.";
        } else if (hallRepository.findByName(hall.getName()) != null) {
            if (hallRepository.findByName(hall.getName()).getId() != hall.getId()){
                message = "Already exist a hall with given name. Try with different name.";
            }
        }
        return message;
    }


//    delete lecture hall
}
