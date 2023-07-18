package uom.mosip.attendanceservice.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.HallRepository;
import uom.mosip.attendanceservice.dto.HallDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.models.Exam;
import uom.mosip.attendanceservice.models.Hall;
import uom.mosip.attendanceservice.models.Lecture;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HallService {

    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private ModelMapper modelMapper;

    // get all lecture halls
    public Iterable<HallDTO> getAllHalls() {
        Iterable<Hall> halls = hallRepository.findAll();
        return modelMapper.map(halls, new TypeToken<List<HallDTO>>() {
        }.getType());
    }

    // Create lecture hall

    // update lecture hall
    public ResponseDTO updateHall(HallDTO hall) {

        ResponseDTO responseDTO = new ResponseDTO();
        if (hallRepository.findById(hall.getId()).isEmpty()) {
            responseDTO.setMessage("Error occur when loading existing hall data!");
            responseDTO.setStatus("HALL_NOT_FOUND");
        } else {
            Hall updatedHall = hallRepository.findById(hall.getId()).get();
            String errorMessage = validateHallInputs(hall);
            if (errorMessage != null) {
                responseDTO.setMessage(errorMessage);
                responseDTO.setStatus("INVALID_INPUTS");
            } else {
                updatedHall.setName(hall.getName());
                updatedHall.setLocation(hall.getLocation());
                updatedHall.setCapacity(hall.getCapacity());
                hallRepository.save(updatedHall);
                responseDTO.setData(hall);
                responseDTO.setMessage("Updated lecture hall successfully!");
                responseDTO.setStatus("HALL_UPDATED_SUCCESSFULLY");

            }
        }
        return responseDTO;
    }

    private String validateHallInputs(HallDTO hall) {
        String message = null;
        if (hall.getCapacity() <= 0) {
            message = "Capacity must be a positive number";
        } else if (hallRepository.findMaxExpectedAttendanceAssignedForSelectedHall(hall.getId()) != null
                && hall.getCapacity() < hallRepository.findMaxExpectedAttendanceAssignedForSelectedHall(hall.getId())) {
            message = "Issue occur in updating capacity. Need higher capacity for update.";
        } else if (hallRepository.findByName(hall.getName()) != null) {
            if (hallRepository.findByName(hall.getName()).getId() != hall.getId()) {
                message = "Already exist a hall with given name. Try with different name.";
            }
        }
        return message;
    }

    // delete lecture hall
    public ResponseDTO deleteHallById(long hallId) {

        ResponseDTO responseDTO = new ResponseDTO();

        if (hallRepository.findById(hallId).isEmpty()) {
            responseDTO.setMessage("No hall found corresponding to the hall ID!");
            responseDTO.setStatus("HALL_NOT_FOUND");
        } else {
            Hall deletedHall = hallRepository.findById(hallId).get();
            deletedHall.setActive(false);
            hallRepository.save(deletedHall);
            responseDTO.setMessage("Deleted lecture hall successfully!");
            responseDTO.setStatus("HALL_DELETED_SUCCESSFULLY");
        }

        return responseDTO;
    }

    //get hall by hallId
    public Hall getHallById(long hallId) {
        Optional<Hall> hallOptional = hallRepository.findById(hallId);
        return hallOptional.orElse(null);
    }

    public boolean isHallAvailable(Hall hall, LocalDateTime startTime, LocalDateTime endTime) {
        if (!hall.isActive()) {
            return false;
        }

        List<Lecture> lectureList = hall.getLectures();
        List<Exam> examList = hall.getExams();

        for (Lecture lecture : lectureList) {
            if (lecture.getEndTime().isAfter(startTime) || lecture.getStartTime().isBefore(endTime)) {
                return false;
            }
        }

        for (Exam exam : examList) {
            if (exam.getEndTime().isAfter(startTime) || exam.getStartTime().isBefore(endTime)) {
                return false;
            }
        }

        return true;
    }

}
