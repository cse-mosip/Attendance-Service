package uom.mosip.attendanceservice.helpers;

import org.springframework.stereotype.Component;

@Component
public class UserTypeHelper {
    public String getUserTypeName(int userId) {
        if (userId == 1) return "ADMIN";
        else if (userId == 2) return "LECTURER";
        else return "";
    }
}
