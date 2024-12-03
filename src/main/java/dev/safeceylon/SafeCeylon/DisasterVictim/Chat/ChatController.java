package dev.safeceylon.SafeCeylon.DisasterVictim.Chat;

import dev.safeceylon.SafeCeylon.DisasterVictim.TableCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final TableCreationService tableCreationService;

    @Autowired
    public ChatController(TableCreationService tableCreationService) {
        this.tableCreationService = tableCreationService;
    }

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
}

