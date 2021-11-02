package com.dev.ddaangn.badge.controller;

import com.dev.ddaangn.badge.dto.BadgeResponse;
import com.dev.ddaangn.badge.service.BadgeService;
import com.dev.ddaangn.common.api.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/badges")
public class BadgeController {
    private final BadgeService badgeService;

    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @GetMapping() // 배지 전체 조회
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<BadgeResponse>> getAll() {
        List<BadgeResponse> all = badgeService.findAll();
        return ApiResponse.ok(all);
    }
}
