package dev.safeceylon.SafeCeylon.repo;

import dev.safeceylon.SafeCeylon.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ShelterRepo extends JpaRepository<Shelter, Long> {

}
