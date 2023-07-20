package uom.mosip.attendanceservice.models.lms;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(
        name = "lms_courses",
        uniqueConstraints = @UniqueConstraint(columnNames = {"module_code", "intake"})
)
@Setter
@Getter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "module_code", nullable = false, length = 8)
    private String moduleCode;

    @Column(nullable = false)
    private String moduleName;

    @Column(nullable = false)
    private int intake;

    @ManyToMany
    @JoinTable(
            name = "lms_enrollments",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )

    private List<Student> enrolledStudents;

}
