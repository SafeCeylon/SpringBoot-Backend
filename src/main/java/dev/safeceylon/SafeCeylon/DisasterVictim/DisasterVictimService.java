package dev.safeceylon.SafeCeylon.DisasterVictim;

import dev.safeceylon.SafeCeylon.user.User;
import dev.safeceylon.SafeCeylon.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DisasterVictimService {
    private final DisasterVictimRepository disasterVictimRepository;
    private final UserRepository userRepository;

    public DisasterVictimService(DisasterVictimRepository disasterVictimRepository, UserRepository userRepository) {
        this.disasterVictimRepository = disasterVictimRepository;
        this.userRepository = userRepository;
    }

    public DisasterVictim SaveDisasterVictim(DisasterVictim disasterVictim) {
        return disasterVictimRepository.save(disasterVictim);
    }

    public List<DisasterVictim> GetAllDisasterVictims() {
        return disasterVictimRepository.findAll();
    }

    // Get all disaster victims for a specific disaster
    public List<DisasterVictim> GetDisasterVictimsByDisasterId(String idDisaster) {
        return disasterVictimRepository.findByIdDisaster(idDisaster);
    }

    public void DeleteDisasterVictim(String id) {
        disasterVictimRepository.deleteById(id);
    }

    // getToReplyCount
    public int getToReplyCount() {
        return disasterVictimRepository.findByvictimStatus(VictimStatus.ToReply).size();
    }

    // getRepliedCount
    public int getRepliedCount() {
        return disasterVictimRepository.findByvictimStatus(VictimStatus.Replied).size();
    }

    // getClosedCount
    public int getClosedCount() {
        return disasterVictimRepository.findByvictimStatus(VictimStatus.Closed).size();
    }

    public List<User> GetVictimUsersOfStatus(VictimStatus victimStatus) {
        List<DisasterVictim> victimIds = disasterVictimRepository.findByvictimStatus(victimStatus);

        List<User> users = new ArrayList<>();
        for (DisasterVictim victim : victimIds) {
            User user = userRepository.findUserById(victim.getIdVictim()).orElse(null);
            if (user != null) {
                users.add(user);
            }
        }
        return users;
    }

    public User GetVictimUser(String id) {
        return userRepository.findUserById(id).orElse(null);
    }
}
