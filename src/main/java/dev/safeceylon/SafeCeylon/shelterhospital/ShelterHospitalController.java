package dev.safeceylon.SafeCeylon.shelterhospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/shelters-hospitals")
@CrossOrigin(origins = "*")
public class ShelterHospitalController {
    private final ShelterHospitalService service;

    @Autowired
    public ShelterHospitalController(ShelterHospitalService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> saveEntity(@RequestBody ShelterHospitalRequest request) {
        if ("shelter".equalsIgnoreCase(request.getType())) {
            Shelter shelter = new Shelter(
                    request.getType(),
                    request.getName(),
                    request.getLatitude(),
                    request.getLongitude(),
                    request.getCapacity(),
                    request.getContact()
            );
            return ResponseEntity.ok(service.saveShelter(shelter));
        } else if ("hospital".equalsIgnoreCase(request.getType())) {
            Hospital hospital = new Hospital(
                    request.getType(),
                    request.getName(),
                    request.getLatitude(),
                    request.getLongitude(),
                    request.getContact()
            );
            return ResponseEntity.ok(service.saveHospital(hospital));
        } else {
            return ResponseEntity.badRequest().body("Invalid type. Must be 'shelter' or 'hospital'.");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEntities() {
        List<Shelter> shelters = service.getAllShelters();
        List<Hospital> hospitals = service.getAllHospitals();

        List<Object> mergedEntities = new ArrayList<>();
        mergedEntities.addAll(shelters);
        mergedEntities.addAll(hospitals);

        return ResponseEntity.ok(mergedEntities);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEntity(@PathVariable String id, @RequestBody ShelterHospitalRequest request) {
        // Check in Shelter table
        Shelter existingShelter = service.findShelterById(id);
        if (existingShelter != null) {
            Shelter updatedShelter = service.updateShelter(id, request);
            if (updatedShelter != null) {
                return ResponseEntity.ok(updatedShelter);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update Shelter.");
            }
        }

        // Check in Hospital table
        Hospital existingHospital = service.findHospitalById(id);
        if (existingHospital != null) {
            Hospital updatedHospital = service.updateHospital(id, request);
            if (updatedHospital != null) {
                return ResponseEntity.ok(updatedHospital);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update Hospital.");
            }
        }

        // If ID is not found in either table
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntity(@PathVariable String id) {
        // Check in Shelter table
        if (service.findShelterById(id) != null) {
            if (service.deleteShelter(id)) {
                return ResponseEntity.ok("Shelter deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Shelter.");
            }
        }

        // Check in Hospital table
        if (service.findHospitalById(id) != null) {
            if (service.deleteHospital(id)) {
                return ResponseEntity.ok("Hospital deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Hospital.");
            }
        }

        // If ID is not found in either table
        return ResponseEntity.notFound().build();
    }




}
