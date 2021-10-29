package com.dev.ddaangn.badge.converter;

import com.dev.ddaangn.badge.Badge;
import com.dev.ddaangn.badge.dto.BadgeResponse;
import com.dev.ddaangn.user.User;
import org.springframework.stereotype.Component;

@Component
public class BadgeConverter {

    public Badge converterBadge(BadgeResponse badgeResponse, User user){ // responseDto -> Entity
        return Badge.builder()
                .id(badgeResponse.getBadgeId())
                .name(badgeResponse.getName())
                .description(badgeResponse.getDescription())
                .achievement(badgeResponse.isAchievement())
                .user(user)
                .badgeImages(badgeResponse.getBadgeImages())
                .build();
    }
}
