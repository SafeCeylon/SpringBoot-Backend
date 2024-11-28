package dev.safeceylon.SafeCeylon.disastermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisasterService {
    private final DisasterRepository disasterRepository;

    @Autowired
    public DisasterService(DisasterRepository disasterRepository) {
        this.disasterRepository = disasterRepository;
    }

    public Disaster saveDisaster(Disaster disaster) {
        return disasterRepository.save(disaster);
    }

    public List<Disaster> getAllDisasters() {
        return disasterRepository.findAll();
    }

    public Disaster updateDisaster(String id, Disaster disaster) {
//        Disaster existingDisaster = disasterRepository.findById(id).orElseThrow(
//                () -> new IllegalAccessException("Disaster not found")
//        );
//        existingDisaster.setDisasterType(disaster.getDisasterType());
//        existingDisaster.setLatitude(disaster.getLatitude());
//        existingDisaster.setLongitude(disaster.getLongitude());
//        existingDisaster.setRadius(disaster.getRadius());
//        existingDisaster.setResolved(disaster.isResolved());
//
//        return disasterRepository.save(existingDisaster);
        return null;

    }

    public void deleteDisaster(String id) {
//        disasterRepository.deleteById(id);
    }

}
