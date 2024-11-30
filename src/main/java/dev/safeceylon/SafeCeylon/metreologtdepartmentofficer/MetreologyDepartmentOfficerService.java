package dev.safeceylon.SafeCeylon.metreologtdepartmentofficer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetreologyDepartmentOfficerService {
    @Autowired
    private MetreologyDepartmentOfficerRepo metreologyDepartmentOfficerRepo;


    public MetreologyDepartmentOfficer saveMdofficer(MetreologyDepartmentOfficer metreologyDepartmentOfficer) {

        return metreologyDepartmentOfficerRepo.save(metreologyDepartmentOfficer);
    }
}
