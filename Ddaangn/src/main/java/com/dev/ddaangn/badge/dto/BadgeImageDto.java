package com.dev.ddaangn.badge.dto;

import com.dev.ddaangn.badge.BadgeImage;
import com.dev.ddaangn.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class BadgeImageDto {
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private LocalDateTime deletedAt;
    private Long id;
    private String url;
    private String type;

    public BadgeImage toEntity(){
        return (BadgeImage)BadgeImage.builder()
                .id(id)
                .url(url)
                .type(type)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BadgeImageDto)) return false;
        BadgeImageDto that = (BadgeImageDto) o;
        return Objects.equals(createdAt, that.createdAt)
                && Objects.equals(updateAt, that.updateAt)
                && Objects.equals(deletedAt, that.deletedAt)
                && Objects.equals(id, that.id)
                && Objects.equals(url, that.url)
                && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdAt, updateAt, deletedAt, id, url, type);
    }
}
