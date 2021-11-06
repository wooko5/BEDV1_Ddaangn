package com.dev.ddaangn.post.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostInsertRequest {

    //    private Long sellerId;
    private String title;
    private String contents;
    // private List<Image> images;
}
