package dev.safeceylon.SafeCeylon.service.interfac;

import dev.safeceylon.SafeCeylon.dto.DisasterDTO;

public interface DisasterService {
    String saveDisaster(DisasterDTO disasterDTO);

    String updateDisaster(DisasterDTO disasterDTO);
}
