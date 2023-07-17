package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.LectureRepository;
import uom.mosip.attendanceservice.dto.LectureUpdateRequestDTO;
import uom.mosip.attendanceservice.dto.LectureDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.models.*;

import java.time.LocalDateTime;
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
                .orElseThrow(() -> new IllegalArgumentException("examId: " + lectureId + "was not found!"));
    }

    // create lecture
    public ResponseDTO createLecture(LectureDTO lectureDTO) {

        ResponseDTO responseDTO = new ResponseDTO();
        Lecture lecture = new Lecture();

        String errorMessage = validateLectureInputs(lectureDTO);
        if (errorMessage != null) {
            responseDTO.setMessage(errorMessage);
            responseDTO.setStatus("INVALID_INPUTS");
        } else {
            lecture.setModuleCode(lectureDTO.getModuleCode());
            lecture.setModuleName(lectureDTO.getModuleName());
            lecture.setIntake(lectureDTO.getIntake());
            lecture.setStartTime(lectureDTO.getStartTime());
            lecture.setEndTime(lectureDTO.getEndTime());
            lecture.setStarted(lectureDTO.isStarted());
            lecture.setEnded(lectureDTO.isEnded());
            lecture.setExpectedAttendance(lectureDTO.getExpectedAttendance());
            lecture.setAttendance(lectureDTO.getAttendance());
            lecture.setHall(lectureDTO.getHall());
            lecture.setLecturer(lectureDTO.getLecturer());

            lectureRepository.save(lecture);

            responseDTO.setData(lectureDTO);
            responseDTO.setMessage("Lecture created successfully!");
            responseDTO.setStatus("LECTURE_CREATED_SUCCESSFULLY");
        }

        return responseDTO;
    }

    //create lecture validations
    private String validateLectureInputs(LectureDTO lectureDTO) {
        String message = null;

        String moduleCode = lectureDTO.getModuleCode();
        String moduleName = lectureDTO.getModuleName();
        int intake = lectureDTO.getIntake();
        LocalDateTime startTime = lectureDTO.getStartTime();
        LocalDateTime endTime = lectureDTO.getEndTime();
        boolean isStarted = lectureDTO.isStarted();
        boolean isEnded = lectureDTO.isEnded();
        int expectedAttendance = lectureDTO.getExpectedAttendance();
        int attendance = lectureDTO.getAttendance();
        Hall hall = lectureDTO.getHall();
        User lecturer = lectureDTO.getLecturer();
        List<LectureAttendance> attendees = lectureDTO.getAttendees();

        if (moduleCode==null || moduleName==null || !Optional.ofNullable(intake).isPresent() || startTime.equals(null) || endTime.equals(null) || !Optional.ofNullable(isStarted).isPresent() || !Optional.ofNullable(isEnded).isPresent() ||
                !Optional.ofNullable(expectedAttendance).isPresent() || !Optional.ofNullable(attendance).isPresent() || hall==null || lecturer==null || attendees.isEmpty()){
            message = "Cannot be empty";
        } else if (lectureDTO.getEndTime().compareTo(lectureDTO.getStartTime()) <= 0) {
            message = "Start time should be earlier than End time";
        } else if (lectureDTO.getExpectedAttendance() <= 0) {
            message = "Expected attendance should be greater than zero";
        } else if (lectureDTO.getAttendance() > lectureDTO.getExpectedAttendance()){
            message = "Attendance should not be greater than expected attendance";
        }

        return message;
    }

    public ResponseDTO getAllLectures() {
        List<Lecture> lectureList = (List<Lecture>) lectureRepository.findAll();
        return new ResponseDTO("OK", "All lectures fetched successfully", lectureList);
    }

    public ResponseDTO updateLecture(LectureUpdateRequestDTO lectureUpdateRequestDTO) {
        ResponseDTO res = new ResponseDTO();
        if (lectureRepository.findById(lectureUpdateRequestDTO.getLectureId()).isEmpty()) {
            res.setMessage("Error occur when loading existing lecture data!");
            res.setStatus("LECTURE_NOT_FOUND");
        } else {
            Lecture lecture = new Lecture();
            lecture.setId(lectureUpdateRequestDTO.getLectureId());
            lecture.setModuleCode(lectureUpdateRequestDTO.getModuleCode());
            lecture.setModuleName(lectureUpdateRequestDTO.getModuleName());
            lecture.setIntake(lectureUpdateRequestDTO.getIntake());
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

}
