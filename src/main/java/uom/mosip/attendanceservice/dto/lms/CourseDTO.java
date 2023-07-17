package uom.mosip.attendanceservice.dto.lms;

import lombok.Getter;
import lombok.Setter;
import uom.mosip.attendanceservice.models.lms.Course;

@Setter
@Getter
public class CourseDTO {
    private long id;
    private String moduleCode;
    private String moduleName;
    private int intake;

    public CourseDTO(Course c) {
        this.id = c.getId();
        this.moduleCode = c.getModuleCode();
        this.moduleName = c.getModuleName();
        this.intake = c.getIntake();
    }
}
