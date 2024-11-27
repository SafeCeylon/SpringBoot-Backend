package dev.safeceylon.SafeCeylon.service.interfac;

import dev.safeceylon.SafeCeylon.dto.D_VictimDTO;

import java.util.List;

public interface D_VictimService {

    
    String saveDvictim(D_VictimDTO dVictimDTO);

    String updateDvictim(D_VictimDTO dVictimDTO);

    List<D_VictimDTO> getAllDvictims();
}
