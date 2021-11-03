package com.dev.ddaangn.post.service;

import com.dev.ddaangn.common.error.exception.NotFoundException;
import com.dev.ddaangn.post.converter.PostConverter;
import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.post.domain.PostStatus;
import com.dev.ddaangn.post.dto.request.PostInsertRequest;
import com.dev.ddaangn.post.dto.request.PostStatusUpdateRequest;
import com.dev.ddaangn.post.dto.request.PostUpdateRequest;
import com.dev.ddaangn.post.dto.response.PostDetailResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
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
                .contents("test content")
                .title("test title")
                .sellerId(user.getId())
                .build();
        Post postStub = Post.builder()
                .title(givenRequest.getTitle())
                .contents(givenRequest.getContents())
                .status(PostStatus.SELLING)
                .views(INIT_POST_VIEWS)
                .seller(user)
                .build();
        Post insertedPostStub = Post.builder()
                .id(POST_ID)
                .title(givenRequest.getTitle())
                .contents(givenRequest.getContents())
                .status(PostStatus.SELLING)
                .views(INIT_POST_VIEWS)
                .seller(user)
                .isHidden(false)
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
        PostDetailResponse givenResult = new PostDetailResponse(insertedPostStub);
        // WHEN
        PostDetailResponse result = postService.insert(givenRequest);

        // THEN
        assertThat(result).isEqualTo(givenResult);
        assertThat(result.getIsHidden()).isFalse();
        verify(postConverter).insertRequestDtoToEntity(givenRequest, user);
        verify(userRepository).findById(givenRequest.getSellerId());
        verify(postRepository).save(postStub);
    }

    @Test
    @DisplayName("Post 리스트를 조회할 수 있다.")
    void testFindAll() {
        // GIVEN
        final int GIVEN_PAGE = 0;
        final int GIVEN_SIZE = 2;
        Pageable givenPageable = PageRequest.of(GIVEN_PAGE, GIVEN_SIZE);
        List<Post> posts = List.of(
                Post.builder()
                        .title("Test Title 1")
                        .contents("Test contents 1")
                        .status(PostStatus.SELLING)
                        .views(INIT_POST_VIEWS)
                        .seller(user)
                        .isHidden(true)
                        .build(),
                Post.builder()
                        .title("Test Title 2")
                        .contents("Test contents 2")
                        .status(PostStatus.SELLING)
                        .views(INIT_POST_VIEWS)
                        .seller(user)
                        .isHidden(false)
                        .build()
        );
        Page<Post> stubPosts = new PageImpl<>(posts);
        Page<PostDetailResponse> stubPostResponses = stubPosts.map(PostDetailResponse::new);
        when(postRepository.findAll(givenPageable)).thenReturn(stubPosts);

        // WHEN
        Page<PostDetailResponse> result = postService.findAll(givenPageable);

        // THEN
        assertThat(result.getTotalElements()).isEqualTo(stubPostResponses.getTotalElements());
        assertThat(result.getTotalPages()).isEqualTo(stubPostResponses.getTotalPages());
        verify(postRepository).findAll(givenPageable);
    }

    @Test
    @DisplayName("Post를 id로 조회할 수 있다.")
    void testFindById() {
        // GIVEN
        Post stubPostEntity = Post.builder()
                .id(POST_ID)
                .title("test title")
                .contents("test contents")
                .status(PostStatus.SELLING)
                .views(INIT_POST_VIEWS)
                .seller(user)
                .isHidden(false)
                .build();
        stubPostEntity.setCreatedAt(LocalDateTime.now());
        stubPostEntity.setUpdateAt(LocalDateTime.now());
        PostDetailResponse stubResponseDto = new PostDetailResponse(stubPostEntity);
        when(postRepository.findById(any())).thenReturn(Optional.of(stubPostEntity));

        // WHEN
        PostDetailResponse result = postService.findById(POST_ID);

        // THEN
        assertThat(result).isEqualTo(stubResponseDto);
        verify(postRepository).findById(POST_ID);
    }

    @Test
    @DisplayName("Post를 없는 id로 조회할 경우 에러를 반환 한다.")
    void testFindByIdNotFoundException() {
        // GIVEN
        when(postRepository.findById(POST_ID)).thenReturn(Optional.empty());

        // WHEN // THEN
        assertThatThrownBy(() -> postService.findById(POST_ID)).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("Post를 수정할 수 있다.")
    void testUpdate() {
        PostUpdateRequest givenRequest = PostUpdateRequest.builder()
                .contents("수정 컨텍스트")
                .title("수정된 제목")
                .build();
        Post stubOriginPostEntity = Post.builder()
                .id(POST_ID)
                .title("test title")
                .contents("test contents")
                .status(PostStatus.SELLING)
                .views(INIT_POST_VIEWS)
                .seller(user)
                .isHidden(true)
                .build();
        stubOriginPostEntity.setCreatedAt(LocalDateTime.now());
        stubOriginPostEntity.setUpdateAt(LocalDateTime.now());

        Post stubUpdatedPostEntity = Post.builder()
                .id(stubOriginPostEntity.getId())
                .title(givenRequest.getTitle())
                .contents(givenRequest.getContents())
                .status(stubOriginPostEntity.getStatus())
                .views(stubOriginPostEntity.getViews())
                .seller(stubOriginPostEntity.getSeller())
                .isHidden(stubOriginPostEntity.isHidden())
                .build();
        stubOriginPostEntity.setCreatedAt(stubOriginPostEntity.getCreatedAt());
        stubOriginPostEntity.setUpdateAt(LocalDateTime.now());

        PostDetailResponse stubResponseDto = new PostDetailResponse(stubUpdatedPostEntity);
        when(postRepository.findById(any())).thenReturn(Optional.of(stubOriginPostEntity));

        // WHEN
        PostDetailResponse result = postService.update(POST_ID, givenRequest);

        // THEN
        assertThat(result).isEqualTo(stubResponseDto);
        assertThat(result.getIsHidden()).isTrue();
        verify(postRepository).findById(POST_ID);
    }

    @Test
    @DisplayName("Post를 id로 삭제할 수 있다.")
    void testDelete() {
        // GIVEN
        when(postRepository.existsById(any())).thenReturn(true);
        // WHEN
        postService.delete(POST_ID);
        // THEN
        verify(postRepository).existsById(POST_ID);
        verify(postRepository).deleteById(POST_ID);
    }

    @Test
    @DisplayName("Post의 상태를 수정할 수 있다.")
    void testUpdateStatus() {
        // GIVEN
        User buyer = User.builder()
                .id(USER_ID + 1)
                .address("my address")
                .name("일용 2")
                .phoneNumber("010-5048-0001")
                .temperature(USER_TEMPERATURE)
                .soldPosts(new SoldPosts())
                .boughtPosts(new BoughtPosts())
                .build();
        PostStatusUpdateRequest givenRequest = PostStatusUpdateRequest.builder()
                .status(PostStatus.RESERVED)
                .targetUserId(buyer.getId())
                .build();
        Post stubOriginPostEntity = Post.builder()
                .id(POST_ID)
                .title("test title")
                .contents("test contents")
                .status(PostStatus.SELLING)
                .views(INIT_POST_VIEWS)
                .seller(user)
                .isHidden(true)
                .build();
        stubOriginPostEntity.setCreatedAt(LocalDateTime.now());
        stubOriginPostEntity.setUpdateAt(LocalDateTime.now());

        user.getSoldPosts().addPost(stubOriginPostEntity);

        Post stubUpdatedPostEntity = Post.builder()
                .id(stubOriginPostEntity.getId())
                .title(stubOriginPostEntity.getTitle())
                .contents(stubOriginPostEntity.getContents())
                .status(givenRequest.getStatus())
                .views(stubOriginPostEntity.getViews())
                .seller(stubOriginPostEntity.getSeller())
                .buyer(buyer)
                .isHidden(stubOriginPostEntity.isHidden())
                .build();
        stubOriginPostEntity.setCreatedAt(stubOriginPostEntity.getCreatedAt());
        stubOriginPostEntity.setUpdateAt(LocalDateTime.now());

        PostDetailResponse stubResponseDto = new PostDetailResponse(stubUpdatedPostEntity);
        when(postRepository.findById(any())).thenReturn(Optional.of(stubOriginPostEntity));
        when(userRepository.findById(any())).thenReturn(Optional.of(buyer));

        // WHEN
        PostDetailResponse result = postService.updateStatus(POST_ID, givenRequest);

        // THEN
        assertThat(result).isEqualTo(stubResponseDto);
        assertThat(result.getStatus()).isEqualTo(givenRequest.getStatus());
        assertThat(result.getBuyerName()).isEqualTo(buyer.getName());
        assertThat(buyer.getBoughtPosts().getSize()).isGreaterThan(0);
        assertThat(user.getSoldPosts().getSize()).isGreaterThan(0);

        verify(postRepository).findById(POST_ID);
    }
}