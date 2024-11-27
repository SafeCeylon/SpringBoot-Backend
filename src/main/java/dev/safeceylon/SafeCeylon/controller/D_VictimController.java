package dev.safeceylon.SafeCeylon.controller;


import dev.safeceylon.SafeCeylon.dto.D_VictimDTO;
import dev.safeceylon.SafeCeylon.service.interfac.D_VictimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Dvictim")
@CrossOrigin
public class D_VictimController {

    @Autowired
    private D_VictimService d_victimService;

    @PostMapping("/save")
    public String saveDvictim(@RequestBody D_VictimDTO d_victimDTO) {
        d_victimService.saveDvictim(d_victimDTO);
        return "SAVED";
    }


    @PutMapping("/update")
    public String updateDvictim(@RequestBody  D_VictimDTO d_victimDTO) {
        d_victimService.updateDvictim(d_victimDTO);
        return "UPDATED";
    }

    @GetMapping("/getAll")
    public List<D_VictimDTO> getAllDvictims() {
        return d_victimService.getAllDvictims();
    }

}
