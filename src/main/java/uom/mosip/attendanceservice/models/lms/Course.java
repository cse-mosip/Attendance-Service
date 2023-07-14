package uom.mosip.attendanceservice.models.lms;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "lms_courses")
@Setter
@Getter
@NoArgsConstructor
public class Course {
    @Id
    private String moduleCode;

    private int intake;

    @ManyToMany
    @JoinTable(
            name = "lms_enrollments",
            joinColumns = @JoinColumn(name = "module_code"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> enrolledStudents;
}
