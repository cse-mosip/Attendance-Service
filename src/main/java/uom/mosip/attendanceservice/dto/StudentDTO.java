package uom.mosip.attendanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uom.mosip.attendanceservice.dto.rs.RSStudentDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String index;
    private String email;
    private String firstName;
    private String lastName;
    private String faculty;
    private String department;

    public StudentDTO(RSStudentDTO rsStudentDTO) {
        this.index = rsStudentDTO.index;
        this.email = rsStudentDTO.email;
        this.firstName = rsStudentDTO.firstName;
        this.lastName = rsStudentDTO.lastName;
        this.faculty = rsStudentDTO.faculty;
        this.department = rsStudentDTO.department;
    }
}
