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

    @JsonProperty("victim_image")
    private String victimImage;

    // mobilenumber
    @JsonProperty("mobile_number")
    private String mobileNumber;

    //address
    @JsonProperty("address")
    private String address;

    //email
    @JsonProperty("email")
    private String email;

    public ChatData(String victimName, List<Disaster> disasterList, List<Map<String, Object>> chatMessages, String victimImage, String mobileNumber, String address, String email) {
        this.victimName = victimName;
        this.disasterList = disasterList;
        this.chatMessages = chatMessages;
        this.victimImage = victimImage;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.email = email;
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

    public String getVictimImage() {
        return victimImage;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
