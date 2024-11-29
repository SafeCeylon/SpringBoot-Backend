package dev.safeceylon.SafeCeylon.service;

import dev.safeceylon.SafeCeylon.dto.DisasterDTO;
import dev.safeceylon.SafeCeylon.entity.Disaster;
import dev.safeceylon.SafeCeylon.entity.Shelter;
import dev.safeceylon.SafeCeylon.repo.DisasterRepo;
import dev.safeceylon.SafeCeylon.service.interfac.DisasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DisasterServiceIMPL implements DisasterService {

    @Autowired
    private DisasterRepo disasterRepo;


    @Override
    public String saveDisaster(DisasterDTO disasterDTO) {

        Disaster disaster = new Disaster(

                disasterDTO.getDisasterId(),
                disasterDTO.getDisasterType(),
                disasterDTO.getLocation(),
                disasterDTO.getLevel(),
                disasterDTO.getReportedDate(),
                disasterDTO.getStartedDate(),
                disasterDTO.getExpectedEndDate()
        );
        disasterRepo.save(disaster);
        return "Successfully saved Disaster";
    }

    @Override
    public String updateDisaster(DisasterDTO disasterDTO) {

        if (disasterRepo.existsById(disasterDTO.getDisasterId())) {

            Disaster disaster = disasterRepo.getReferenceById(disasterDTO.getDisasterId());

            disaster.setDisasterType(disasterDTO.getDisasterType());
            disaster.setLocation(disasterDTO.getLocation());
            disaster.setLevel(disasterDTO.getLevel());
            disaster.setReportedDate(disasterDTO.getReportedDate());
            disaster.setStartedDate(disasterDTO.getStartedDate());
            disaster.setExpectedEndDate(disasterDTO.getExpectedEndDate());


            disasterRepo.save(disaster);

            return disasterDTO.getDisasterId() + " Update Success";
        } else {

            throw new RuntimeException("No data found");
        }

    }
}
