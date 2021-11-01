package com.dev.ddaangn.badge.dto;

import com.dev.ddaangn.badge.Badge;
import com.dev.ddaangn.badge.BadgeImage;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@EqualsAndHashCode
public class BadgeImageDto {
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private LocalDateTime deletedAt;
    private Long id;
    private String url;
    private String type;

    public BadgeImage toEntity(){
        return (BadgeImage) BadgeImage.builder()
                .id(id)
                .url(url)
                .type(type)
                .build();
    }
}
