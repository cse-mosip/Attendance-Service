package uom.mosip.attendanceservice.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uom.mosip.attendanceservice.models.Hall;

public interface HallRepository extends CrudRepository<Hall,Long> {

    Hall findByName(String name);

    @Query(value = "SELECT MAX(expected_attendance) AS max_expected_attendance " +
            "FROM (SELECT expected_attendance FROM lectures WHERE hall_id = :hallId AND end_time > NOW() " +
            "         UNION ALL SELECT expected_attendance FROM exams " +
            "        WHERE hall_id = :hallId AND end_time > NOW()) AS combined_tables", nativeQuery = true)
    Integer findMaxExpectedAttendanceAssignedForSelectedHall(@Param("hallId") long hallId);
}
