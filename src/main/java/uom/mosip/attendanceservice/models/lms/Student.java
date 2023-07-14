package uom.mosip.attendanceservice.models.lms;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "lms_students")
@Setter
@Getter
@NoArgsConstructor
public class Student {
    @Id
    private String studentId;

    @ManyToMany(mappedBy = "enrolledStudents")
    private List<Course> enrolledCourses;
}
