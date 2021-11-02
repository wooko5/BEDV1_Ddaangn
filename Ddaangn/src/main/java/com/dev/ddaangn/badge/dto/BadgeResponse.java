package com.dev.ddaangn.badge.dto;

import com.dev.ddaangn.badge.Badge;
import com.dev.ddaangn.badge.BadgeImage;
import lombok.Getter;

import java.util.Objects;

@Getter
public class BadgeResponse {
    private final Long badgeId;
    private final String name;
    private final String description;
    private final boolean achievement;
    private final Long userId;
    private final BadgeImage badgeImage;

    public BadgeResponse(Badge badge) {
        this.badgeId = badge.getId();
        this.name = badge.getName();
        this.description = badge.getDescription();
        this.achievement = badge.isAchievement();
        this.userId = badge.getUser().getId();
        this.badgeImage = badge.getBadgeImage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BadgeResponse)) return false;
        BadgeResponse that = (BadgeResponse) o;
        return achievement == that.achievement
                && Objects.equals(badgeId, that.badgeId)
                && Objects.equals(name, that.name)
                && Objects.equals(description, that.description)
                && Objects.equals(userId, that.userId)
                && Objects.equals(badgeImage, that.badgeImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(badgeId, name, description, achievement, userId, badgeImage);
    }
}
