package uom.mosip.attendanceservice.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uom.mosip.attendanceservice.models.Exam;

import java.util.Optional;

public interface ExamRepository extends CrudRepository<Exam,Long> {

    @Query("SELECT e FROM Exam e LEFT JOIN FETCH e.attendees WHERE e.id=?1")
    Optional<Exam> fetchExamAttendanceById(long examId);

}
