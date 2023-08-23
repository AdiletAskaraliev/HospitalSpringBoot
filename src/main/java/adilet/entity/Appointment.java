package adilet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "appointment_gen"
    )
    @SequenceGenerator(
            name = "appointment_gen",
            sequenceName = "appointment_seq",
            allocationSize = 1
    )
    private Long id;
    private LocalDate date;
    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private Patient patient;
    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private Doctor doctor;
    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private Department department;
    @ManyToOne
    private Hospital hospital;
}
