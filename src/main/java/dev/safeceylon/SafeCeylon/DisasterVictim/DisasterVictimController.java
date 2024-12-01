package dev.safeceylon.SafeCeylon.DisasterVictim;

import dev.safeceylon.SafeCeylon.disastermanagement.Disaster;
import dev.safeceylon.SafeCeylon.user.User;
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


    @GetMapping("/chat-ToReply")
    public Object getVictimsToReplyChatAndDetails(@RequestParam(value = "UserId", required = false) String UserId){
        if(UserId == null){
            List<User> victimsToReply =disasterVictimService.GetVictimUsersOfStatus(VictimStatus.ToReply);
            return victimsToReply;  //
        }else {
            System.out.println("userId: " + UserId + "reponce"); // Ensure it's logged
            return disasterVictimService.GetDisasterByVictimId(UserId);
        }
    }









}
