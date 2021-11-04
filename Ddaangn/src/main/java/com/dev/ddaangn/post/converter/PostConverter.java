package com.dev.ddaangn.post.converter;

import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.post.domain.PostStatus;
import com.dev.ddaangn.post.dto.request.PostInsertRequest;
import com.dev.ddaangn.user.User;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {
    private final Long INIT_VIEW_NUMBER = 0L;

    // requestDto -> Entity
    public Post insertRequestDtoToEntity(PostInsertRequest request, User seller) {
        return Post.builder()
                .title(request.getTitle())
                .contents(request.getContents())
                .status(PostStatus.SELLING)
                .views(INIT_VIEW_NUMBER)
                .build();
    }
}
