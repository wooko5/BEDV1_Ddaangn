package com.dev.ddaangn.post.service;

import com.dev.ddaangn.post.converter.PostConverter;
import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.post.domain.PostStatus;
import com.dev.ddaangn.post.dto.request.PostInsertRequest;
import com.dev.ddaangn.post.dto.response.PostInsertResponse;
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

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    private final Long INIT_POST_VIEWS = 1L;
    private final Long POST_ID = 1L;
    private final Long USER_ID = 3L;
    private final Double USER_TEMPERATURE = 36.0;

    @InjectMocks
    private PostService postService;
    @Mock
    private PostConverter postConverter;
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(USER_ID)
                .address("my address")
                .name("일용")
                .phoneNumber("010-5048-0000")
                .temperature(USER_TEMPERATURE)
                .soldPosts(new SoldPosts())
                .boughtPosts(new BoughtPosts())
                .build();
    }

    @Test
    @DisplayName("Post를 생성할 수 있다.")
    void testInsert() {
        // GIVEN
        PostInsertRequest givenRequest = PostInsertRequest.builder()
                .content("test content")
                .title("test title")
                .sellerId(user.getId())
                .build();
        Post postStub = Post.builder()
                .title(givenRequest.getTitle())
                .contents(givenRequest.getContent())
                .status(PostStatus.SELLING)
                .views(INIT_POST_VIEWS)
                .seller(user)
                .build();
        Post insertedPostStub = Post.builder()
                .id(POST_ID)
                .title(givenRequest.getTitle())
                .contents(givenRequest.getContent())
                .status(PostStatus.SELLING)
                .views(INIT_POST_VIEWS)
                .seller(user)
                .build();
        insertedPostStub.setCreatedAt(LocalDateTime.now());
        insertedPostStub.setUpdateAt(LocalDateTime.now());
        when(postConverter
                .insertRequestDtoToEntity(
                        givenRequest, user))
                .thenReturn(postStub);
        when(userRepository
                .findById(
                        givenRequest.getSellerId()
                ))
                .thenReturn(Optional.of(user));
        when(postRepository.save(postStub)).thenReturn(insertedPostStub);
        PostInsertResponse givenResult = new PostInsertResponse(insertedPostStub);
        // WHEN
        PostInsertResponse result = postService.insert(givenRequest);

        // THEN
        assertThat(result).isEqualTo(givenResult);
        verify(postConverter).insertRequestDtoToEntity(givenRequest, user);
        verify(userRepository).findById(givenRequest.getSellerId());
        verify(postRepository).save(postStub);
    }
}