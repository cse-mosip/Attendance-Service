package uom.mosip.attendanceservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "lecture_attendance")
@Setter
@Getter
@NoArgsConstructor
public class LectureAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lecture_id", nullable = false)
    private Lecture lecture;

}
