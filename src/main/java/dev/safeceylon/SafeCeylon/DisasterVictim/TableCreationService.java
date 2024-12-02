package dev.safeceylon.SafeCeylon.DisasterVictim;

import dev.safeceylon.SafeCeylon.DisasterVictim.Chat.ChatMessageOwner;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableCreationService {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void createTable(String tableName) {
        String createTableQuery = "CREATE TABLE " + tableName + " (" +
                "id BIGSERIAL PRIMARY KEY, " +
                "owner VARCHAR(255), " +
                "message VARCHAR(255)" +
                ")";
        entityManager.createNativeQuery(createTableQuery).executeUpdate();

        ChatMessageOwner owner = ChatMessageOwner.DMC_OFFICER;
        String message = "Welcome to the chat!";
        String insertQuery = "INSERT INTO " + tableName + " (owner, message) VALUES (:owner, :message)";
        entityManager.createNativeQuery(insertQuery)
                .setParameter("owner", owner.toString())
                .setParameter("message", message)
                .executeUpdate();
    }



}
