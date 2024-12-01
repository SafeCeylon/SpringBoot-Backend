package dev.safeceylon.SafeCeylon.DisasterVictim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface DisasterVictimRepository extends JpaRepository<DisasterVictim, String> {

    List<DisasterVictim> id(String id);

    List<DisasterVictim> findByIdDisaster(String idDisaster);

    // findByStatus
    List<DisasterVictim> findByvictimStatus(VictimStatus victimStatus);
}
