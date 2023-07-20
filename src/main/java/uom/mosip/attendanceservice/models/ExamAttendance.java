package uom.mosip.attendanceservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "exam_attendance")
@Setter
@Getter
@NoArgsConstructor
public class ExamAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    private LocalDateTime markedTime;

    @Column(columnDefinition = "boolean default false")
    private boolean isValidated;

    private LocalDateTime validatedTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

}
