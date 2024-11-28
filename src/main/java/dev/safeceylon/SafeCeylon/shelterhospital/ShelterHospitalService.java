package dev.safeceylon.SafeCeylon.shelterhospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelterHospitalService {
    private final ShelterRepository shelterRepository;
    private final HospitalRepository hospitalRepository;

    @Autowired
    public ShelterHospitalService(ShelterRepository shelterRepository, HospitalRepository hospitalRepository) {
        this.shelterRepository = shelterRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public Shelter saveShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    public Hospital saveHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll(); // Assuming JPA repository
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll(); // Assuming JPA repository
    }

    // Find shelter by ID
    public Shelter findShelterById(String id) {
        return shelterRepository.findById(id).orElse(null);
    }

    // Find hospital by ID
    public Hospital findHospitalById(String id) {
        return hospitalRepository.findById(id).orElse(null);
    }

    public Shelter updateShelter(String id, ShelterHospitalRequest request) {
        return shelterRepository.findById(id).map(shelter -> {
            shelter.setType(request.getType());
            shelter.setName(request.getName());
            shelter.setLatitude(request.getLatitude());
            shelter.setLongitude(request.getLongitude());
            shelter.setCapacity(request.getCapacity());
            shelter.setContact(request.getContact());
            return shelterRepository.save(shelter);
        }).orElse(null);
    }

    public Hospital updateHospital(String id, ShelterHospitalRequest request) {
        return hospitalRepository.findById(id).map(hospital -> {
            hospital.setType(request.getType());
            hospital.setName(request.getName());
            hospital.setLatitude(request.getLatitude());
            hospital.setLongitude(request.getLongitude());
            hospital.setContact(request.getContact());
            return hospitalRepository.save(hospital);
        }).orElse(null);
    }

    public boolean deleteShelter(String id) {
        if (shelterRepository.existsById(id)) {
            shelterRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteHospital(String id) {
        if (hospitalRepository.existsById(id)) {
            hospitalRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
