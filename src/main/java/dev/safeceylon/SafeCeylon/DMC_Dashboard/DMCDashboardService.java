package dev.safeceylon.SafeCeylon.DMC_Dashboard;

import dev.safeceylon.SafeCeylon.DisasterVictim.DisasterVictimRepository;
import dev.safeceylon.SafeCeylon.DisasterVictim.VictimStatus;
import dev.safeceylon.SafeCeylon.disastermanagement.DisasterRepository;
import dev.safeceylon.SafeCeylon.user.UserRepository;
import dev.safeceylon.SafeCeylon.user.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DMCDashboardService {
    private final DisasterVictimRepository disasterVictimRepository;
    private final UserRepository userRepository;
    private final DisasterRepository disasterRepository;

    public DMCDashboardService(DisasterVictimRepository disasterVictimRepository, UserRepository userRepository, DisasterRepository disasterRepository) {
        this.disasterVictimRepository = disasterVictimRepository;
        this.userRepository = userRepository;
        this.disasterRepository = disasterRepository;
    }

    public float getDisasterVictimStatusToReplyCount(){
        return disasterVictimRepository.countByVictimStatus(VictimStatus.ToReply);
    }

    public float getDisasterVictimStatusRepliedCount(){
        return disasterVictimRepository.countByVictimStatus(VictimStatus.Replied);
    }

    public float getDisasterVictimStatusClosedCount(){
        return disasterVictimRepository.countByVictimStatus(VictimStatus.Closed);
    }

    public int getDisasterOfficerCount(){
        int disasterOfficerCount = userRepository.countByRole(UserRole.DISASTER_OFFICER);
        int disasterAdminCount = userRepository.countByRole(UserRole.DISASTER_ADMIN);
        return disasterOfficerCount + disasterAdminCount;
    }

    public int getDisasterPublicUserCount(){
        return userRepository.countByRole(UserRole.USER);
    }

    public List<Map<String, Integer>> getDisasterMarksCounts(){
        // today date
        LocalDateTime today = LocalDateTime.now();
        int count = 30;
        List<Map<String, Integer>> disasterMarkCounts = new ArrayList<>(); //{ date: "2024-04-01", value: 118 },
        for (int i = 0; i < count; i++) {
            // @Query("SELECT COUNT(d) FROM Disaster d WHERE d.reportedAt BETWEEN :startDate AND :endDate")
            // int countDisastersReportedOnDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
            // Satart date = today morning 00:00:00
            LocalDateTime startDate = today.minusDays(i).withHour(0).withMinute(0).withSecond(0);
            // End date = today night 23:59:59
            LocalDateTime endDate = today.minusDays(i).withHour(23).withMinute(59).withSecond(59);
            int disasterCount = disasterRepository.countDisastersReportedOnDate(startDate, endDate);
            LocalDateTime date = today.minusDays(i);
            String dateStr = date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth();
            Map<String, Integer> disasterMarkCount = new HashMap<>();
            disasterMarkCount.put(dateStr, disasterCount);
            disasterMarkCounts.add(disasterMarkCount);
            System.out.println("Disaster count on " + dateStr + " is " + disasterCount);
        }
        return disasterMarkCounts;
    }

}
