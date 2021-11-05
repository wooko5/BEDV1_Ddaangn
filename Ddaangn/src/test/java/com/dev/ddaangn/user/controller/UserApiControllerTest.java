package com.dev.ddaangn.user.controller;

import com.dev.ddaangn.evaluation.domain.EvaluationsDetail;
import com.dev.ddaangn.evaluation.dto.request.EvaluationInsertRequest;
import com.dev.ddaangn.post.dto.response.PostDetailResponse;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import com.dev.ddaangn.user.dto.request.UserInsertRequestDto;
import com.dev.ddaangn.user.repository.UserRepository;
import com.dev.ddaangn.user.role.LoginRole;
import com.dev.ddaangn.user.service.TempService;
//import org.junit.Before;
//import org.junit.Test;
import com.dev.ddaangn.user.vo.BoughtPosts;
import com.dev.ddaangn.user.vo.SoldPosts;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
//import org.junit.runner.RunWith;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserApiControllerTest {

    private final Double USER_TEMPERATURE = 36.0;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    protected MockHttpSession session;

    @Autowired
    private WebApplicationContext context;

    private User myUser;

    private SessionUser sessionUser;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {

        LocalDateTime now = LocalDateTime.now();

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

    }

    @Test
    @DisplayName("유저 저장")
    @WithMockUser(roles = "USER")
    public void 유저SAVE() throws Exception {
        // Given
//        myUser = User.builder()
//                // .id(GIVING_ID)
//                .address("my address")
//                .name("test")
//                .phoneNumber("010-xxxx-xxxx")
//                .email("sds1vrk@naver.com")
//                .temperature(USER_TEMPERATURE)
//                .soldPosts(new SoldPosts())
//                .boughtPosts(new BoughtPosts())
//                .role(LoginRole.USER)
//                .build();

        session = new MockHttpSession();
        sessionUser = new SessionUser();
        session.setAttribute("user", sessionUser);

        // When
        UserInsertRequestDto requestDto = UserInsertRequestDto.builder()
                .name("test")
                .email("sds1zzang@naver.com")
                .role(LoginRole.USER)
                .picture("DESKTOP")
                .build();


        PostDetailResponse stubResponse = new PostDetailResponse(requestDto);
        given(postService.insert(any())).willReturn(stubResponse);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/v1/join")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto));

        // Then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(
                        status().is2xxSuccessful())
                .andDo(document("user-save",
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("title"),
                                fieldWithPath("contents").type(JsonFieldType.STRING).description("contents"),
                                fieldWithPath("sellerId").type(JsonFieldType.NUMBER).description("user Id")
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
    @DisplayName("ID로 유저 조회")
    @WithMockUser(roles = "USER")
    public void ID로유저조회 () throws Exception {
        // Given
        String id="1";
        String url = "http://localhost:" + 8080 + "/api/v1/temperature/"+id;

        // When Then
        mockMvc
                .perform(get(url))
                .andDo(print())
                .andExpect(
                status().is2xxSuccessful());
    }


    @Test
    @DisplayName("전체 USER LIST 조회")
    @WithMockUser(roles = "USER")
    public void 다건조회() throws Exception {
        // Given
        String url = "http://localhost:" + 8080 + "/api/v1/temperature/list";

        // When Then
        mockMvc
                .perform(get(url))
                .andDo(print())
                .andExpect(
                        status().is2xxSuccessful());
    }




}
