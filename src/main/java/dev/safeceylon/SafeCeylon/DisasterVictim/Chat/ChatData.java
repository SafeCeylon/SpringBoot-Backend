package dev.safeceylon.SafeCeylon.DisasterVictim.Chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.safeceylon.SafeCeylon.disastermanagement.Disaster;

import java.util.List;
import java.util.Map;

public class ChatData {
    @JsonProperty("victim_name")
    private String victimName;
    @JsonProperty("disasters")
    private List<Disaster> disasterList;
    @JsonProperty("chat_messages")
    private List<Map<String, Object>> chatMessages;

    public ChatData(String victimName, List<Disaster> disasterList, List<Map<String, Object>> chatMessages) {
        this.victimName = victimName;
        this.disasterList = disasterList;
        this.chatMessages = chatMessages;
    }

    public String getVictimName() {
        return victimName;
    }

    public List<Disaster> getDisasterList() {
        return disasterList;
    }

    public List<Map<String, Object>> getChatMessages() {
        return chatMessages;
    }
}
