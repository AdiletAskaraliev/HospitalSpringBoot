package adilet.service;

import adilet.entity.Hospital;

import java.util.List;

public interface HospitalService {
    void saveHospital(Hospital hospital);

    List<Hospital> getAllHospital();

    Hospital findById(Long id);

    void updateHospital(Long id, Hospital hospital);

    void deleteHospital(Long id);
}
