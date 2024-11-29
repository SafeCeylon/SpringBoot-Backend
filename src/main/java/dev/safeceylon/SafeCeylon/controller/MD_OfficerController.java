package dev.safeceylon.SafeCeylon.controller;


import dev.safeceylon.SafeCeylon.dto.MD_OfficerDTO;
import dev.safeceylon.SafeCeylon.service.interfac.MD_OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/mdofficer")

public class MD_OfficerController {

    @Autowired
    private MD_OfficerService md_officerService;


    @PostMapping("/save")
    public String saveMdofficer(@RequestBody MD_OfficerDTO md_officerDTO) {
        md_officerService.saveMdofficer(md_officerDTO);
        return "SAVED";
    }

    @PutMapping("/update")
    public String updateMdofficer(@RequestBody  MD_OfficerDTO md_officerDTO) {
        md_officerService.updateMdofficer(md_officerDTO);
        return "UPDATED";
    }

    @DeleteMapping(
            path="/delete-officer/{empNumber}"
    )
    public String deleteMdofficer(@PathVariable Long empNumber) {
        md_officerService.deleteMdofficer(empNumber);
        return "DELETED";
    }

    @GetMapping(
            path = "/get-all-officers"
    )

    public List<MD_OfficerDTO> getAllOfficers(){
        List<MD_OfficerDTO> allOfficers = md_officerService.getAllOfficers();
        return allOfficers;
    }

}
