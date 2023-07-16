package uom.mosip.attendanceservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    private boolean isActive = true;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private int capacity;

    @JsonManagedReference
    @OneToMany(mappedBy = "hall", fetch = FetchType.LAZY)
    private List<Lecture> lectures;

    @OneToMany(mappedBy = "hall", fetch = FetchType.LAZY)
    private List<Lecture> exams;

}
