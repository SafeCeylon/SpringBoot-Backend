package dev.safeceylon.SafeCeylon.MD_Dashboard;

import dev.safeceylon.SafeCeylon.weather.WeatherReport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/meteorology/dashboard")
@CrossOrigin(origins = "*")
public class MDDashboardController {

    private final MDDashboardService mdDashboardService;

    public MDDashboardController(MDDashboardService mdDashboardService) {
        this.mdDashboardService = mdDashboardService;
    }

    // Get all wethear data
    @GetMapping()
    public List<WeatherReport> getWeatherReport() {
        return mdDashboardService.getWeatherReport();
    }
}
