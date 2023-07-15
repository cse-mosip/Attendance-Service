package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.HallRepository;
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
    public ResponseDTO updateHall(Hall hall) {

        Hall updatedHall = hallRepository.findById(hall.getId()).get();
        ResponseDTO responseDTO = new ResponseDTO();
        if (updatedHall == null) {
            responseDTO.setMessage("Error occur when loading existing hall data!");
            responseDTO.setStatus("HALL_NOT_FOUND");
        } else {
            updatedHall.setName(hall.getName());
            updatedHall.setLocation(hall.getLocation());
            updatedHall.setCapacity(hall.getCapacity());
            responseDTO.setData(hallRepository.save(updatedHall));
            responseDTO.setMessage("Updated lecture hall successfully!");
            responseDTO.setStatus("HALL_UPDATED_SUCCESSFULLY");

        }
        return responseDTO;
    }


//    delete lecture hall
}
