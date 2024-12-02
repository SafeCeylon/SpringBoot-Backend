package dev.safeceylon.SafeCeylon.DisasterVictim;

import dev.safeceylon.SafeCeylon.disastermanagement.Disaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.safeceylon.SafeCeylon.disastermanagement.Disaster;

import java.util.List;
import java.util.Optional;


public interface DisasterVictimRepository extends JpaRepository<DisasterVictim, String> {

    List<DisasterVictim> id(String id);

    List<DisasterVictim> findByIdDisaster(String idDisaster);

    // findByStatus
    List<DisasterVictim> findByvictimStatus(VictimStatus victimStatus);

    @Query("SELECT d FROM Disaster d WHERE d.id NOT IN (SELECT dv.idDisaster FROM DisasterVictim dv WHERE dv.idVictim = :userId)")
    List<Disaster> findDisastersUserNotPartOf(@Param("userId") String userId);
    
    List<DisasterVictim> findByIdVictim(String idVictim);
    // find all dissters that assigned to a specific victim
    // join disaster table with disasterVictim table
    List<Disaster> findDisastersByIdVictim(String idVictim);


}

