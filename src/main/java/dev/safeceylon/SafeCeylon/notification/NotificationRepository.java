package dev.safeceylon.SafeCeylon.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import dev.safeceylon.SafeCeylon.notification.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {
    // Additional query methods can be added if needed

    @Query("SELECT n FROM Notification n WHERE n.userId = :userId AND n.cleared = false")
    List<Notification> findUnclearedNotificationsByUserId(@Param("userId") String userId);

    @Query("SELECT n FROM Notification n WHERE n.userId = :userId AND n.title = :title")
    List<Notification> findByUserIdAndTitle(@Param("userId") String userId, @Param("title") String title);

}
