package com.dev.ddaangn.post.dto.response;

import com.dev.ddaangn.common.dto.BaseResponse;
import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.post.domain.PostStatus;
import lombok.Getter;

import java.util.Objects;


@Getter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostInsertResponse that = (PostInsertResponse) o;
        return postId.equals(that.postId) && title.equals(that.title) && Objects.equals(content, that.content) && username.equals(that.username) && status.equals(that.status) && views.equals(that.views);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }
}
