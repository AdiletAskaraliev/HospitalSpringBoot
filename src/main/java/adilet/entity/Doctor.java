package adilet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "doctor_gen"
    )
    @SequenceGenerator(
            name = "doctor_gen",
            sequenceName = "doctor_seq",
            allocationSize = 1,
            initialValue = 2
    )
    private Long id;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    private String position;
    private String email;
    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private Hospital hospital;
    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.REMOVE
    })
    private List<Department> departments;
    @OneToMany(mappedBy = "doctor",
            cascade = CascadeType.ALL
    )
    private List<Appointment> appointments;

    public void setDepartment(Department department) {
        if (departments != null){
            departments = new ArrayList<>();
        } else {
            departments.add(department);
        }
    }
}
