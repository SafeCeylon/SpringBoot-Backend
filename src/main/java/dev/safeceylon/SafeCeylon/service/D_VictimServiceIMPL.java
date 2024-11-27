package dev.safeceylon.SafeCeylon.service;


import dev.safeceylon.SafeCeylon.dto.D_VictimDTO;
import dev.safeceylon.SafeCeylon.entity.D_Victim;
import dev.safeceylon.SafeCeylon.repo.D_VictimRepo;
import dev.safeceylon.SafeCeylon.service.interfac.D_VictimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class D_VictimServiceIMPL implements D_VictimService {

    @Autowired
    private D_VictimRepo d_VictimRepo;

    @Override
    public String saveDvictim(D_VictimDTO  d_VictimDTO) {

        D_Victim d_Victim = new D_Victim (


                d_VictimDTO.getDvId(),
                d_VictimDTO.getDisasterType(),
                d_VictimDTO.getReportedDate()
        );
        d_VictimRepo.save(d_Victim);
        return "Saved";

    }
}
