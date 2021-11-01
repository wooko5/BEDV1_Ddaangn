package com.dev.ddaangn.badge.dto;

import com.dev.ddaangn.badge.Badge;
import com.dev.ddaangn.badge.BadgeImage;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class BadgeRequest {
    private String name;
    private String description;
    private boolean achievement;
    private List<BadgeImageDto> badgeImageDto;
    private Long userId;

    public List<BadgeImage> ListDtoToListEntity(List<BadgeImageDto> badgeImageDtos){
        return badgeImageDtos.stream()
                .map(BadgeImageDto::toEntity)
                .collect(Collectors.toList());
    }
}
