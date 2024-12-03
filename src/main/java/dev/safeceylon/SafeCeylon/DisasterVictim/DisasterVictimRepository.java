package dev.safeceylon.SafeCeylon.DisasterVictim;

import dev.safeceylon.SafeCeylon.disastermanagement.Disaster;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    // search all entries in the disaster victim table by the victim id and set the status to replied
    @Modifying
    @Transactional
    @Query("UPDATE DisasterVictim dv SET dv.victimStatus = :status WHERE dv.idVictim = :idVictim")
    void updateVictimStatus(@Param("idVictim") String idVictim, @Param("status") VictimStatus status);

    float countByVictimStatus(VictimStatus victimStatus);
}

