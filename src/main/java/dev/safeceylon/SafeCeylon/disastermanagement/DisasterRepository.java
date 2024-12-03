package dev.safeceylon.SafeCeylon.disastermanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisasterRepository extends JpaRepository<Disaster, String> {
    List<Disaster> id(String id);
    
    @Query("SELECT d FROM Disaster d WHERE d.reportedBy <> :reportedBy AND d.resolved = false")
    List<Disaster> findDisastersNotReportedBy(@Param("reportedBy") String reportedBy);

    @Query("SELECT d FROM Disaster d WHERE d.resolved = false")
    List<Disaster> findUnresolvedDisasters();
}
