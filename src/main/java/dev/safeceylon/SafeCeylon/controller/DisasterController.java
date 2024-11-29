package dev.safeceylon.SafeCeylon.controller;


import dev.safeceylon.SafeCeylon.dto.DisasterDTO;
import dev.safeceylon.SafeCeylon.dto.ShelterDTO;
import dev.safeceylon.SafeCeylon.service.interfac.DisasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/Disaster")
@CrossOrigin
public class DisasterController {

    @Autowired
    private DisasterService disasterService;


    @PostMapping("/save")
    public String saveDisaster(@RequestBody DisasterDTO disasterDTO) {
        disasterService.saveDisaster(disasterDTO);
        return "SAVED";
    }


    @PutMapping("/update")
    public String updateDisaster(@RequestBody DisasterDTO disasterDTO) {
        disasterService.updateDisaster(disasterDTO);
        return "UPDATED";
    }
}
