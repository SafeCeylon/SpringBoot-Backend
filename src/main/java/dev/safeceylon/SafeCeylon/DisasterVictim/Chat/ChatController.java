package dev.safeceylon.SafeCeylon.DisasterVictim.Chat;


import dev.safeceylon.SafeCeylon.DisasterVictim.TableCreationService;
import dev.safeceylon.SafeCeylon.DisasterVictim.Chat.ChatMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class ChatController {

    private final TableCreationService tableCreationService;
    private final ChatService chatService;

    @Autowired
    public ChatController(TableCreationService tableCreationService, ChatService chatService) {
        this.tableCreationService = tableCreationService;
        this.chatService = chatService;
    }

    @PostMapping("/createTable")
    public String createTable(@RequestParam String tableName) {
        try {
            tableCreationService.createTable(tableName);
            return "Table '" + tableName + "' created successfully!";
        } catch (IllegalArgumentException e) {
            return "Invalid table name: " + e.getMessage();
        } catch (Exception e) {
            return "Failed to create table: " + e.getMessage();
        }
    }

    @GetMapping("/fetchMessages")
    public List<ChatMessage> fetchMessages(@RequestParam String tableName) {
        return chatService.getMessagesFromVictimChat(tableName);
    }

    @PostMapping("/addMessage")
    public String addMessage(@RequestParam String tableName, @RequestBody ChatMessage message) {
        chatService.addMessageToVictimChat(tableName, message.getText(), ChatMessageOwner.VICTIM);
        return "Message added to chat table: " + tableName;
    }
}
