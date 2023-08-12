package uom.mosip.attendanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uom.mosip.attendanceservice.models.User;
import uom.mosip.attendanceservice.models.lms.Course;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long id;
    private String email;
    private String name;
    private String mosipId;

    public UserDTO(User u) {
        this.id = u.getId();
        this.email = u.getEmail();
        this.name = u.getName();
        this.mosipId = u.getMosipId();
    }
}


