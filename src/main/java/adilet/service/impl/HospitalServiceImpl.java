package adilet.service.impl;

import adilet.entity.Hospital;
import adilet.repository.HospitalRepo;
import adilet.service.HospitalService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepo hospitalRepo;

    @Override
    public void saveHospital(Hospital hospital) {
        hospitalRepo.save(hospital);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalRepo.findAll();
    }

    @Override
    public Hospital findById(Long id) {
        return hospitalRepo.findById(id).orElseThrow(
                ()-> new NullPointerException(
                        "User with Id : " + id + " is not found"
                )
        );
    }

    @Override
    public void updateHospital(Long id, Hospital hospital) {
        Hospital hospital1 = findById(id);
        hospital1.setName(hospital.getName());
        hospital1.setAddress(hospital.getAddress());

        hospitalRepo.save(hospital1);
    }

    @Override
    public void deleteHospital(Long id) {
        if (hospitalRepo.existsById(id)){
            hospitalRepo.deleteById(id);
        } else throw new NullPointerException( "User with id : " + id + " doesn't exists!");

    }

}
