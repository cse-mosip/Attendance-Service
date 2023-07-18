package uom.mosip.attendanceservice.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uom.mosip.attendanceservice.models.Exam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ExamRepository extends CrudRepository<Exam, Long> {

    @Query("SELECT e FROM Exam e LEFT JOIN FETCH e.attendees WHERE e.id=?1")
    Optional<Exam> fetchExamAttendanceById(long examId);

    @Query("SELECT e FROM Exam e WHERE e.invigilator=?1")
    List<Exam> fetchExamsByInvigilator(long userId);

    @Query("SELECT e FROM Exam e WHERE e.invigilator=?1 AND e.endTime >= ?2")
    List<Exam> fetchExamsByInvigilatorAndMinTime(long userId, LocalDateTime minTime);

    @Query("SELECT e FROM Exam e WHERE e.invigilator=?1 AND e.startTime <= ?2")
    List<Exam> fetchExamsByInvigilatorAndMaxTime(long userId, LocalDateTime maxTime);

    @Query("SELECT e FROM Exam e WHERE e.invigilator=?1 AND (e.endTime >= ?2 OR e.startTime <= ?3)")
    List<Exam> fetchExamsByInvigilatorAndMinTimeAndMaxTime(long userId, LocalDateTime minTime, LocalDateTime maxTime);
}
