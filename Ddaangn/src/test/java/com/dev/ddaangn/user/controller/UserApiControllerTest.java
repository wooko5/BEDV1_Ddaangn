package com.dev.ddaangn.user.controller;

import com.dev.ddaangn.evaluation.domain.EvaluationsDetail;
import com.dev.ddaangn.evaluation.dto.request.EvaluationInsertRequest;
import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.post.domain.PostStatus;
import com.dev.ddaangn.post.dto.request.PostUpdateRequest;
import com.dev.ddaangn.post.dto.response.PostDetailResponse;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import com.dev.ddaangn.user.dto.request.UserInsertRequestDto;
import com.dev.ddaangn.user.dto.request.UserUpdateRequestDto;
import com.dev.ddaangn.user.dto.response.UserDetailResponse;
import com.dev.ddaangn.user.repository.UserRepository;
import com.dev.ddaangn.user.role.LoginRole;
import com.dev.ddaangn.user.service.TempService;
//import org.junit.Before;
//import org.junit.Test;
import com.dev.ddaangn.user.service.UserService;
import com.dev.ddaangn.user.vo.BoughtPosts;
import com.dev.ddaangn.user.vo.SoldPosts;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
//import org.junit.runner.RunWith;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.RestDocumentationExtension;
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
import java.util.List;

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
@AutoConfigureRestDocs
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

    @MockBean
    private UserService userService;

    private User user;

    LocalDateTime now = LocalDateTime.now();

    @BeforeEach
    public void setUp() {

        session = new MockHttpSession();
        sessionUser = new SessionUser();
        session.setAttribute("user", sessionUser);

        user = User.builder()
                .name("test")
                .email("sds1zzang@naver.com")
                .role(LoginRole.USER)
                .picture("DESKTOP")
                .temperature(USER_TEMPERATURE)
                .phoneNumber("010")
                .address("서울특별시")
                .build();

        user.setCreatedAt(now);
        user.setUpdateAt(now);

        userRepository.save(user);

//        this.mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();

    }

    @AfterEach
    void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("[POST] /api/v1/member/insert")
    @WithMockUser(roles = "USER")
    public void 유저SAVE() throws Exception {



        // When
        UserInsertRequestDto requestDto = UserInsertRequestDto.builder()
                .name("test")
                .email("sds1zzang@naver.com")
                .role(LoginRole.USER)
                .picture("DESKTOP")
                .temperature(USER_TEMPERATURE)
                .phoneNumber("010")
                .address("서울특별시")
                .build();


        RequestBuilder request = MockMvcRequestBuilders.post("/api/v1/member/join")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto));

        UserDetailResponse stubResponse = new UserDetailResponse(user);
        given(userService.insert(any())).willReturn(stubResponse);

        // Then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("user-save",
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("userName"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("userEmail"),
                                fieldWithPath("role").type(JsonFieldType.STRING).description("GUEST or USER"),
                                fieldWithPath("picture").type(JsonFieldType.STRING).description("userPicture"),
                                fieldWithPath("temperature").type(JsonFieldType.NUMBER).description("userTemperature"),
                                fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("userPhoneNum"),
                                fieldWithPath("address").type(JsonFieldType.STRING).description("userAddress")
                        ),
                        responseFields(

                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("userName"),
                                fieldWithPath("data.email").type(JsonFieldType.STRING).description("userEmail"),
                                fieldWithPath("data.role").type(JsonFieldType.STRING).description("GUEST or USER"),
                                fieldWithPath("data.picture").type(JsonFieldType.STRING).description("userPicture"),
                                fieldWithPath("data.temperature").type(JsonFieldType.NUMBER).description("userTemperature"),
                                fieldWithPath("data.phoneNumber").type(JsonFieldType.STRING).description("userPhoneNum"),
                                fieldWithPath("data.address").type(JsonFieldType.STRING).description("userAddress"),
                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 일자"),
                                fieldWithPath("data.updatedAt").type(JsonFieldType.STRING).description("수정 일자"),
                                fieldWithPath("data.deletedAt").type(JsonFieldType.NULL).description("삭제 일자"),
                                fieldWithPath("serverDateTime").type(JsonFieldType.STRING).description("응답시간")
                        )
                ));

    }


    @Test
    @DisplayName("[GET] /api/v1/member/{id}")
    @WithMockUser(roles = "USER")
    public void ID로유저조회 () throws Exception {
        // Given
        user = User.builder()
                .name("test")
                .email("sds1zzang@naver.com")
                .role(LoginRole.USER)
                .picture("DESKTOP")
                .temperature(USER_TEMPERATURE)
                .phoneNumber("010")
                .address("서울특별시")
                .build();

        user.setCreatedAt(now);
        user.setUpdateAt(now);

        userRepository.save(user);

        RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/member/" + user.getId())
                .session(session)
                .contentType(MediaType.APPLICATION_JSON);

        UserDetailResponse stubResponse = new UserDetailResponse(user);
        given(userService.findById(any())).willReturn(stubResponse);

        // When Then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user-get-one",
                        responseFields(
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("userName"),
                                fieldWithPath("data.email").type(JsonFieldType.STRING).description("userEmail"),
                                fieldWithPath("data.role").type(JsonFieldType.STRING).description("GUEST or USER"),
                                fieldWithPath("data.picture").type(JsonFieldType.STRING).description("userPicture"),
                                fieldWithPath("data.temperature").type(JsonFieldType.NUMBER).description("userTemperature"),
                                fieldWithPath("data.phoneNumber").type(JsonFieldType.STRING).description("userPhoneNum"),
                                fieldWithPath("data.address").type(JsonFieldType.STRING).description("userAddress"),
                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 일자"),
                                fieldWithPath("data.updatedAt").type(JsonFieldType.STRING).description("수정 일자"),
                                fieldWithPath("data.deletedAt").type(JsonFieldType.NULL).description("삭제 일자"),
                                fieldWithPath("serverDateTime").type(JsonFieldType.STRING).description("응답시간")
                        )
                ));

    }


