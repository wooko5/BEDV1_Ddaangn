package com.dev.ddaangn.badge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BadgeImageDto {
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private LocalDateTime deletedAt;
    private Long id;
    private String url;
    private String type;

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
