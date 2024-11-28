package dev.safeceylon.SafeCeylon.disastermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disasters")
@CrossOrigin(origins = "*")
public class DisasterController {
    private final DisasterService disasterService;

    @Autowired
    public DisasterController(DisasterService disasterService) {
        this.disasterService = disasterService;
    }

    @PostMapping
    public ResponseEntity<Disaster> saveDisaster(@RequestBody Disaster disaster) {
        Disaster savedDisaster = disasterService.saveDisaster(disaster);
        return ResponseEntity.ok(savedDisaster);
    }

    @GetMapping
    public ResponseEntity<List<Disaster>> getAllDisasters() {
        List<Disaster> disasters = disasterService.getAllDisasters();
        return ResponseEntity.ok(disasters);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disaster> updateDisaster(@PathVariable String id, @RequestBody Disaster disaster) throws IllegalAccessException {
        Disaster updateDisaster = disasterService.updateDisaster(id, disaster);
        return ResponseEntity.ok(updateDisaster);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Disaster> deleteDisaster(@PathVariable String id) {
        disasterService.deleteDisaster(id);
        return ResponseEntity.noContent().build();
    }
}
