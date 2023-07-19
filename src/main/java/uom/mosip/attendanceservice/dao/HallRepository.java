package uom.mosip.attendanceservice.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uom.mosip.attendanceservice.models.Hall;

import java.time.LocalDateTime;

public interface HallRepository extends CrudRepository<Hall,Long> {
    @Query(value = "SELECT halls.id, halls.capacity, halls.is_active, halls.location, halls.name from halls " +
            "WHERE Halls.id NOT IN  " +
            "(SELECT halls.id FROM halls " +
            "LEFT JOIN exams ON halls.id = exams.hall_id " +
            "LEFT JOIN lectures ON halls.id = lectures.hall_id " +
            "WHERE ( " +
            "        ((exams.start_time < ?2  AND exams.start_time >= ?1) " +
                        "OR ( exams.end_time <= ?2 AND exams.end_time > ?1)) " +
            "        OR " +
            "        ((lectures.start_time < ?2  AND lectures.start_time >= ?1) "+
                        "OR ( lectures.end_time <= ?2 AND lectures.end_time > ?1)) " +
            "      ));", nativeQuery = true)
    Iterable<Hall> findByTime(LocalDateTime startTime, LocalDateTime endTime);


    Hall findByName(String name);

    @Query(value = "SELECT MAX(expected_attendance) AS max_expected_attendance " +
            "FROM (SELECT expected_attendance FROM lectures WHERE hall_id = :hallId AND end_time > NOW() " +
            "         UNION ALL SELECT expected_attendance FROM exams " +
            "        WHERE hall_id = :hallId AND end_time > NOW()) AS combined_tables", nativeQuery = true)
    Integer findMaxExpectedAttendanceAssignedForSelectedHall(@Param("hallId") long hallId);
}
