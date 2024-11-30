package dev.safeceylon.SafeCeylon.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll(); // This method is inherited from JpaRepository
    Optional<User> findUserById(String id);
    boolean existsById(String id);
    void deleteById(String id);
    Optional<User> findUserByEmail(String email);
}
