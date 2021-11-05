package com.dev.ddaangn.like.service;

import com.dev.ddaangn.common.error.ErrorMessage;
import com.dev.ddaangn.common.error.exception.NotFoundException;
import com.dev.ddaangn.like.domain.Like;
import com.dev.ddaangn.like.dto.response.LikeDetailResponse;
import com.dev.ddaangn.like.repository.LikeRepository;
import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.post.repository.PostRepository;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public LikeService(LikeRepository likeRepository, UserRepository userRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // [PUT] /api/v1/posts/{postId}/likes
    @Transactional
    public LikeDetailResponse create(Long postId, Long userId) {
        User user = getUser(userId);
        Post post = getPost(postId);

        Like like = new Like();
        like.addLike(user, post);

        return new LikeDetailResponse(likeRepository.save(like));
    }


    // [DELETE] /api/v1/posts/{postId}/likes
    @Transactional
    public void delete(Long likeId) {
        Like like = likeRepository.findById(likeId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_LIKE));
        like.removeLike();
        likeRepository.deleteById(likeId);
    }

    @Transactional
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_MEMBER));
    }

    @Transactional
    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_POST));
    }


}
