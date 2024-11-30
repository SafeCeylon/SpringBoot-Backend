package dev.safeceylon.SafeCeylon.metreologtdepartmentofficer;

import dev.safeceylon.SafeCeylon.disastermanagement.Disaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetreologyDepartmentOfficerService {

    @Autowired
    private MetreologyDepartmentOfficerRepo metreologyDepartmentOfficerRepo;


    public MetreologyDepartmentOfficer saveMdofficer(MetreologyDepartmentOfficer metreologyDepartmentOfficer) {

        return metreologyDepartmentOfficerRepo.save(metreologyDepartmentOfficer);
    }

    public List<MetreologyDepartmentOfficer> getAllOfficers(){
        return metreologyDepartmentOfficerRepo.findAll();
    }


    public MetreologyDepartmentOfficer updateMdofficer(Long id, MetreologyDepartmentOfficer metreologyDepartmentOfficer) throws  IllegalAccessException {

        MetreologyDepartmentOfficer existingMdofficer = metreologyDepartmentOfficerRepo.findById(id).orElseThrow(
                () -> new IllegalAccessException("Disaster not found")
        );
        existingMdofficer.setEmpNumber(metreologyDepartmentOfficer.getEmpNumber());
        existingMdofficer.setNicNumber(metreologyDepartmentOfficer.getNicNumber());
        existingMdofficer.setFirstName(metreologyDepartmentOfficer.getFirstName());
        existingMdofficer.setLastName(metreologyDepartmentOfficer.getLastName());
        existingMdofficer.setEmail(metreologyDepartmentOfficer.getEmail());
        existingMdofficer.setContactNumber(metreologyDepartmentOfficer.getContactNumber());
        existingMdofficer.setOfficerPhotoUrl(metreologyDepartmentOfficer.getOfficerPhotoUrl());


        return metreologyDepartmentOfficerRepo.save(existingMdofficer);
    }

    public void deleteOfficer(Long id) {
        metreologyDepartmentOfficerRepo.deleteById(id);
    }
}
