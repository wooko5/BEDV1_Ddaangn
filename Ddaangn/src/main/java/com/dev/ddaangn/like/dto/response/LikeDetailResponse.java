package com.dev.ddaangn.like.dto.response;

import com.dev.ddaangn.common.dto.BaseResponse;
import com.dev.ddaangn.like.domain.Like;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class LikeDetailResponse extends BaseResponse {
    private final Long id;
    private final Long userId;
    private final Long postId;

    @Builder
    public LikeDetailResponse(LocalDateTime createdAt,
                              LocalDateTime updatedAt,
                              LocalDateTime deletedAt,
                              Long id,
                              Long userId,
                              Long postId) {
        super(createdAt, updatedAt, deletedAt);
        this.id = id;
        this.userId = userId;
        this.postId = postId;
    }

    public LikeDetailResponse(Like like) {
        super(like.getCreatedAt(), like.getUpdateAt(), like.getDeletedAt());
        id = like.getId();
        userId = like.getUser().getId();
        postId = like.getPost().getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeDetailResponse that = (LikeDetailResponse) o;
        return id.equals(that.id) && userId.equals(that.userId) && postId.equals(that.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
