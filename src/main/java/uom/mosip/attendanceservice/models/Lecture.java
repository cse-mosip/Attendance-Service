package uom.mosip.attendanceservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "lectures")
@Setter
@Getter
@NoArgsConstructor
public class Lecture {

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

    @Column(columnDefinition = "boolean default false")
    private boolean isStarted;

    @Column(columnDefinition = "boolean default false")
    private boolean isEnded;

    @Column(nullable = false)
    private int expectedAttendance;

    @Column(nullable = false)
    private int attendance;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lecturer_id", nullable = false)
    private User lecturer;

    @OneToMany(mappedBy = "lecture", fetch = FetchType.LAZY)
    private List<LectureAttendance> attendees;
}
