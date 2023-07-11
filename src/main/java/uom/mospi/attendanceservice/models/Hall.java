package uom.mospi.attendanceservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "halls")
@Setter
@Getter
@NoArgsConstructor
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private int capacity;

    @OneToMany(mappedBy = "hall", fetch = FetchType.LAZY)
    private List<Lecture> lectures;

    @OneToMany(mappedBy = "hall", fetch = FetchType.LAZY)
    private List<Lecture> exams;

}
