package dev.safeceylon.SafeCeylon.service.interfac;

import dev.safeceylon.SafeCeylon.dto.MD_OfficerDTO;

import java.util.List;

public interface MD_OfficerService {
    String saveMdofficer(MD_OfficerDTO mdOfficerDTO);

    String updateMdofficer(MD_OfficerDTO mdOfficerDTO);

    String deleteMdofficer(Long empNumber);

    List<MD_OfficerDTO> getAllOfficers();
}
