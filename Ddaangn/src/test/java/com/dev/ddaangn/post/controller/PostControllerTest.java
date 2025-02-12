package com.dev.ddaangn.post.controller;

import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.post.domain.PostStatus;
import com.dev.ddaangn.post.dto.request.PostInsertRequest;
import com.dev.ddaangn.post.dto.request.PostUpdateRequest;
import com.dev.ddaangn.post.dto.response.PostDetailResponse;
import com.dev.ddaangn.post.service.PostService;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import com.dev.ddaangn.user.vo.BoughtPosts;
import com.dev.ddaangn.user.vo.SoldPosts;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class PostControllerTest {
    private final Long INIT_POST_VIEWS = 1L;
    private final Long POST_ID = 1L;
    private final Long USER_ID = 3L;
    private final Double USER_TEMPERATURE = 36.0;
    protected MockHttpSession session;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PostService postService;
    private User user;
    private Post post;
    private SessionUser sessionUser;

    @Autowired
    private WebApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
//                .apply(springSecurity())
//                .apply(mock)
//                .build();
        LocalDateTime now = LocalDateTime.now();
        user = User.builder()
                .id(USER_ID)
                .address("my address")
                .name("일용")
                .phoneNumber("010-5048-0000")
                .temperature(USER_TEMPERATURE)
                .soldPosts(new SoldPosts())
                .boughtPosts(new BoughtPosts())
                .build();
        post = Post.builder()
                .id(POST_ID)
                .title("test title")
                .contents("test contents")
                .status(PostStatus.SELLING)
                .views(INIT_POST_VIEWS)
                .seller(user)
                .build();
        post.setCreatedAt(now);
        post.setUpdateAt(now);
        user.setCreatedAt(now);
        post.setUpdateAt(now);

        session = new MockHttpSession();
        sessionUser = new SessionUser(user);
        sessionUser.setId(user.getId());
        session.setAttribute("user", sessionUser);
    }

    @Test
    @DisplayName("[POST] '/api/v1/posts'")
    @WithMockUser(roles = "USER")
    void testInsertCall() throws Exception {
        // GIVEN
        PostInsertRequest givenRequest = PostInsertRequest.builder()
                .contents("test content")
                .title("test title")
                // .sellerId(user.getId())
                .build();
        PostDetailResponse stubResponse = new PostDetailResponse(post);
        given(postService.insert(any(), any())).willReturn(stubResponse);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/v1/posts")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON) // TODO: 사진 들어오면 multipart/form-data
                .content(objectMapper.writeValueAsString(givenRequest));

        // WHEN // THEN
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(document("post-save",
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("title"),
                                fieldWithPath("contents").type(JsonFieldType.STRING).description("contents")
                        ),
                        responseFields(
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("id"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("title"),
                                fieldWithPath("data.contents").type(JsonFieldType.STRING).description("contents"),
                                fieldWithPath("data.status").type(JsonFieldType.STRING).description("status"),
                                fieldWithPath("data.views").type(JsonFieldType.NUMBER).description("조회수"),
                                fieldWithPath("data.isHidden").type(JsonFieldType.BOOLEAN).description("판매자"),
                                fieldWithPath("data.sellerName").type(JsonFieldType.STRING).description("판매자"),
                                fieldWithPath("data.buyerName").type(JsonFieldType.STRING).description("구매자"),
                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 일자"),
                                fieldWithPath("data.updatedAt").type(JsonFieldType.STRING).description("수정 일자"),
                                fieldWithPath("data.deletedAt").type(JsonFieldType.NULL).description("삭제 일자"),
                                fieldWithPath("serverDateTime").type(JsonFieldType.STRING).description("응답시간")
                        )
                ));
    }

    @Test
    @DisplayName("[GET] '/api/v1/posts'")
    void testGetAllCall() throws Exception {
        // GIVEN
        final int PARAM_SIZE = 3;
        final int PARAM_PAGE = 0;
        Page<PostDetailResponse> stubResponses = new PageImpl<>(
                List.of(
                        new PostDetailResponse(
                                Post.builder()
                                        .title("Test Title 1")
                                        .contents("Test contents 1")
                                        .status(PostStatus.SELLING)
                                        .views(INIT_POST_VIEWS)
                                        .seller(user)
                                        .build()
                        ),
                        new PostDetailResponse(
                                Post.builder()
                                        .title("Test Title 2")
                                        .contents("Test contents 2")
                                        .status(PostStatus.SELLING)
                                        .views(INIT_POST_VIEWS)
                                        .seller(user)
                                        .build()
                        )
                )
        );

        given(postService.findAll(any())).willReturn(stubResponses);
        RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/posts")
                .param("page", String.valueOf(PARAM_PAGE))
                .param("size", String.valueOf(PARAM_SIZE))
                .contentType(MediaType.APPLICATION_JSON);

        // WHEN
        // THEN
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print());
        // TODO: REST-DOCS 문서화 작업
    }

    @Test
    @DisplayName("[GET] '/api/v1/posts/{id}'")
    void testGetOneCall() throws Exception {
        // GIVEN
        Long givenPostId = post.getId();
        PostDetailResponse stubResponse = new PostDetailResponse(post);

        given(postService.findById(any())).willReturn(stubResponse);
        RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/posts/" + givenPostId)
                .contentType(MediaType.APPLICATION_JSON);

        // WHEN // THEN
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("post-get-one",
                        responseFields(
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("id"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("title"),
                                fieldWithPath("data.contents").type(JsonFieldType.STRING).description("contents"),
                                fieldWithPath("data.status").type(JsonFieldType.STRING).description("status"),
                                fieldWithPath("data.views").type(JsonFieldType.NUMBER).description("조회수"),
                                fieldWithPath("data.isHidden").type(JsonFieldType.BOOLEAN).description("판매자"),
                                fieldWithPath("data.sellerName").type(JsonFieldType.STRING).description("판매자"),
                                fieldWithPath("data.buyerName").type(JsonFieldType.STRING).description("구매자"),
                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 일자"),
                                fieldWithPath("data.updatedAt").type(JsonFieldType.STRING).description("수정 일자"),
                                fieldWithPath("data.deletedAt").type(JsonFieldType.NULL).description("삭제 일자"),
                                fieldWithPath("serverDateTime").type(JsonFieldType.STRING).description("응답시간")
                        )
                ));
    }

    @Test
    @DisplayName("[PUT] '/api/v1/posts/{id}'")
    void testUpdateCall() throws Exception {
        // GIVEN
        PostUpdateRequest givenRequest = PostUpdateRequest.builder()
                .contents("update content")
                .title("update title")
                .build();

        post.update(givenRequest);

        PostDetailResponse stubResponse = new PostDetailResponse(post);
        given(postService.update(any(), any(), any())).willReturn(stubResponse);

        RequestBuilder request = MockMvcRequestBuilders.put("/api/v1/posts/" + post.getId())
                .session(session)
                .contentType(MediaType.APPLICATION_JSON) // TODO: 사진 들어오면 multipart/form-data
                .content(objectMapper.writeValueAsString(givenRequest));

        // WHEN // THEN
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print());
//                .andDo(document("post-update",
//                        requestFields(
//                                fieldWithPath("title").type(JsonFieldType.STRING).description("title"),
//                                fieldWithPath("contents").type(JsonFieldType.STRING).description("contents")
//                        ),
//                        responseFields(
//                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("id"),
//                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("title"),
//                                fieldWithPath("data.contents").type(JsonFieldType.STRING).description("contents"),
//                                fieldWithPath("data.status").type(JsonFieldType.STRING).description("status"),
//                                fieldWithPath("data.views").type(JsonFieldType.NUMBER).description("조회수"),
//                                fieldWithPath("data.isHidden").type(JsonFieldType.BOOLEAN).description("판매자"),
//                                fieldWithPath("data.sellerName").type(JsonFieldType.STRING).description("판매자"),
//                                fieldWithPath("data.buyerName").type(JsonFieldType.STRING).description("구매자"),
//                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 일자"),
//                                fieldWithPath("data.updatedAt").type(JsonFieldType.STRING).description("수정 일자"),
//                                fieldWithPath("data.deletedAt").type(JsonFieldType.NULL).description("삭제 일자"),
//                                fieldWithPath("serverDateTime").type(JsonFieldType.STRING).description("응답시간")
//                        )
//                ));
    }

    @Test
    @DisplayName("[DELETE] '/api/v1/posts/{id}'")
    void testDeleteCall() throws Exception {
        // GIVEN
        // WHEN
        RequestBuilder request = MockMvcRequestBuilders.delete("/api/v1/posts/" + post.getId())
                .session(session)
                .contentType(MediaType.APPLICATION_JSON);

        // THEN
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("post-delete",
                        responseFields(
                                fieldWithPath("data").type(JsonFieldType.NULL).description("데이터"),
                                fieldWithPath("serverDateTime").type(JsonFieldType.STRING).description("응답시간")
                        )
                ));

    }

    @Test
    @DisplayName("[PUT] '/api/v1/posts/{postId}/toggle-hidden'")
    void testToggleHiddenCall() throws Exception {
        // SOLD -> SELLING
        // GIVEN
        Post stubResultPost = Post.builder()
                .id(POST_ID)
                .title("test title")
                .contents("test contents")
                .status(PostStatus.SELLING)
                .views(INIT_POST_VIEWS)
                .seller(user)
                .isHidden(!post.isHidden())
                .build();
        stubResultPost.setCreatedAt(post.getCreatedAt());
        stubResultPost.setUpdateAt(post.getUpdateAt());

        PostDetailResponse stubResultResponse = new PostDetailResponse(stubResultPost);
        given(postService.toggleHidden(any(), any())).willReturn(stubResultResponse);

        RequestBuilder request = MockMvcRequestBuilders.put("/api/v1/posts/" + post.getId() + "/toggle-hidden")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON); // TODO: 사진 들어오면 multipart/form-data

        // WHEN // THEN
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("post-toggle",
                        responseFields(
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("id"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("title"),
                                fieldWithPath("data.contents").type(JsonFieldType.STRING).description("contents"),
                                fieldWithPath("data.status").type(JsonFieldType.STRING).description("status"),
                                fieldWithPath("data.views").type(JsonFieldType.NUMBER).description("조회수"),
                                fieldWithPath("data.isHidden").type(JsonFieldType.BOOLEAN).description("판매자"),
                                fieldWithPath("data.sellerName").type(JsonFieldType.STRING).description("판매자"),
                                fieldWithPath("data.buyerName").type(JsonFieldType.STRING).description("구매자"),
                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 일자"),
                                fieldWithPath("data.updatedAt").type(JsonFieldType.STRING).description("수정 일자"),
                                fieldWithPath("data.deletedAt").type(JsonFieldType.NULL).description("삭제 일자"),
                                fieldWithPath("serverDateTime").type(JsonFieldType.STRING).description("응답시간")
                        )
                ));
    }
}