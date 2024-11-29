package dev.safeceylon.SafeCeylon.service;


import dev.safeceylon.SafeCeylon.dto.MD_OfficerDTO;
import dev.safeceylon.SafeCeylon.dto.ShelterDTO;
import dev.safeceylon.SafeCeylon.entity.MD_Officer;
import dev.safeceylon.SafeCeylon.entity.Shelter;
import dev.safeceylon.SafeCeylon.repo.ShelterRepo;
import dev.safeceylon.SafeCeylon.service.interfac.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShelterServiceIMPL implements ShelterService {

    @Autowired
    private ShelterRepo shelterRepo;


    @Override
    public String saveSheleter(ShelterDTO shelterDTO) {
        Shelter shelter = new Shelter(

                shelterDTO.getShelterId(),
                shelterDTO.getLocation(),
                shelterDTO.getContactNumber(),
                shelterDTO.getCapacity()
        );
        shelterRepo.save(shelter);
        return "Successfully Saved";

    }

    @Override
    public String updateSheleter(ShelterDTO shelterDTO) {

        if (shelterRepo.existsById(shelterDTO.getShelterId())) {

            Shelter shelter = shelterRepo.getReferenceById(shelterDTO.getShelterId());

            shelter.setLocation(shelterDTO.getLocation());
            shelter.setContactNumber(shelterDTO.getContactNumber());
            shelter.setCapacity(shelterDTO.getCapacity());

            shelterRepo.save(shelter);

            return shelterDTO.getShelterId() + " Update Success";
        } else {

            throw new RuntimeException("No data found");
        }
    }

    @Override
    public List<ShelterDTO> getAllShelters() {

        List<Shelter> getAllShelters = shelterRepo.findAll();
        List<ShelterDTO> shelterDTOList = new ArrayList<>();

        for(Shelter shelter : getAllShelters) {

            ShelterDTO shelterDTO = new ShelterDTO (

                    shelter.getShelterId(),
                    shelter.getLocation(),
                    shelter.getContactNumber(),
                    shelter.getCapacity()
            );
            shelterDTOList.add(shelterDTO);
        }
        return shelterDTOList;
    }
}
