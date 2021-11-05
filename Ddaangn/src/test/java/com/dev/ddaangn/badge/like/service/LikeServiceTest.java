package com.dev.ddaangn.badge.like.service;

import com.dev.ddaangn.like.domain.Like;
import com.dev.ddaangn.like.dto.response.LikeDetailResponse;
import com.dev.ddaangn.like.repository.LikeRepository;
import com.dev.ddaangn.like.service.LikeService;
import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.post.domain.PostStatus;
import com.dev.ddaangn.post.repository.PostRepository;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.repository.UserRepository;
import com.dev.ddaangn.user.vo.BoughtPosts;
import com.dev.ddaangn.user.vo.SoldPosts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

    @InjectMocks
    private LikeService likeService;

    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private LikeRepository likeRepository;

    private User user;
    private Post post;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .address("my address")
                .name("일용")
                .phoneNumber("010-5048-0000")
                .temperature(36.0)
                .soldPosts(new SoldPosts())
                .boughtPosts(new BoughtPosts())
                .likes(new ArrayList<>())
                .build();
        post = Post.builder()
                .id(1L)
                .title("title")
                .contents("contents")
                .status(PostStatus.SELLING)
                .views(2L)
                .seller(user)
                .likes(new ArrayList<>())
                .build();
    }

    @Test
    @DisplayName("Like를 생성 할 수 있다.")
    void testCreateLike() {
        assertThat(user.getLikes()).isNotNull();
        // GIVEN
        Long givenPostId = post.getId();
        Long givenUserId = user.getId();

        Like stubLikeResult = Like.builder()
                .id(1L)
                .user(user)
                .post(post)
                .build();
        LikeDetailResponse stubLikeResponse = LikeDetailResponse.builder()
                .id(stubLikeResult.getId())
                .postId(stubLikeResult.getPost().getId())
                .userId(stubLikeResult.getUser().getId())
                .build();

        when(userRepository
                .findById(givenUserId))
                .thenReturn(Optional.of(user));
        when(postRepository
                .findById(givenPostId))
                .thenReturn(Optional.of(post));
        when(likeRepository
                .save(any()))
                .thenReturn(stubLikeResult);
        // WHEN
        LikeDetailResponse result = likeService.create(givenPostId, givenUserId);

        // THEN
        assertThat(result).isEqualTo(stubLikeResponse);
        assertThat(user.getLikes().size()).isGreaterThan(0);
        assertThat(post.getLikes().size()).isGreaterThan(0);

    }
}