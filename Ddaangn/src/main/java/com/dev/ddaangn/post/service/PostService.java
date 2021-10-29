package com.dev.ddaangn.post.service;

import com.dev.ddaangn.common.error.ErrorMessage;
import com.dev.ddaangn.common.error.exception.NotFoundException;
import com.dev.ddaangn.post.converter.PostConverter;
import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.post.dto.request.PostInsertRequest;
import com.dev.ddaangn.post.dto.response.PostInsertResponse;
import com.dev.ddaangn.post.repository.PostRepository;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {
    private final PostConverter postConverter;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostConverter postConverter, PostRepository postRepository, UserRepository userRepository) {
        this.postConverter = postConverter;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public PostInsertResponse insert(PostInsertRequest request) {
        User user = getUser(request.getSellerId());
        Post post = postConverter.insertRequestDtoToEntity(request, user);

        post.addPost(user);

        Post insertedPost = postRepository.save(post);
        return new PostInsertResponse(insertedPost);
    }

    @Transactional
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_MEMBER));
    }

}
