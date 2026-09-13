package com.fintrack.fintrack.controller;

import com.fintrack.fintrack.dto.DashboardResponse;
import com.fintrack.fintrack.service.DashboardService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboard(
            @AuthenticationPrincipal Jwt jwt) {

        Long userId = jwt.getClaim("userId");

        DashboardResponse dashboard =
                dashboardService.getDashboard(userId);

        return ResponseEntity.ok(dashboard);
    }
}