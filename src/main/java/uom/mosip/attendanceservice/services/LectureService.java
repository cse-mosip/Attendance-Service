package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.LectureRepository;
import uom.mosip.attendanceservice.dto.LectureDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.models.Exam;
import uom.mosip.attendanceservice.models.Lecture;

import java.util.List;
import java.util.Optional;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
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

    public Lecture getLectureById(long lectureId) {
        return lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException
                        ("examId: " + lectureId + "was not found!"));
    }

    // create lecture
    public ResponseDTO createLecture(LectureDTO lectureDTO){
        ResponseDTO responseDTO = new ResponseDTO();

        if (lectureRepository.findById(lectureDTO.getId()).isEmpty()){
            responseDTO.setMessage("");
            responseDTO.setStatus("");
        } else {
            Lecture createdLecture = lectureRepository.findById(lectureDTO.getId()).get();
            String errorMessage = validateLectureInputs(lectureDTO);
            if (errorMessage != null){
                responseDTO.setMessage(errorMessage);
                responseDTO.setStatus("INVALID_INPUTS");
            } else {
                createdLecture.setId(lectureDTO.getId());
                createdLecture.setModuleCode(lectureDTO.getModuleCode());
                createdLecture.setModuleName(lectureDTO.getModuleName());
                createdLecture.setIntake(lectureDTO.getIntake());
                createdLecture.setStartTime(lectureDTO.getStartTime());
                createdLecture.setEndTime(lectureDTO.getEndTime());
                createdLecture.setStarted(lectureDTO.isStarted());
                createdLecture.setEnded(lectureDTO.isEnded());
                createdLecture.setExpectedAttendance(lectureDTO.getExpectedAttendance());
                createdLecture.setAttendance(lectureDTO.getAttendance());
                createdLecture.setHall(lectureDTO.getHall());
                createdLecture.setLecturer(lectureDTO.getLecturer());

                createdLecture = lectureRepository.save(createdLecture);

                responseDTO.setData(lectureDTO);
                responseDTO.setMessage("Lecture created successfully!");
                responseDTO.setMessage("LECTURE_CREATED_SUCCESSFULLY");
            }
        }

        return responseDTO;
    }

    private String validateLectureInputs(LectureDTO lectureDTO){
        String message = null;

        if(lectureDTO.getEndTime().compareTo(lectureDTO.getStartTime()) <= 0){
            message = "Start time should be earlier than End time";
        } else if (lectureDTO.getExpectedAttendance() <= 0){
            message = "Expected attendance should be greater than zero";
        }

        return message;
    }

    public ResponseDTO getAllLectures() {
        List<Lecture> lectureList = (List<Lecture>) lectureRepository.findAll();
        return new ResponseDTO("OK", "All lectures fetched successfully", lectureList);
    }

}
