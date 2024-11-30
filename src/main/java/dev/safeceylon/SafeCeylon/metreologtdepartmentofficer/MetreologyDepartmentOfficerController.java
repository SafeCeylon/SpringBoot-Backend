package dev.safeceylon.SafeCeylon.metreologtdepartmentofficer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
