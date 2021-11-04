package com.dev.ddaangn.badge.controller;

import com.dev.ddaangn.badge.dto.BadgeRequest;
import com.dev.ddaangn.badge.dto.BadgeResponse;
import com.dev.ddaangn.badge.service.BadgeService;
import com.dev.ddaangn.common.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/badges")
public class BadgeController {
    private final BadgeService badgeService;

    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @GetMapping // 배지 전체 조회
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<BadgeResponse>> getAll() {
        List<BadgeResponse> all = badgeService.findAll();
        return ApiResponse.ok(all);
    }

    @PostMapping // 배지 생성
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> save(@RequestBody BadgeRequest request) {
        BadgeResponse badgeResponse = badgeService.save(request);
        return ApiResponse.ok(badgeResponse.getBadgeId());
    }
}