//    @Test
//    @DisplayName("[GET] /api/v1/member")
//    @WithMockUser(roles = "USER")
//    public void 다건조회() throws Exception {
//        // Given
//        String url = "http://localhost:" + 8080 + "/api/v1/temperature/list";
//
//        // When Then
//        mockMvc
//                .perform(get(url))
//                .andDo(print())
//                .andExpect(
//                        status().is2xxSuccessful());
//    }

    @Test
    @DisplayName("[GET] /api/v1/member")
    void testGetAllCall() throws Exception {
        // GIVEN
        final int PARAM_SIZE = 10;
        final int PARAM_PAGE = 0;
        User user1;
        User user2;

        user1 =User.builder()
                .name("11111")
                .email("sds1zzang@naver.com")
                .role(LoginRole.USER)
                .picture("DESKTOP")
                .temperature(USER_TEMPERATURE)
                .phoneNumber("010")
                .address("서울특별시")
                .build();

        user2=User.builder()
                .name("22222")
                .email("sds1vrk@naver.com")
                .role(LoginRole.USER)
                .picture("NOTEBOOK")
                .temperature(USER_TEMPERATURE)
                .phoneNumber("010")
                .address("서울특별시")
                .build();

        user1.setCreatedAt(now);
        user1.setUpdateAt(now);

        user2.setCreatedAt(now);
        user2.setUpdateAt(now);


        Page<UserDetailResponse> stubResponses = new PageImpl<>(
                List.of(
                        new UserDetailResponse(
                            user1
                        )
                        ,
                        new UserDetailResponse(
                            user2
                        )
                )

        );

        given(userService.findAll(any())).willReturn(stubResponses);
        RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/member")
                .param("page", String.valueOf(PARAM_PAGE))
                .param("size", String.valueOf(PARAM_SIZE))
                .contentType(MediaType.APPLICATION_JSON);

        // WHEN
        // THEN
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user-getAll",
                        responseFields(
//                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("data"),
                                fieldWithPath("data.pageable").type(JsonFieldType.STRING).description("instance"),
                                fieldWithPath("data.last").type(JsonFieldType.BOOLEAN).description("instance"),
                                fieldWithPath("data.totalElements").type(JsonFieldType.NUMBER).description("total"),
                                fieldWithPath("data.totalPages").type(JsonFieldType.NUMBER).description("totalPage"),
                                fieldWithPath("data.size").type(JsonFieldType.NUMBER).description("size"),
                                fieldWithPath("data.number").type(JsonFieldType.NUMBER).description("number"),
                                fieldWithPath("data.first").type(JsonFieldType.BOOLEAN).description("first"),
                                fieldWithPath("data.numberOfElements").type(JsonFieldType.NUMBER).description("numberOfElements"),
                                fieldWithPath("data.empty").type(JsonFieldType.BOOLEAN).description("empty"),
                                fieldWithPath("data.sort.empty").type(JsonFieldType.BOOLEAN).description("empty"),
                                fieldWithPath("data.sort.sorted").type(JsonFieldType.BOOLEAN).description("sorted"),
                                fieldWithPath("data.sort.unsorted").type(JsonFieldType.BOOLEAN).description("unsorted"),


                                fieldWithPath("data.content.[].name").type(JsonFieldType.STRING).description("userName"),
                                fieldWithPath("data.content.[].email").type(JsonFieldType.STRING).description("userEmail"),
                                fieldWithPath("data.content.[].role").type(JsonFieldType.STRING).description("GUEST or USER"),
                                fieldWithPath("data.content.[].picture").type(JsonFieldType.STRING).description("userPicture"),
                                fieldWithPath("data.content.[].temperature").type(JsonFieldType.NUMBER).description("userTemperature"),
                                fieldWithPath("data.content.[].phoneNumber").type(JsonFieldType.STRING).description("userPhoneNum"),
                                fieldWithPath("data.content.[].address").type(JsonFieldType.STRING).description("userAddress"),
                                fieldWithPath("data.content.[].createdAt").type(JsonFieldType.STRING).description("생성 일자"),
                                fieldWithPath("data.content.[].updatedAt").type(JsonFieldType.STRING).description("수정 일자"),
                                fieldWithPath("data.content.[].deletedAt").type(JsonFieldType.NULL).description("삭제 일자"),
                                fieldWithPath("serverDateTime").type(JsonFieldType.STRING).description("응답시간")
                        )
                ));
    }

    // 업데이트
    @Test
    @DisplayName("[PUT] '/api/v1/member/update/{id}'")
    void testUpdateCall() throws Exception {
        // GIVEN
        UserUpdateRequestDto givenRequest = UserUpdateRequestDto.builder()
                .name("updateID")
                .picture("updatePicture")
                .build();

        user.updatedRequestDto(givenRequest);

        UserDetailResponse stubResponse = new UserDetailResponse(user);
        given(userService.update(any(), any())).willReturn(stubResponse);

        RequestBuilder request = MockMvcRequestBuilders.put("/api/v1/member/update/" + user.getId())
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(givenRequest));

        // WHEN // THEN
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(document("user-update",
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("name"),
                                fieldWithPath("picture").type(JsonFieldType.STRING).description("picture")
                        ),
                        responseFields(
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("userName"),
                                fieldWithPath("data.email").type(JsonFieldType.STRING).description("userEmail"),
                                fieldWithPath("data.role").type(JsonFieldType.STRING).description("GUEST or USER"),
                                fieldWithPath("data.picture").type(JsonFieldType.STRING).description("userPicture"),
                                fieldWithPath("data.temperature").type(JsonFieldType.NUMBER).description("userTemperature"),
                                fieldWithPath("data.phoneNumber").type(JsonFieldType.STRING).description("userPhoneNum"),
                                fieldWithPath("data.address").type(JsonFieldType.STRING).description("userAddress"),
                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 일자"),
                                fieldWithPath("data.updatedAt").type(JsonFieldType.STRING).description("수정 일자"),
                                fieldWithPath("data.deletedAt").type(JsonFieldType.NULL).description("삭제 일자"),
                                fieldWithPath("serverDateTime").type(JsonFieldType.STRING).description("응답시간")
                        )
                ));
    }



    // 삭제

    @Test
    @DisplayName("[DELETE] '/api/v1/member/{id}'")
    void testDeleteCall() throws Exception {
        // GIVEN
        // WHEN
        RequestBuilder request = MockMvcRequestBuilders.delete("/api/v1/member/" + user.getId())
                .session(session)
                .contentType(MediaType.APPLICATION_JSON);

        // THEN
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user-delete",
                        responseFields(
                                fieldWithPath("data").type(JsonFieldType.NULL).description("데이터"),
                                fieldWithPath("serverDateTime").type(JsonFieldType.STRING).description("응답시간")
                        )
                ));

    }




}
