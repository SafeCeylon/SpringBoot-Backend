package dev.safeceylon.SafeCeylon.metreologtdepartmentofficer;
import dev.safeceylon.SafeCeylon.disastermanagement.Disaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mdofficer")
@CrossOrigin(origins = "*")

    public class MetreologyDepartmentOfficerController {

    @Autowired
    private MetreologyDepartmentOfficerService metreologyDepartmentOfficerService;

    @Autowired
    public MetreologyDepartmentOfficerController(MetreologyDepartmentOfficerService metreologyDepartmentOfficerService) {
        this.metreologyDepartmentOfficerService = metreologyDepartmentOfficerService;

    }

    @PostMapping("/save")
    public ResponseEntity<MetreologyDepartmentOfficer> saveMdofficer(@RequestBody MetreologyDepartmentOfficer metreologyDepartmentOfficer) {
        MetreologyDepartmentOfficer savedMdofficer = metreologyDepartmentOfficerService.saveMdofficer(metreologyDepartmentOfficer);
        return ResponseEntity.ok(savedMdofficer);
    }

    @GetMapping
    public ResponseEntity<List<MetreologyDepartmentOfficer>> getAllOfficers() {
        List<MetreologyDepartmentOfficer> metreologyDepartmentOfficers = metreologyDepartmentOfficerService.getAllOfficers();
        return ResponseEntity.ok(metreologyDepartmentOfficers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetreologyDepartmentOfficer> updateMdofficer(@PathVariable Long id, @RequestBody MetreologyDepartmentOfficer metreologyDepartmentOfficer) throws IllegalAccessException {
        MetreologyDepartmentOfficer updateMdofficer = metreologyDepartmentOfficerService.updateMdofficer(id, metreologyDepartmentOfficer);
        return ResponseEntity.ok(updateMdofficer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MetreologyDepartmentOfficer> deleteMdofficer(@PathVariable Long id) {
        metreologyDepartmentOfficerService.deleteOfficer(id);
        return ResponseEntity.noContent().build();
    }

}
