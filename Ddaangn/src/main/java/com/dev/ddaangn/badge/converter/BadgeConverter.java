package com.dev.ddaangn.badge.converter;

import com.dev.ddaangn.badge.Badge;
import com.dev.ddaangn.badge.dto.BadgeRequest;
import com.dev.ddaangn.user.User;
import org.springframework.stereotype.Component;

@Component
public class BadgeConverter {

    public Badge converterBadge(BadgeRequest request, User user){ // Dto -> Entity
        return Badge.builder()
                .name(request.getName())
                .description(request.getDescription())
                .achievement(false)
                .user(user)
                .badgeImages(request.getBadgeImages())
                .build();
    }
}
