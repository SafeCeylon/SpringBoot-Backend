package dev.safeceylon.SafeCeylon.service;


import dev.safeceylon.SafeCeylon.dto.MD_OfficerDTO;
import dev.safeceylon.SafeCeylon.entity.MD_Officer;
import dev.safeceylon.SafeCeylon.repo.MD_OfficerRepo;
import dev.safeceylon.SafeCeylon.service.interfac.MD_OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                //  md_OfficerDTO.getOfficerPhotoUrl()
        );
        md_OfficerRepo.save(md_Officer);
        return md_OfficerDTO.getFirstName() +  "Successfully Saved";
    }

    @Override
    public String updateMdofficer(MD_OfficerDTO mdOfficerDTO) {
        if (md_OfficerRepo.existsById(mdOfficerDTO.getEmpNumber())) {
            MD_Officer md_Officer = md_OfficerRepo.getReferenceById(mdOfficerDTO.getEmpNumber());
            md_Officer.setFirstName(mdOfficerDTO.getFirstName());
            md_Officer.setLastName(mdOfficerDTO.getLastName());
            md_Officer.setEmail(mdOfficerDTO.getEmail());
            md_Officer.setContactNumber(mdOfficerDTO.getContactNumber());
            md_Officer.setNicNumber(mdOfficerDTO.getNicNumber());
            // md_Officer.setOfficerPhotoUrl(mdOfficerDTO.getEmail());

            md_OfficerRepo.save(md_Officer);

            return mdOfficerDTO.getFirstName() + " Update Success";
        } else {

            throw new RuntimeException("No data found");
        }
    }

    @Override
    public String deleteMdofficer(Long empNumber) {
        if(md_OfficerRepo.existsById(empNumber)) {
            md_OfficerRepo.deleteById(empNumber);
            return "Successfully Deleted";
        }else {
            throw new RuntimeException("No data found");
        }
    }

    @Override
    public List<MD_OfficerDTO> getAllOfficers() {

        List<MD_Officer> getAllOfficers = md_OfficerRepo.findAll();
        List<MD_OfficerDTO> officerDTOList = new ArrayList<>();

        for(MD_Officer md_Officer : getAllOfficers) {

            MD_OfficerDTO md_officerDTO = new MD_OfficerDTO (

                    md_Officer.getEmpNumber(),
                    md_Officer.getNicNumber(),
                    md_Officer.getFirstName(),
                    md_Officer.getLastName(),
                    md_Officer.getEmail(),
                    md_Officer.getContactNumber()
                    // md_Officer.getOfficerPhotoUrl()
            );
            officerDTOList.add(md_officerDTO);
        }
        return officerDTOList;

    }


}


