package adilet.entity;

import adilet.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "patient_gen"
    )
    @SequenceGenerator(
            name = "patient_gen",
            sequenceName = "patient_seq",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "phone_number")
    private String phoneNumber;
    private Gender gender;
    private String email;
    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private Hospital hospital;
    @OneToMany(mappedBy = "patient",
            cascade = CascadeType.ALL
    )
    private List<Appointment> appointments;
}
