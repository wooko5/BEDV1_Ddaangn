package com.dev.ddaangn.badge.controller;

import com.dev.ddaangn.badge.dto.BadgeResponse;
import com.dev.ddaangn.badge.service.BadgeService;
import com.dev.ddaangn.common.api.ApiResponse;
import com.dev.ddaangn.image.dto.ImageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/badges")
public class BadgeController {
    private final BadgeService badgeService;

    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @GetMapping("/") // 배지 전체 조회
    public ApiResponse<Page<BadgeResponse>> getAll(Pageable pageable) {
        Page<BadgeResponse> all = badgeService.findAll(pageable);
        return ApiResponse.ok(all);
    }
}
