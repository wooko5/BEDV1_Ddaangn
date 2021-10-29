package com.dev.ddaangn.badge.dto;

import com.dev.ddaangn.badge.Badge;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class BadgeResponse{
    private Long badgeId;
    private String name;
    private String description;
    private boolean achievement;

    public BadgeResponse(Badge badge){
        this.badgeId = badge.getId();
        this.name = badge.getName();
        this.description = badge.getDescription();
        this.achievement = badge.isAchievement();
    }
}
