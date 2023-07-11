package uom.mosip.attendanceservice.dao;

import org.springframework.data.repository.CrudRepository;
import uom.mosip.attendanceservice.models.User;

public interface UserRepository extends CrudRepository<User,Long> {
}
