package dev.safeceylon.SafeCeylon.service.interfac;

import dev.safeceylon.SafeCeylon.dto.ShelterDTO;

import java.util.List;


public interface ShelterService  {
    String saveSheleter(ShelterDTO shelterDTO);

    String updateSheleter(ShelterDTO shelterDTO);

    List<ShelterDTO> getAllShelters();
}
