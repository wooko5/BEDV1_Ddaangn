package com.dev.ddaangn.evaluation.controller;

import com.dev.ddaangn.evaluation.Evaluation;
import com.dev.ddaangn.evaluation.dto.request.EvaluationRequestDto;
import com.dev.ddaangn.evaluation.repository.EvaluationRepository;
import com.dev.ddaangn.evaluation.service.EvaluationService;
import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.post.domain.PostStatus;
import com.dev.ddaangn.post.dto.request.PostInsertRequest;
import com.dev.ddaangn.post.dto.response.PostInsertResponse;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.vo.BoughtPosts;
import com.dev.ddaangn.user.vo.SoldPosts;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.dev.ddaangn.user.repository.UserRepository;
import com.dev.ddaangn.user.service.TempService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.*;

import org.springframework.http.*;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class EvaluationControllerTest {

    private final Long GIVING_ID = 4L;
    private final Long GIVEN_ID = 5L;
    private final Double USER_TEMPERATURE = 36.0;

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private EvaluationRepository evaluationRepository;

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    private User givingUser;
    private User givenUser;
    private Evaluation evaluation;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @BeforeEach
    void setUp() {
        LocalDateTime now = LocalDateTime.now();
        givingUser = User.builder()
                .id(GIVING_ID)
                .address("my address")
                .name("동성")
                .phoneNumber("010-xxxx-xxxx")
                .temperature(USER_TEMPERATURE)
                .soldPosts(new SoldPosts())
                .boughtPosts(new BoughtPosts())
                .build();
        givenUser = User.builder()
                .id(GIVEN_ID)
                .address("my address")
                .name("일용")
                .phoneNumber("010-xxxx-yyyy")
                .temperature(USER_TEMPERATURE)
                .soldPosts(new SoldPosts())
                .boughtPosts(new BoughtPosts())
                .build();
        givingUser.setCreatedAt(now);
        givingUser.setUpdateAt(now);
        givenUser.setCreatedAt(now);
        givenUser.setUpdateAt(now);
    }


    // 평가해보기
    @Test
    @DisplayName("Session 계정으로 User 평가하기")
    @WithMockUser(roles = "USER")
    public void 평가하기() throws Exception {


        // GIVEN
        EvaluationRequestDto requestDto=EvaluationRequestDto.builder()
                .givingUser(givingUser)
                .givenUser(givenUser)
                .build();

        RequestBuilder request = MockMvcRequestBuilders.put("/api/v1/temperature/evaluation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto));

        // WHEN THEN
        mvc
                .perform(request)
                .andDo(print())
                .andExpect(
                        status().is2xxSuccessful());
    }

}