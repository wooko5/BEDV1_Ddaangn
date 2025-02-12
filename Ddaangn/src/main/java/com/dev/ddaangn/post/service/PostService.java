package com.dev.ddaangn.post.service;

import com.dev.ddaangn.common.error.ErrorMessage;
import com.dev.ddaangn.common.error.exception.ForbiddenException;
import com.dev.ddaangn.common.error.exception.NotFoundException;
import com.dev.ddaangn.post.converter.PostConverter;
import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.post.dto.request.PostInsertRequest;
import com.dev.ddaangn.post.dto.request.PostStatusUpdateRequest;
import com.dev.ddaangn.post.dto.request.PostUpdateRequest;
import com.dev.ddaangn.post.dto.response.PostDetailResponse;
import com.dev.ddaangn.post.repository.PostRepository;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import com.dev.ddaangn.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public PostDetailResponse insert(PostInsertRequest request, SessionUser sessionUser) {
        User user = getUser(sessionUser.getId());
        Post post = postConverter.insertRequestDtoToEntity(request, user);

        post.addPost(user);

        Post insertedPost = postRepository.save(post);
        return new PostDetailResponse(insertedPost);
    }

    @Transactional
    public Page<PostDetailResponse> findAll(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(PostDetailResponse::new);
    }

    @Transactional
    public PostDetailResponse findById(Long postId) {
        return postRepository.findById(postId)
                .map(PostDetailResponse::new)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_POST));
    }

    @Transactional
    public PostDetailResponse update(Long postId, PostUpdateRequest request, SessionUser sessionUser) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_POST));
        if (!sessionUser.getId().equals(post.getSeller().getId())) { // 작성자만 가능.
            throw new ForbiddenException(ErrorMessage.FORBIDDEN);
        }
        post.update(request);
        return new PostDetailResponse(post);
    }

    @Transactional
    public void delete(Long postId, SessionUser sessionUser) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_POST));
        if (!sessionUser.getId().equals(post.getSeller().getId())) { // 작성자만 가능.
            throw new ForbiddenException(ErrorMessage.FORBIDDEN);
        }

        postRepository.delete(post);
    }

    @Transactional
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_MEMBER));
    }

    @Transactional
    public PostDetailResponse updateStatus(Long postId, PostStatusUpdateRequest request, SessionUser sessionUser) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_POST));
        if (!sessionUser.getId().equals(post.getSeller().getId())) { // 작성자만 가능.
            throw new ForbiddenException(ErrorMessage.FORBIDDEN);
        }
        if (request.getTargetUserId() != null) { // 예약자, 구입자가 있는 경우
            User buyer = getUser(request.getTargetUserId());
            post.updateBuyer(buyer);
        }
        post.updateStatus(request.getStatus());
        return new PostDetailResponse(post);
    }

    @Transactional
    public PostDetailResponse toggleHidden(Long postId, SessionUser sessionUser) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_POST));
        if (!sessionUser.getId().equals(post.getSeller().getId())) { // 작성자만 가능.
            throw new ForbiddenException(ErrorMessage.FORBIDDEN);
        }
        post.toggleHidden();
        return new PostDetailResponse(post);
    }
}