package adilet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hospitals")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "hospital_gen"
    )
    @SequenceGenerator(
            name = "hospital_gen",
            sequenceName = "hospital_seq",
            allocationSize = 1
    )
    private Long id;
    private String name;
    private String address;
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Patient> patients;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Department> departments;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    public Hospital(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void addDepartment(Department department) {
        if (departments == null){
            departments = new ArrayList<>();
        } else {
            departments.add(department);
        }
    }

    public void addDoctor(Doctor doctor) {
        if (doctors == null){
            doctors = new ArrayList<>();
        } else {
            doctors.add(doctor);
        }
    }


}
