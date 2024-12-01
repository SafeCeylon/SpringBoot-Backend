package dev.safeceylon.SafeCeylon.user;

import dev.safeceylon.SafeCeylon.disastermanagement.Disaster;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //GET /api/users

    @GetMapping("")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        Optional<User> user = userRepository.findUserById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    //POST /api/users

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void createUser(@RequestBody User user) {
        userRepository.save(user);
    }


    //PUT /api/users

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateUser(@RequestBody User user, @PathVariable String id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        user.setId(id); // Ensure the ID is set for the entity being saved
        userRepository.save(user); // Save will update if the entity exists
    }
    
    //DELETE /api/users

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Transactional
    public void deleteUser(@PathVariable String id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.deleteById(id); // deleteById is provided by JpaRepository
    }

    @GetMapping("/disaster")
    public List<User> getDisasterAdminsAndOfficers() {
        return userRepository.findByRoleIn(Arrays.asList(UserRole.DISASTER_ADMIN, UserRole.DISASTER_OFFICER));
    }
}
