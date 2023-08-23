package adilet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "department_gen"
    )
    @SequenceGenerator(
            name = "department_gen",
            sequenceName = "department_seq",
            allocationSize = 1
    )
    private Long id;
    private String name;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Hospital hospital;
    @ManyToMany(mappedBy = "departments",
            cascade = CascadeType.ALL
    )
    private List<Doctor> doctors;
    @OneToMany(mappedBy = "department",
            cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private List<Appointment> appointments;
   @Transient
   private Long hospitalId;

    public Department(String name) {
        this.name = name;
    }

    public void addDoctor(Doctor doctor) {
        if (doctors != null){
            doctors = new ArrayList<>();
        } else doctors.add(doctor);
    }
}
