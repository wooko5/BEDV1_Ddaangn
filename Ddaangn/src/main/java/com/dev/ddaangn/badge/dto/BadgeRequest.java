package com.dev.ddaangn.badge.dto;

import com.dev.ddaangn.badge.BadgeImage;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BadgeRequest {
    private String name;
    private String description;
    private boolean achievement;
    private List<BadgeImage> badgeImages;
    private Long userId;
}
