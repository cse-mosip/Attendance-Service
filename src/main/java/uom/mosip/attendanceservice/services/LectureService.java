package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.LectureRepository;
import uom.mosip.attendanceservice.dto.CreateLectureRequestDTO;
import uom.mosip.attendanceservice.dto.LectureDTO;
import uom.mosip.attendanceservice.dto.LectureUpdateRequestDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.models.Hall;
import uom.mosip.attendanceservice.models.Lecture;
import uom.mosip.attendanceservice.models.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;
    private final HallService hallService;
    private final UserService userService;

    private final LMSService lmsService;

    @Autowired
    public LectureService(LectureRepository lectureRepository, HallService hallService, UserService userService, LMSService lmsService) {
        this.lectureRepository = lectureRepository;
        this.hallService = hallService;
        this.userService = userService;
        this.lmsService = lmsService;
    }

    public ResponseDTO startLecture(long lecture_id) {

        Optional<Lecture> lecture = lectureRepository.findById(lecture_id);

        if (lecture.isPresent()) {
            Lecture validLecture = lecture.get();

            if (validLecture.isStarted()) {
                return new ResponseDTO("INVALID_DATA", "Lecture is already started");
            } else if (validLecture.isEnded()) {
                return new ResponseDTO("INVALID_DATA", "Lecture has already ended");
            } else {
                validLecture.setStarted(true);
                lectureRepository.save(validLecture);
                return new ResponseDTO("OK", "Lecture started successfully", lecture_id);
            }

        } else {
            return new ResponseDTO("INVALID_DATA", "Invalid lecture");
        }
    }

    public ResponseDTO endLecture(long lecture_id) {

        Optional<Lecture> lecture = lectureRepository.findById(lecture_id);

        if (lecture.isPresent()) {
            Lecture validLecture = lecture.get();

            if (!validLecture.isStarted()) {
                return new ResponseDTO("INVALID_DATA", "Lecture is not started");
            } else if (validLecture.isEnded()) {
                return new ResponseDTO("INVALID_DATA", "Lecture has already ended");
            } else {
                validLecture.setEnded(true);
                lectureRepository.save(validLecture);
                return new ResponseDTO("OK", "Lecture finished successfully", lecture_id);
            }

        } else {
            return new ResponseDTO("INVALID_DATA", "Invalid lecture");
        }
    }

    public LectureDTO getLectureById(long lectureId) {
        Optional<Lecture> lectureOptional = lectureRepository.findById(lectureId);

        if (lectureOptional.isPresent()) {
            Lecture lecture = lectureOptional.get();
            return createLectureDTO(lecture);
        } else {
            return null;
        }
    }

    // create lecture
    public ResponseDTO createLecture(CreateLectureRequestDTO lectureRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        Lecture lecture = new Lecture();

        String errorMessage = validateLectureInputs(lectureRequestDTO);
        if (errorMessage != null) {
            responseDTO.setMessage(errorMessage);
            responseDTO.setStatus("INVALID_INPUTS");
        } else {
            lecture.setCourseId(lectureRequestDTO.getCourseId());
            lecture.setStartTime(lectureRequestDTO.getStartTime());
            lecture.setEndTime(lectureRequestDTO.getEndTime());
            lecture.setExpectedAttendance(lectureRequestDTO.getExpectedAttendance());

            Hall hall = hallService.getHallById(lectureRequestDTO.getHallId());
            User lecturer = userService.getUserByMosipID(Long.toString(lectureRequestDTO.getLecturerId())).get();

            lecture.setHall(hall);
            lecture.setLecturer(lecturer);

            lectureRepository.save(lecture);

            responseDTO.setData(lecture.getId());
            responseDTO.setMessage("Lecture created successfully!");
            responseDTO.setStatus("LECTURE_CREATED_SUCCESSFULLY");
        }

        return responseDTO;
    }

    private String validateLectureInputs(CreateLectureRequestDTO lectureRequestDTO) {
        String message = null;

        LocalDateTime startTime = lectureRequestDTO.getStartTime();
        LocalDateTime endTime = lectureRequestDTO.getEndTime();

        Hall hall = hallService.getHallById(lectureRequestDTO.getHallId());
        Optional<User> responseDTOLecturer = userService.getUserByMosipID(Long.toString(lectureRequestDTO.getLecturerId()));

        if (startTime == null || endTime == null) {
            message = "Cannot be empty";
        } else if (startTime.isAfter(endTime)) {
            message = "Start time should be earlier than End time";
        } else if (responseDTOLecturer.isEmpty()) {
            message = "Invalid lecturer id";
        } else if (hall == null) {
            message = "Invalid hall id";
        } else if (!hallService.isHallAvailable(hall, startTime, endTime)) {
            message = "Hall unavailable";
        }
        return message;
    }

    public List<LectureDTO> getAllLectures(long userId) {
        List<Lecture> lectureList = lectureRepository.fetchLecturesByLecturer(userId);

        List<LectureDTO> lectureDTOList = new ArrayList<>();

        for (Lecture lecture : lectureList) {
            LectureDTO lectureDTO = createLectureDTO(lecture);
            lectureDTOList.add(lectureDTO);
        }

        return lectureDTOList;
    }

    public ResponseDTO updateLecture(LectureUpdateRequestDTO lectureUpdateRequestDTO) {
        ResponseDTO res = new ResponseDTO();

        Optional<Lecture> lectureOptional = lectureRepository.findById(lectureUpdateRequestDTO.getLectureId());

        if (lectureOptional.isEmpty()) {
            res.setMessage("Error occur when loading existing lecture data!");
            res.setStatus("LECTURE_NOT_FOUND");
        } else {
            Lecture lecture = lectureOptional.get();
            lecture.setId(lectureUpdateRequestDTO.getLectureId());
            lecture.setCourseId(lectureUpdateRequestDTO.getCourseId());
            lecture.setEndTime(lectureUpdateRequestDTO.getEndTime());
            lecture.setStartTime(lectureUpdateRequestDTO.getStartTime());
            lecture.setStarted(lectureUpdateRequestDTO.isStarted());
            lecture.setEnded(lectureUpdateRequestDTO.isEnded());
            lecture.setExpectedAttendance(lectureUpdateRequestDTO.getExpectedAttendance());
            lecture.setAttendance(lectureUpdateRequestDTO.getAttendance());
            lectureRepository.save(lecture);
            res.setMessage("Updated lecture details successfully!");
            res.setStatus("LECTURE_UPDATED_SUCCESSFULLY");
        }
        return res;
    }

    public ResponseDTO deleteLectureByID(long lectureID) {

        ResponseDTO responseDTO = new ResponseDTO();

        if (lectureRepository.findById(lectureID).isEmpty()) {
            responseDTO.setMessage("No Lecture found corresponding to the lecture ID!");
            responseDTO.setStatus("LECTURE_NOT_FOUND");
        } else {
            lectureRepository.deleteById(lectureID);
            responseDTO.setMessage("Deleted lecture successfully!");
            responseDTO.setStatus("LECTURE_DELETED_SUCCESSFULLY");
        }
        return responseDTO;
    }

    private LectureDTO createLectureDTO(Lecture lecture) {
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setId(lecture.getId());
        lectureDTO.setCourse(lmsService.getCourseByID(Long.toString(lecture.getCourseId())));
        lectureDTO.setStartTime(lecture.getStartTime());
        lectureDTO.setEndTime(lecture.getEndTime());
        lectureDTO.setStarted(lecture.isStarted());
        lectureDTO.setEnded(lecture.isEnded());
        lectureDTO.setExpectedAttendance(lecture.getExpectedAttendance());
        lectureDTO.setAttendance(lecture.getAttendance());
        lectureDTO.setHall(lecture.getHall());
        lectureDTO.setLecturer(lecture.getLecturer());
        lectureDTO.setAttendees(lecture.getAttendees());

        return lectureDTO;
    }

}
