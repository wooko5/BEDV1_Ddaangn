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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
public class EvaluationControllerTest {


    private final Long GIVING_ID = 4L;
    private final Long GIVEN_ID = 5L;
    private final Double USER_TEMPERATURE = 36.0;
    protected MockHttpSession session;

    @Autowired
    private WebApplicationContext context;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EvaluationsDetailRepository evaluationsDetailRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private User givingUser;
    private User givenUser;
    private User givingUserEntity;
    private User givenUserEntity;
    private EvaluationsDetail evaluationsDetailEntity1;
    private EvaluationsDetail evaluationsDetailEntity2;
    private EvaluationsDetail evaluationsDetailEntity3;


    private SessionUser sessionUser;

    @BeforeEach
    public void setUp() {


        LocalDateTime now = LocalDateTime.now();


//        this.mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();

        // mock 한글깨짐 방지
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();



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
        log.info("[제발제발] id: " + givenUserEntity.toString());
        log.info("[제발제발] name: " + givenUserEntity.getName());


        session = new MockHttpSession();
        sessionUser = new SessionUser();
        sessionUser.setId(givingUserEntity.getId());
        session.setAttribute("user", sessionUser);


        evaluationsDetailEntity1 = evaluationsDetailRepository.save(
                EvaluationsDetail.builder()
                        .description("빨라!")
                        .positive(true)
                        .build()
        );
        evaluationsDetailEntity2 = evaluationsDetailRepository.save(
                EvaluationsDetail.builder()
                        .description("싸게 팝니다.")
                        .positive(true)
                        .build()
        );
        evaluationsDetailEntity3 = evaluationsDetailRepository.save(
                EvaluationsDetail.builder()
                        .description("욕씀 막 때려")
                        .positive(false)
                        .build()
        );
    }


    // 평가해보기
    @Test
    @DisplayName("Session 계정으로 User 평가하기")
    @WithMockUser(roles = "USER")
    public void 평가하기() throws Exception {
        // GIVEN
        EvaluationInsertRequest requestDto = EvaluationInsertRequest.builder()
                .evaluatedId(givenUserEntity.getId())
                .evaluation(String.valueOf(EvaluationStatus.GOOD))
                .evaluationDetails(
                        Arrays.asList(
                                evaluationsDetailEntity1.getId(),
                                evaluationsDetailEntity2.getId()
                        )
                )
                .build();

        RequestBuilder request = MockMvcRequestBuilders.post("/api/v1/evaluations")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto));

        // WHEN THEN
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(
                        status().is2xxSuccessful());
    }

}