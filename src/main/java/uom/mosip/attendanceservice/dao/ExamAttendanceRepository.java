package uom.mosip.attendanceservice.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uom.mosip.attendanceservice.models.Exam;
import uom.mosip.attendanceservice.models.ExamAttendance;

import java.util.List;

public interface ExamAttendanceRepository extends CrudRepository<ExamAttendance, Long> {
    @Query("SELECT ea FROM ExamAttendance ea WHERE ea.studentId=?1 AND ea.exam=?2")
    List<ExamAttendance> getExamAttendanceByStudentIdAndExam(String studentId, Exam exam);
}
