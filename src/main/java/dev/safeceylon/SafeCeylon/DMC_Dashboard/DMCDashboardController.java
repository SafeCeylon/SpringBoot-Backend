package dev.safeceylon.SafeCeylon.DMC_Dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/disaster/dashboard")
@CrossOrigin(origins = "*")
public class DMCDashboardController {

    private final DMCDashboardService dmcDashboardService;

    @Autowired
    public DMCDashboardController(DMCDashboardService dmcDashboardService) {
        this.dmcDashboardService = dmcDashboardService;
    }

    @GetMapping
    public DMCDashboard getDashboard() {
        DMCDashboard dashboard = new DMCDashboard();
        dashboard.setDisasterVictimStatusToReplyCount(dmcDashboardService.getDisasterVictimStatusToReplyCount());
        dashboard.setDisasterVictimStatusRepliedCount(dmcDashboardService.getDisasterVictimStatusRepliedCount());
        dashboard.setDisasterVictimStatusClosedCount(dmcDashboardService.getDisasterVictimStatusClosedCount());
        dashboard.setDisasterOfficerCount(dmcDashboardService.getDisasterOfficerCount());
        dashboard.setPublicUserCount(dmcDashboardService.getDisasterPublicUserCount());
        // Disaster Marks for the last 30 days
        //   { date: "2024-04-01", value: 118 },
        dashboard.setDisasterMarkCounts(dmcDashboardService.getDisasterMarksCounts());
        dashboard.setMarksCount(dmcDashboardService.getMarksCount());
        dashboard.setMonetaryDonations(dmcDashboardService.getMonetaryDonations());
        dashboard.setSuppliesDonationsWater(dmcDashboardService.getSuppliesDonationsWater());
        dashboard.setSuppliesDonationsFood(dmcDashboardService.getSuppliesDonationsFood());
        dashboard.setSuppliesDonationsMedicalSupplies(dmcDashboardService.getSuppliesDonationsMedicalSupplies());
        dashboard.setSuppliesDonationsClothing(dmcDashboardService.getSuppliesDonationsClothing());
        dashboard.setSuppliesDonationsOther(dmcDashboardService.getSuppliesDonationsOther());

        return dashboard;
    }

}
