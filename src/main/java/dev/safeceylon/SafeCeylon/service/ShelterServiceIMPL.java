package dev.safeceylon.SafeCeylon.service;


import dev.safeceylon.SafeCeylon.dto.ShelterDTO;
import dev.safeceylon.SafeCeylon.repo.ShelterRepo;
import dev.safeceylon.SafeCeylon.service.interfac.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShelterServiceIMPL implements ShelterService {

    @Autowired
    private ShelterRepo shelterRepo;


    @Override
    public String saveSheleter(ShelterDTO shelterDTO) {
        return "";
    }
}
