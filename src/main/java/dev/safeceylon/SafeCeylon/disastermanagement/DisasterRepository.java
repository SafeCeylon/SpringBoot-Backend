package dev.safeceylon.SafeCeylon.disastermanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisasterRepository extends JpaRepository<Disaster, String> {

    List<Disaster> id(String id);
}
