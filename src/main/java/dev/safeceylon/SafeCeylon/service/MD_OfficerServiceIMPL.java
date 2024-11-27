package dev.safeceylon.SafeCeylon.service;


import dev.safeceylon.SafeCeylon.dto.MD_OfficerDTO;
import dev.safeceylon.SafeCeylon.entity.MD_Officer;
import dev.safeceylon.SafeCeylon.repo.MD_OfficerRepo;
import dev.safeceylon.SafeCeylon.service.interfac.MD_OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MD_OfficerServiceIMPL implements MD_OfficerService {

    @Autowired
    private MD_OfficerRepo md_OfficerRepo;


    @Override
    public String saveMdofficer(MD_OfficerDTO md_OfficerDTO) {
        MD_Officer md_Officer = new MD_Officer(

                md_OfficerDTO.getEmpNumber(),
                md_OfficerDTO.getNicNumber(),
                md_OfficerDTO.getFirstName(),
                md_OfficerDTO.getLastName(),
                md_OfficerDTO.getEmail(),
                md_OfficerDTO.getContactNumber()
                //  mdOfficerDTO.getOfficerPhotoUrl()
        );
        md_OfficerRepo.save(md_Officer);
        return md_OfficerDTO.getFirstName() +  "Successfully Saved";
    }
}
