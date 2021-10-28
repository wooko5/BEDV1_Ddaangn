package com.dev.ddaangn.post.dto.response;

import com.dev.ddaangn.common.BaseEntity;
import com.dev.ddaangn.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostResponse extends BaseEntity {
    private final Long postId;
    private final String title;
    private final String content;
    private final String username;

    public PostResponse(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getTitle();
        this.username = post.getSeller().getName();
    }
}
