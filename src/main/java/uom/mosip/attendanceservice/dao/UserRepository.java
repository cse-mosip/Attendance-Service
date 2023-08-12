package uom.mosip.attendanceservice.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uom.mosip.attendanceservice.models.User;
import uom.mosip.attendanceservice.models.lms.Course;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByMosipId(String mosipId);

    @Query("SELECT c FROM User c")
    List<User> fetchAllUsers();
}
