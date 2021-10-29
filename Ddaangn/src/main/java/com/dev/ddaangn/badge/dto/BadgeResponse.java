package com.dev.ddaangn.badge.dto;

import com.dev.ddaangn.badge.Badge;
import com.dev.ddaangn.badge.BadgeImage;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode
public class BadgeResponse{
    private final Long badgeId;
    private final String name;
    private final String description;
    private final boolean achievement;
    private final Long userId;
    private final List<BadgeImage> badgeImages;

    public BadgeResponse(Badge badge){
        this.badgeId = badge.getId();
        this.name = badge.getName();
        this.description = badge.getDescription();
        this.achievement = badge.isAchievement();
        this.userId = badge.getUser().getId();
        this.badgeImages = badge.getBadgeImages();
    }
}
