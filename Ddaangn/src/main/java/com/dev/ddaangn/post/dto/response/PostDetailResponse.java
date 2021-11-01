package com.dev.ddaangn.post.dto.response;

import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.post.domain.PostStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode
public class PostDetailResponse {
    private final Long id;
    private final String title;
    private final String contents;
    private final PostStatus status;
    private final Long views;
    private final String sellerName;
    // TODO: images

    public PostDetailResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.status = post.getStatus();
        this.views = post.getViews();
        this.sellerName = post.getSeller().getName();
    }
}
