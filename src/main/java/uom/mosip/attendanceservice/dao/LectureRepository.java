package uom.mosip.attendanceservice.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uom.mosip.attendanceservice.models.Lecture;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureRepository extends CrudRepository<Lecture, Long> {
    @Query("SELECT l FROM Lecture l WHERE l.lecturer=?1")
    List<Lecture> fetchLecturesByLecturer(long userId);

    @Query("SELECT l FROM Lecture l WHERE l.endTime >= ?1 OR l.startTime <= ?3")
    List<Lecture> fetchLecturesByStartTimeAndEndTime(LocalDateTime startTime, LocalDateTime endTime);
}
