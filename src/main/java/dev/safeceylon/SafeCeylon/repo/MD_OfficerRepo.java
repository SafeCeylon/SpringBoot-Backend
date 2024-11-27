package dev.safeceylon.SafeCeylon.repo;

import dev.safeceylon.SafeCeylon.entity.MD_Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface MD_OfficerRepo extends JpaRepository<MD_Officer, Long> {

}
