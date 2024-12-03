package dev.safeceylon.SafeCeylon.DisasterVictim;

import dev.safeceylon.SafeCeylon.DisasterVictim.Chat.ChatData;
import dev.safeceylon.SafeCeylon.DisasterVictim.Chat.ChatMessageOwner;
import dev.safeceylon.SafeCeylon.DisasterVictim.Chat.ChatService;
import dev.safeceylon.SafeCeylon.disastermanagement.Disaster;
import dev.safeceylon.SafeCeylon.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/disaster/disaster-victims")
@CrossOrigin(origins = "*")
public class DisasterVictimController {

    private final DisasterVictimService disasterVictimService;
    private final TableCreationService tableCreationService;

    private final ChatService chatService;


    @Autowired
    public DisasterVictimController(DisasterVictimService disasterVictimService, TableCreationService tableCreationService, ChatService chatService) {
        this.disasterVictimService = disasterVictimService;
        this.tableCreationService = tableCreationService;
        this.chatService = chatService;
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

            // create a new table for the chat
            // in User_id replace the "-" with "_"
            String newUserId = UserId.replace("-", "_");

            String tableName = "chat_dmc_" + newUserId;
            try {
                tableCreationService.createTable(tableName);
                System.out.println("Table created successfully");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid table name: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Failed to create table: " + e.getMessage());
            }

            User user = disasterVictimService.GetUserByVictimId(UserId);
            System.out.println("User: " + user.getName());
            List<Disaster> disasterList = disasterVictimService.GetDisasterByVictimId(UserId);
            System.out.println("Disasters: " + disasterList);
            List<Map<String, Object>> Chatmasages = chatService.getChatMessages(UserId);
            System.out.println("Chat messages: " + Chatmasages);


            ChatData chatData = new ChatData(user.getName(), disasterList, Chatmasages);

            return chatData;

        }
    }

    @PostMapping("/chat-ToReply")
    public ResponseEntity<String> sendMessage(@RequestBody Map<String, String> payload){
        String message = payload.get("message");
        String UserId = payload.get("UserId");
        if (message == null || message.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Message cannot be empty");
        }
        // Handle the message (e.g., save to database or broadcast to users)
        System.out.println("Received message: " + message);
        System.out.println("Sending message to the user ---> " + UserId);
        chatService.addChatMessageByOwner(UserId, message, ChatMessageOwner.DMC_OFFICER);

        return ResponseEntity.ok("Message sent successfully");
    }








}
