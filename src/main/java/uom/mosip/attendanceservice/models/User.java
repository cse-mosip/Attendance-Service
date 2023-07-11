package uom.mosip.attendanceservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int userType;

    @OneToMany(mappedBy = "lecturer", fetch = FetchType.LAZY)
    private List<Lecture> lectures;

    @OneToMany(mappedBy = "invigilator", fetch = FetchType.LAZY)
    private List<Exam> exams;

}
