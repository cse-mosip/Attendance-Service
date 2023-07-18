package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.ExamRepository;
import uom.mosip.attendanceservice.dto.ExamDTO;
import uom.mosip.attendanceservice.dto.GetExamsRequestDTO;
import uom.mosip.attendanceservice.models.Exam;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private LMSService lmsService;

    public Optional<Exam> getAttendanceForAnExamById(long examId) {
        return examRepository.fetchExamAttendanceById(examId);
    }

    public List<ExamDTO> getAllExams(long userId, GetExamsRequestDTO examsRequestDTO) {
        List<Exam> examList;
        List<ExamDTO> examDTOList = new ArrayList<>();

        if (examsRequestDTO.getMinTime() == null && examsRequestDTO.getMaxTime() == null) {
            examList = examRepository.fetchExamsByInvigilator(userId);
        } else if (examsRequestDTO.getMinTime() != null && examsRequestDTO.getMaxTime() == null) {
            examList = examRepository.fetchExamsByInvigilatorAndMinTime(userId, Date.from(Instant.from(examsRequestDTO.getMinTime())));
        } else if (examsRequestDTO.getMinTime() == null) {
            examList = examRepository.fetchExamsByInvigilatorAndMaxTime(userId, Date.from(Instant.from(examsRequestDTO.getMaxTime())));
        } else {
            examList = examRepository.fetchExamsByInvigilatorAndMinTimeAndMaxTime(userId, Date.from(Instant.from(examsRequestDTO.getMinTime())), Date.from(Instant.from(examsRequestDTO.getMaxTime())));
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
        examDTO.setLecturerName(exam.getInvigilator().getName());
        examDTO.setAttendees(exam.getAttendees());

        return examDTO;
    }

    public List<Exam> getExamsInTimePeriod(Date startTime, Date endTime) {
        return examRepository.fetchExamsByStartTimeAndEndTime(startTime, endTime);
    }

}
