package dev.safeceylon.SafeCeylon.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import jakarta.annotation.PostConstruct;

@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();

    List<User> findAll() {
        return users;
    }

    Optional<User> findUserById(Integer id) {
        return users.stream()
            .filter(user -> user.id().equals(id))
            .findFirst();
    }

    void create (User user) {
        users.add(user);
    }

    void update (User user, Integer id) {
        Optional<User> existingUser = findUserById(id);
        if(existingUser.isPresent()) {
            users.set(users.indexOf(existingUser.get()), user);
        }
    }

    void delete (Integer id) {
        users.removeIf(user -> user.id().equals(id));
    }

    @PostConstruct
    void init() {
        users.add(new User(
            0,
            "John Doe",
            "1234567890",
            "0712345678",
            "johndoe@gmail.com",
            "123, Main Street",
            "Colombo",
            "Colombo 07",
            "password",
            null));

        users.add(new User(
            1,
            "Jane Doe",
            "1234567891",
            "0712345679",
            "janedoe@gmail.com",
            "124, Main Street",
            "Colombo",
            "Colombo 07",
            "password",
            null));

        users.add(new User(
            2,
            "John Smith",
            "1234567892",
            "0712345680",
            "johnsmith@gmail.com",
            "125, Main Street",
            "Colombo",
            "Colombo 07",
            "password",
            null));
    }
}
