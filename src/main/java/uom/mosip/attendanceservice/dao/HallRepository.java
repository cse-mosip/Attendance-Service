package uom.mosip.attendanceservice.dao;

import org.springframework.data.repository.CrudRepository;
import uom.mosip.attendanceservice.models.Hall;

public interface HallRepository extends CrudRepository<Hall,Long> {
}
