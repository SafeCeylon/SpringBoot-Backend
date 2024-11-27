package dev.safeceylon.SafeCeylon.repo;


import dev.safeceylon.SafeCeylon.entity.D_Victim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface D_VictimRepo extends JpaRepository<D_Victim, Long> {
}
