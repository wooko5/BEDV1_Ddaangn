package com.dev.ddaangn.post.dto.response;

import com.dev.ddaangn.common.dto.BaseResponse;
import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.post.domain.PostStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;


@Getter
@EqualsAndHashCode
public class PostInsertResponse extends BaseResponse {
    private Long postId;
    private String title;
    private String content;
    private String username;
    private String status;
    private Long views;

    public PostInsertResponse(Post post) {
        super(post.getCreatedAt(), post.getUpdateAt(), post.getDeletedAt());
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContents();
        this.username = post.getSeller().getName();
        this.status = post.getStatus().getStatus();
        this.views = post.getViews();
    }
}
