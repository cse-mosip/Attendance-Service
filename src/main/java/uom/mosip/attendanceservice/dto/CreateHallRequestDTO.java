package uom.mosip.attendanceservice.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateHallRequestDTO {
    private long id;
    private String name;
    private String location;
    private int capacity;
}
