package dev.safeceylon.SafeCeylon.DisasterVictim.Chat;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService {

    // Get the chat messages for the given victim
    // Dynamically get the table name for the victim
    // Get the chat messages from the table

    @Autowired
    private EntityManager entityManager;

//    @Transactional
//    public Map<String, String> getChatMessages(String IdVictim) {
//        String tableName = "chat_dmc_" + IdVictim.replace("-", "_");
//
//        // Get the chat messages from the table
//        String TableQuery = "SELECT * FROM " + tableName;
//        // Execute the query and get the chat messages
//        entityManager.createNativeQuery(TableQuery).getResultList();
//        System.out.println("Chat messages for the victim with ID: " + IdVictim + " retrieved successfully!");
//        System.out.println("Chat messages: " + entityManager.createNativeQuery(TableQuery).getResultList());
//        return null;
//    }

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

    @Transactional
    public void addChatMessageByOwner(String IdVictim, String message, ChatMessageOwner owner) {
        String tableName = "chat_dmc_" + IdVictim.replace("-", "_");

        // Insert the chat message into the table
        String TableQuery = "INSERT INTO " + tableName + " (owner, message) VALUES ('" + owner.toString() + "', '" + message + "')";
        entityManager.createNativeQuery(TableQuery).executeUpdate();
        System.out.println("Chat message added successfully!");
    }
}
