package com.dev.ddaangn.badge.converter;

import com.dev.ddaangn.badge.Badge;
import com.dev.ddaangn.badge.BadgeImage;
import com.dev.ddaangn.badge.dto.BadgeImageDto;
import com.dev.ddaangn.badge.dto.BadgeRequest;
import com.dev.ddaangn.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BadgeConverter {

    public Badge converterBadge(BadgeRequest request, User user){ // Dto -> Entity
        return Badge.builder()
                .name(request.getName())
                .description(request.getDescription())
                .achievement(request.isAchievement())
                .badgeImages(request.ListDtoToListEntity(request.getBadgeImageDto()))
                .user(user)
                .build();
    }
}
