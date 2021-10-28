package com.dev.ddaangn.post.service;

import com.dev.ddaangn.post.dto.request.PostInsertRequest;
import com.dev.ddaangn.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

}
