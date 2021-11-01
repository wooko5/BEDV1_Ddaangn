package com.dev.ddaangn.post.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostUpdateRequest {
    // private Long sellerId; // TODO: SessionUser에서 받기
    private String title;
    private String contents;
    // private List<Image> images;
}
