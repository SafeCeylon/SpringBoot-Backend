package dev.safeceylon.SafeCeylon.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
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

    // @GetMapping("/{id}")
    // public User getUserById(@PathVariable Integer id) {
    //     Optional<User> user = userRepository.findUserById(id);
    //     if(user.isPresent()) {
    //         return user.get();
    //     } else {
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    //     }
    // }

    // //POST /api/users

    // @ResponseStatus(HttpStatus.CREATED)
    // @PostMapping("")
    // void createUser(@RequestBody User user) {
    //     userRepository.create(user);
    // }


    // //PUT /api/users

    // @ResponseStatus(HttpStatus.NO_CONTENT)
    // @PutMapping("/{id}")
    // void updateUser(@RequestBody User user, @PathVariable Integer id) {
    //     userRepository.update(user, id);
    // }
    
    // //DELETE /api/users

    // @ResponseStatus(HttpStatus.NO_CONTENT)
    // @DeleteMapping("/{id}")
    // void deleteUser(@PathVariable Integer id) {
    //     userRepository.delete(id);
    // }
}
