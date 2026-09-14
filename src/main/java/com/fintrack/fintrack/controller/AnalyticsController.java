package com.fintrack.fintrack.controller;

import com.fintrack.fintrack.dto.MonthlyAnalyticsResponse;
import com.fintrack.fintrack.service.AnalyticsService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(
            AnalyticsService analyticsService) {

        this.analyticsService = analyticsService;
    }

    @GetMapping("/monthly")
    public ResponseEntity<MonthlyAnalyticsResponse> getMonthlyAnalytics(
            @RequestParam int year,
            @RequestParam int month,
            @AuthenticationPrincipal Jwt jwt) {

        Long userId = jwt.getClaim("userId");

        MonthlyAnalyticsResponse response =
                analyticsService.getMonthlyAnalytics(
                        userId,
                        year,
                        month
                );

        return ResponseEntity.ok(response);
    }
}