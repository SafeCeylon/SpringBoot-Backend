package dev.safeceylon.SafeCeylon.controller;


import dev.safeceylon.SafeCeylon.dto.MD_OfficerDTO;
import dev.safeceylon.SafeCeylon.dto.ShelterDTO;
import dev.safeceylon.SafeCeylon.service.interfac.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/shelter")
@CrossOrigin
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    @PostMapping("/save")
    public String saveSheleter(@RequestBody ShelterDTO shelterDTO) {
        shelterService.saveSheleter(shelterDTO);
        return "SAVED";
    }


    @PutMapping("/update")
    public String updateShelter(@RequestBody ShelterDTO shelterDTO) {
        shelterService.updateSheleter(shelterDTO);
        return "UPDATED";
    }

    @GetMapping(
            path = "/get-all-shelters"
    )

    public List<ShelterDTO> getAllShelters(){
        List<ShelterDTO> allShelters = shelterService.getAllShelters();
        return allShelters;
    }
}
