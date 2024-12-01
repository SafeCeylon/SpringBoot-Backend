package dev.safeceylon.SafeCeylon.notification;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private String message;
    private Date date;
    private String threatLevel;
    private String idUser;
    private String status;
    private Boolean cleared;

    public Notification() { }

    public Notification(String title, String message, Date date, String idUser, String threatLevel, String status, Boolean cleared) {
        this.title = title;
        this.message = message;
        this.date = date;
        this.idUser = idUser;
        this.threatLevel = threatLevel;
        this.status = status;
        this.cleared = cleared;
    }

}
