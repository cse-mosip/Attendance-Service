package uom.mosip.attendanceservice.services;

import org.springframework.stereotype.Component;
import uom.mosip.attendanceservice.models.User;

@Component
public interface UserService {
    public User saveUser(User user) throws Exception;
}
