package dev.safeceylon.SafeCeylon.DisasterVictim;

import dev.safeceylon.SafeCeylon.user.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/disaster/disaster-victims")
@CrossOrigin(origins = "*")
public class DisasterVictimController {

    private final DisasterVictimService disasterVictimService;

    @Autowired
    public DisasterVictimController(DisasterVictimService disasterVictimService) {
        this.disasterVictimService = disasterVictimService;
    }

    @GetMapping
    public Map<String, Integer> getVictimCounts() {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("ToReply", disasterVictimService.getToReplyCount());
        counts.put("Replied", disasterVictimService.getRepliedCount());
        counts.put("Closed", disasterVictimService.getClosedCount());
        return counts;
    }


//    @GetMapping("/chat-ToReply")
    @GetMapping(value="/chat-ToReply")
    public List<User> getVictimsToReply(@RequestParam(value = "UserId", required = false) String UserId) {
        System.out.println("userId: " + UserId); // Ensure it's logged
        if(Objects.equals(UserId, "null")){
            List<User> victimsToReply =disasterVictimService.GetVictimUsersOfStatus(VictimStatus.ToReply);
            return victimsToReply;  // Ensure you're returning the correct data
        }else{
            List<User> victimsToReply =disasterVictimService.GetVictimUsersOfStatus(VictimStatus.ToReply);
            return victimsToReply;  // Ensure you're returning the correct data
        }
    }




}
