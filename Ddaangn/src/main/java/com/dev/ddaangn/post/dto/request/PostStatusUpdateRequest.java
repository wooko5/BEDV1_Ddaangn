package com.dev.ddaangn.post.dto.request;

import com.dev.ddaangn.post.domain.PostStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostStatusUpdateRequest {
    private final PostStatus status;
    private Long targetUserId; // 예약자, 판매자 대상.
}
