package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.ExamRepository;
import uom.mosip.attendanceservice.dto.CreateExamRequestDTO;
import uom.mosip.attendanceservice.dto.ExamDTO;
import uom.mosip.attendanceservice.dto.GetExamsRequestDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.models.Exam;
import uom.mosip.attendanceservice.models.Hall;
import uom.mosip.attendanceservice.models.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private LMSService lmsService;

    @Autowired
    private UserService userService;

    @Autowired
    private HallService hallService;

    public Optional<Exam> getAttendanceForAnExamById(long examId) {
        return examRepository.fetchExamAttendanceById(examId);
    }

    public List<ExamDTO> getAllExams(long userId, GetExamsRequestDTO examsRequestDTO) {
        List<Exam> examList;
        List<ExamDTO> examDTOList = new ArrayList<>();

        if (examsRequestDTO.getMinTime() == null && examsRequestDTO.getMaxTime() == null) {
            examList = examRepository.fetchExamsByInvigilator(userId);
        } else if (examsRequestDTO.getMinTime() != null && examsRequestDTO.getMaxTime() == null) {
            examList = examRepository.fetchExamsByInvigilatorAndMinTime(userId, examsRequestDTO.getMinTime());
        } else if (examsRequestDTO.getMinTime() == null) {
            examList = examRepository.fetchExamsByInvigilatorAndMaxTime(userId, examsRequestDTO.getMaxTime());
        } else {
            examList = examRepository.fetchExamsByInvigilatorAndMinTimeAndMaxTime(userId, examsRequestDTO.getMinTime(), examsRequestDTO.getMaxTime());
        }

        for (Exam exam : examList) {
            ExamDTO examDTO = createExamDTO(exam);
            examDTOList.add(examDTO);
        }

        return examDTOList;
    }

    public ExamDTO getExamById(long examId) {
        Optional<Exam> examOptional = examRepository.findById(examId);

        if (examOptional.isPresent()) {
            Exam exam = examOptional.get();
            return createExamDTO(exam);
        } else {
            return null;
        }
    }

    private ExamDTO createExamDTO(Exam exam) {
        ExamDTO examDTO = new ExamDTO();
        examDTO.setId(exam.getId());
        examDTO.setCourse(lmsService.getCourseByID(Long.toString(exam.getCourseId())));
        examDTO.setStartTime(exam.getStartTime());
        examDTO.setEndTime(exam.getEndTime());
        examDTO.setStarted(exam.isStarted());
        examDTO.setEnded(exam.isEnded());
        examDTO.setExpectedAttendance(exam.getExpectedAttendance());
        examDTO.setAttendance(exam.getAttendance());
        examDTO.setHall(exam.getHall());
        examDTO.setInvigilatorName(exam.getInvigilator().getName());

        return examDTO;
    }

    public ResponseDTO createExam(CreateExamRequestDTO examRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        Exam exam = new Exam();

        String errorMessage = validateExamInputs(examRequestDTO);
        if (errorMessage != null) {
            responseDTO.setMessage(errorMessage);
            responseDTO.setStatus("INVALID_INPUTS");
        } else {
            exam.setCourseId(examRequestDTO.getCourseId());
            exam.setStartTime(examRequestDTO.getStartTime());
            exam.setEndTime(examRequestDTO.getEndTime());
            exam.setExpectedAttendance(examRequestDTO.getExpectedAttendance());

            Hall hall = hallService.getHallById(examRequestDTO.getHallId());
            User invigilator = userService.getUserByID(examRequestDTO.getInvigilatorId()).get();

            exam.setHall(hall);
            exam.setInvigilator(invigilator);

            examRepository.save(exam);

            responseDTO.setData(exam.getId());
            responseDTO.setMessage("Exam created successfully!");
            responseDTO.setStatus("EXAM_CREATED_SUCCESSFULLY");
        }

        return responseDTO;
    }

    private String validateExamInputs(CreateExamRequestDTO examRequestDTO) {
        String message = null;

        LocalDateTime startTime = examRequestDTO.getStartTime();
        LocalDateTime endTime = examRequestDTO.getEndTime();

        Hall hall = hallService.getHallById(examRequestDTO.getHallId());
        Optional<User> responseDTOExam = userService.getUserByID(examRequestDTO.getInvigilatorId());

        if (startTime == null || endTime == null) {
            message = "Cannot be empty";
        } else if (startTime.isAfter(endTime)) {
            message = "Start time should be earlier than End time";
        } else if (responseDTOExam.isEmpty()) {
            message = "Invalid lecturer id";
        } else if (hall == null) {
            message = "Invalid hall id";
        } else if (!hallService.isHallAvailable(hall, startTime, endTime)) {
            message = "Hall unavailable";
        }
        return message;
    }
}
