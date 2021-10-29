package com.dev.ddaangn.badge.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BadgeRequest {
    private Long userId;
    private String name;
    private String description;
}
