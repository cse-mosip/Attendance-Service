package uom.mosip.attendanceservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "exams")
@Setter
@Getter
@NoArgsConstructor
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 8, nullable = false)
    private String moduleCode;

    @Column(nullable = false)
    private String moduleName;

    @Column(nullable = false)
    private int intake;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private int expectedAttendance;

    @Column(nullable = false)
    private int attendance;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invigilator_id", nullable = false)
    private User invigilator;

    @OneToMany(mappedBy = "exam", fetch = FetchType.LAZY)
    private List<ExamAttendance> attendees;

}
