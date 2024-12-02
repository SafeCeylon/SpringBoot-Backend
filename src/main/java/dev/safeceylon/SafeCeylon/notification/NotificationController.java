package dev.safeceylon.SafeCeylon.notification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {
    private final NotificationRepository notificationRepository;
    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    //POST method to create a new notification
    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody Notification notification) {
        // Fetch notifications by userId and title
        List<Notification> existingNotifications = notificationRepository.findByUserIdAndTitle(notification.getUserId(), notification.getTitle());

        // Check if a similar notification already exists
        for (Notification existing : existingNotifications) {
            if (existing.getThreatLevel().equals(notification.getThreatLevel())) {
                // If threat level matches, no new notification should be added
                return new ResponseEntity<>("Notification already exists with same threat level.", HttpStatus.OK);
            }
        }

        // If no matching notification exists, save the new notification
        Notification newNotification = notificationRepository.save(notification);
        return new ResponseEntity<>("Notification created successfully.", HttpStatus.CREATED);
    }

}
