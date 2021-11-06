package com.dev.ddaangn.evaluation.controller;

import com.dev.ddaangn.evaluation.domain.EvaluationsDetail;
import com.dev.ddaangn.evaluation.dto.request.EvaluationInsertRequest;
import com.dev.ddaangn.evaluation.repository.EvaluationsDetailRepository;
import com.dev.ddaangn.evaluation.role.EvaluationStatus;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import com.dev.ddaangn.user.repository.UserRepository;
import com.dev.ddaangn.user.role.LoginRole;
import com.dev.ddaangn.user.vo.BoughtPosts;
import com.dev.ddaangn.user.vo.SoldPosts;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs
public class EvaluationControllerInstanceTest {


    private final Double USER_TEMPERATURE = 36.0;
    protected MockHttpSession session;

    @Autowired
    private WebApplicationContext context;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private User givingUser;
    private User givenUser;
    private User givingUserEntity;
    private User givenUserEntity;

    private SessionUser sessionUser;

    @BeforeEach
    public void setUp() {

        LocalDateTime now = LocalDateTime.now();

        givingUser = User.builder()
                // .id(GIVING_ID)
                .address("my address")
                .name("test")
                .phoneNumber("010-xxxx-xxxx")
                .email("sds1vrk@naver.com")
                .temperature(USER_TEMPERATURE)
                .soldPosts(new SoldPosts())
                .boughtPosts(new BoughtPosts())
                .role(LoginRole.USER)
                .build();
        givenUser = User.builder()
                // .id(GIVEN_ID)
                .address("my address")
                .name("일용")
                .email("1yongs_@naver.com")
                .phoneNumber("010-xxxx-yyyy")
                .temperature(USER_TEMPERATURE)
                .soldPosts(new SoldPosts())
                .boughtPosts(new BoughtPosts())
                .role(LoginRole.USER)
                .build();
        givingUserEntity = userRepository.save(givingUser);
        givenUserEntity = userRepository.save(givenUser);

        session = new MockHttpSession();
        sessionUser = new SessionUser();
        sessionUser.setId(givingUserEntity.getId());
        session.setAttribute("user", sessionUser);

    }

    // Detail List 조회하기
    @Test
    @DisplayName("미리 만들어져있는 Detail List 조회하기")
    public void DetailList조회하기() throws Exception {
        // GIVEN


        RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/evaluations/details")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON);

        // WHEN THEN
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("evaluation-detailAll",
                        responseFields(
//                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("data"),
                                fieldWithPath("data.pageable.sort.empty").type(JsonFieldType.BOOLEAN).description("sortEmpty"),
                                fieldWithPath("data.pageable.sort.sorted").type(JsonFieldType.BOOLEAN).description("sorted"),
                                fieldWithPath("data.pageable.sort.unsorted").type(JsonFieldType.BOOLEAN).description("unsorted"),


                                fieldWithPath("data.pageable.offset").type(JsonFieldType.NUMBER).description("sortEmpty"),
                                fieldWithPath("data.pageable.pageNumber").type(JsonFieldType.NUMBER).description("sorted"),
                                fieldWithPath("data.pageable.pageSize").type(JsonFieldType.NUMBER).description("unsorted"),
                                fieldWithPath("data.pageable.unpaged").type(JsonFieldType.BOOLEAN).description("unpaged"),
                                fieldWithPath("data.pageable.paged").type(JsonFieldType.BOOLEAN).description("paged"),

                                fieldWithPath("data.pageable").type(JsonFieldType.OBJECT).description("instance"),
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

                                fieldWithPath("data.content.[].id").type(JsonFieldType.NUMBER).description("id"),
                                fieldWithPath("data.content.[].description").type(JsonFieldType.STRING).description("description"),
                                fieldWithPath("data.content.[].positive").type(JsonFieldType.BOOLEAN).description("positive"),
                                fieldWithPath("data.content.[].createdAt").type(JsonFieldType.STRING).description("생성 일자"),
                                fieldWithPath("data.content.[].updatedAt").type(JsonFieldType.STRING).description("수정 일자"),
                                fieldWithPath("data.content.[].deletedAt").type(JsonFieldType.NULL).description("삭제 일자"),
                                fieldWithPath("serverDateTime").type(JsonFieldType.STRING).description("응답시간")
                        )
                ));
    }




    // 평가해보기
    @Test
    @DisplayName("Session 계정으로 User 평가하기")
    @WithMockUser(roles = "USER")
    public void 평가하기() throws Exception {
        // GIVEN
        EvaluationInsertRequest requestDto = EvaluationInsertRequest.builder()
                .evaluatedId(givenUserEntity.getId())
                .evaluation(String.valueOf(EvaluationStatus.EXCELLENT))
                .evaluationDetails(
                        Arrays.asList(
                                1L,
                                2L
                        )
                )
                .build();

        RequestBuilder request = MockMvcRequestBuilders.post("/api/v1/evaluations")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto));

        // WHEN THEN
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(document("evaluation-save",
                        requestFields(

                                fieldWithPath("evaluatedId").type(JsonFieldType.NUMBER).description("evaluatedId"),
                                fieldWithPath("evaluationDetails").type(JsonFieldType.ARRAY).description("evaluationDetails"),
                                fieldWithPath("evaluation").type(JsonFieldType.STRING).description("evaluation")
                        ),
                        responseFields(
                                fieldWithPath("data.[].createdAt").type(JsonFieldType.STRING).description("생성 일자"),
                                fieldWithPath("data.[].updatedAt").type(JsonFieldType.STRING).description("수정 일자"),
                                fieldWithPath("data.[].deletedAt").type(JsonFieldType.NULL).description("삭제 일자"),
                                fieldWithPath("data.[].id").type(JsonFieldType.NUMBER).description("userName"),
                                fieldWithPath("data.[].evaluationId").type(JsonFieldType.NUMBER).description("userName"),
                                fieldWithPath("data.[].evaluationDetail").type(JsonFieldType.NUMBER).description("userName"),
                                fieldWithPath("serverDateTime").type(JsonFieldType.STRING).description("응답시간")

                        )
                ));
    }
}
