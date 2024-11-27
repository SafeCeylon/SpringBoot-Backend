package dev.safeceylon.SafeCeylon.service;

import dev.safeceylon.SafeCeylon.dto.D_VictimDTO;
import dev.safeceylon.SafeCeylon.entity.D_Victim;
import dev.safeceylon.SafeCeylon.entity.Shelter;
import dev.safeceylon.SafeCeylon.repo.D_VictimRepo;
import dev.safeceylon.SafeCeylon.repo.ShelterRepo; // Assuming ShelterRepo exists for fetching shelter entities
import dev.safeceylon.SafeCeylon.service.interfac.D_VictimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class D_VictimServiceIMPL implements D_VictimService {

    @Autowired
    private D_VictimRepo d_VictimRepo;

    @Autowired
    private ShelterRepo shelterRepo;

    @Override
    public String saveDvictim(D_VictimDTO d_VictimDTO) {

        Shelter shelter = shelterRepo.findById(d_VictimDTO.getShelterId())
                .orElseThrow(() -> new RuntimeException("Shelter not found with ID: " + d_VictimDTO.getShelterId()));

        // Map DTO to entity
        D_Victim dVictim = new D_Victim();
        dVictim.setDisasterType(d_VictimDTO.getDisasterType());
        dVictim.setReportedDate(d_VictimDTO.getReportedDate());
        dVictim.setShelter(shelter);

        // Save entity to the database
        d_VictimRepo.save(dVictim);

        return "D_Victim successfully saved";
    }

    @Override
    public String updateDvictim(D_VictimDTO d_VictimDTO) {

            // Find the existing D_Victim entity by its ID
            D_Victim existingDVictim = d_VictimRepo.findById(d_VictimDTO.getDvId())
                    .orElseThrow(() -> new RuntimeException("D_Victim not found with ID: " + d_VictimDTO.getDvId()));

            // Retrieve the associated Shelter entity using shelterId from DTO
            Shelter shelter = shelterRepo.findById(d_VictimDTO.getShelterId())
                    .orElseThrow(() -> new RuntimeException("Shelter not found with ID: " + d_VictimDTO.getShelterId()));

            // Update fields of the existing D_Victim entity
            existingDVictim.setDisasterType(d_VictimDTO.getDisasterType());
            existingDVictim.setReportedDate(d_VictimDTO.getReportedDate());
            existingDVictim.setShelter(shelter);

            // Save the updated entity
            d_VictimRepo.save(existingDVictim);

            return "D_Victim successfully updated";
        }

    @Override
    public List<D_VictimDTO> getAllDvictims() {
        // Retrieve all D_Victim entities from the database
        List<D_Victim> dVictims = d_VictimRepo.findAll();

        // Map entities to DTOs
        return dVictims.stream().map(dVictim -> {
            D_VictimDTO dto = new D_VictimDTO();
            dto.setDvId(dVictim.getDvId());
            dto.setDisasterType(dVictim.getDisasterType());
            dto.setReportedDate(dVictim.getReportedDate());
            dto.setShelterId(dVictim.getShelter().getShelterId()); // Assuming Shelter has getShelterId()
            return dto;
        }).toList();
    }

}

