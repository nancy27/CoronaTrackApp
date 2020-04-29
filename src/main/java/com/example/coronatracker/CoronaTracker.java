package com.example.coronatracker;

import com.example.coronatracker.domain.LocationStats;
import com.example.coronatracker.services.CoronaTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CoronaTracker {
    @Autowired
    CoronaTrackerService coronaTrackerService;
    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> locationStatsList=coronaTrackerService.getLocationStatsList();
        int totaReportedCases=locationStatsList.stream().mapToInt(stat ->stat.getTotalNoCases()).sum();
        model.addAttribute("locationStats" ,locationStatsList);
        model.addAttribute("totaReportedCases",totaReportedCases);
        return "home";
    }
}
