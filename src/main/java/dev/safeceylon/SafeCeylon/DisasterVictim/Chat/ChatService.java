package dev.safeceylon.SafeCeylon.DisasterVictim.Chat;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService {

    @Autowired
    private EntityManager entityManager;

    // Get the chat messages for the given victim dynamically by constructing the table name
    public List<Map<String, Object>> getChatMessages(String IdVictim) {
        String tableName = "chat_dmc_" + IdVictim.replace("-", "_");

        // Query to retrieve chat messages
        String query = "SELECT * FROM " + tableName;

        try {
            // Execute query and retrieve results
            List<Object[]> resultList = entityManager.createNativeQuery(query).getResultList();

            // Get metadata about columns (assuming column order is id, owner, message)
            List<Map<String, Object>> chatMessages = new ArrayList<>();

            for (Object[] row : resultList) {
                Map<String, Object> messageMap = new HashMap<>();
                messageMap.put("id", row[0]);
                messageMap.put("owner", row[1]);
                messageMap.put("message", row[2]);
                chatMessages.add(messageMap);
            }

            System.out.println("Chat messages for the victim with ID: " + IdVictim + " retrieved successfully!");
            return chatMessages;

        } catch (Exception e) {
            System.err.println("Failed to retrieve chat messages for table: " + tableName);
            e.printStackTrace();
            return Collections.emptyList(); // Return an empty list in case of errors
        }
    }

    // Add a chat message by owner to the victim's chat
    @Transactional
    public void addChatMessageByOwner(String IdVictim, String message, ChatMessageOwner owner) {
        String tableName = "chat_dmc_" + IdVictim.replace("-", "_");

        // Insert the chat message into the table
        String insertQuery = "INSERT INTO " + tableName + " (owner, message) VALUES ('" + owner.toString() + "', '" + message + "')";
        entityManager.createNativeQuery(insertQuery).executeUpdate();
        System.out.println("Chat message added successfully!");
    }

    // Alternative method for getting messages using the ChatMessage entity class
    public List<ChatMessage> getMessagesFromVictimChat(String IdVictim) {
        String tableName = "chat_dmc_" + IdVictim.replace("-", "_");
        String query = "SELECT * FROM " + tableName;
        return entityManager.createNativeQuery(query, ChatMessage.class).getResultList();
    }

    // Alternative method for adding messages using the ChatMessage entity class
    @Transactional
    public void addMessageToVictimChat(String IdVictim, String message, ChatMessageOwner owner) {
        String tableName = "chat_dmc_" + IdVictim.replace("-", "_");
        String insertQuery = "INSERT INTO " + tableName + " (owner, message) VALUES ('" + owner.toString() + "', '" + message + "')";
        entityManager.createNativeQuery(insertQuery).executeUpdate();
    }
}
