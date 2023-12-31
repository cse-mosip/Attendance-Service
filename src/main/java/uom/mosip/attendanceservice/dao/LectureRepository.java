package uom.mosip.attendanceservice.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uom.mosip.attendanceservice.models.Lecture;

import java.util.List;

public interface LectureRepository extends CrudRepository<Lecture, Long> {
    @Query("SELECT l FROM Lecture l WHERE l.lecturer.id=?1")
    List<Lecture> fetchLecturesByLecturer(long userId);

    @Query("SELECT l FROM Lecture l WHERE l.lecturer.id=?1 AND l.isStarted=true AND l.isEnded=false")
    List<Lecture> fetchCurrentLecturesByLecturer(long userId);
}
