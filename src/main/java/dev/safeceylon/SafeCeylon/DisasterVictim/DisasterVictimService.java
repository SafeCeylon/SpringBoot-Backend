package dev.safeceylon.SafeCeylon.DisasterVictim;

import dev.safeceylon.SafeCeylon.disastermanagement.Disaster;
import dev.safeceylon.SafeCeylon.disastermanagement.DisasterRepository;
import dev.safeceylon.SafeCeylon.user.User;
import dev.safeceylon.SafeCeylon.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DisasterVictimService {
    private final DisasterVictimRepository disasterVictimRepository;
    private final UserRepository userRepository;

    private final DisasterRepository disasterRepository;

    public DisasterVictimService(DisasterVictimRepository disasterVictimRepository, UserRepository userRepository, DisasterRepository disasterRepository) {
        this.disasterVictimRepository = disasterVictimRepository;
        this.userRepository = userRepository;
        this.disasterRepository = disasterRepository;
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

        // Use a Set to ensure uniqueness
        Set<User> uniqueUsers = new HashSet<>();
        for (DisasterVictim victim : victimIds) {
            User user = userRepository.findUserById(victim.getIdVictim()).orElse(null);
            if (user != null) {
                uniqueUsers.add(user);
            }
        }

        // Convert Set back to List
        return new ArrayList<>(uniqueUsers);
    }


    public User GetVictimUser(String id) {
        return userRepository.findUserById(id).orElse(null);
    }

//    public Disaster<> GetDisasterByVictimId(String id) {
//        DisasterVictim victim = disasterVictimRepository.findByIdVictim(id).orElse(null);
//        if (victim != null) {
//            System.out.println("victim.getIdDisaster() ----->>: " + victim.getIdDisaster());
//            return disasterRepository.findById(victim.getIdDisaster()).orElse(null);
//        }
//        return null;
//    }

    public List<Disaster> GetDisasterByVictimId(String id) {
        List<DisasterVictim> victims = disasterVictimRepository.findByIdVictim(id);
        System.out.println("victims.size() ----->>: " + victims.size());
        List<Disaster> disasters = new ArrayList<Disaster>();
        for (DisasterVictim victim : victims) {
            disasters.add(disasterRepository.findById(victim.getIdDisaster()).orElse(null));
        }
        System.out.println("disasters.size() ----->>: " + disasters.size());
        return disasters;
    }

    public User GetUserByVictimId(String id) {
        return userRepository.findUserById(id).orElse(null);
    }
}
