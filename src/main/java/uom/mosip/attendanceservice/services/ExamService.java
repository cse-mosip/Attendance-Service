package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.ExamRepository;
import uom.mosip.attendanceservice.models.Exam;

import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    public Optional<Exam> getAttendanceForAnExamById(long examId) {
        return examRepository.fetchExamAttendanceById(examId);
    }

    public Iterable<Exam> getAllExams(){
        return examRepository.findAll();
    }

    public Exam getExamById(long examId){
        return examRepository.findById(examId)
                .orElseThrow(()->new IllegalArgumentException
                        ("examId: " + examId + "was not found!"));
    }
}
