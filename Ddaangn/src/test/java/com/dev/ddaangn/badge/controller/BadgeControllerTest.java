package com.dev.ddaangn.badge.controller;

import com.dev.ddaangn.badge.Badge;
import com.dev.ddaangn.badge.BadgeImage;
import com.dev.ddaangn.badge.dto.BadgeImageDto;
import com.dev.ddaangn.badge.dto.BadgeRequest;
import com.dev.ddaangn.badge.dto.BadgeResponse;
import com.dev.ddaangn.badge.repository.BadgeRepository;
import com.dev.ddaangn.badge.service.BadgeService;
import com.dev.ddaangn.user.Role;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.repository.UserRepository;
import com.dev.ddaangn.user.vo.BoughtPosts;
import com.dev.ddaangn.user.vo.SoldPosts;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@AutoConfigureMockMvc
@SpringBootTest
class BadgeControllerTest {

    @Autowired
    BadgeService badgeService;

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .address("myAddress")
                .name("재욱")
                .phoneNumber("010-1234-5678")
                .temperature(36.0)
                .email("weewe@naver.com")
                .role(Role.GUEST)
                .soldPosts(new SoldPosts())
                .boughtPosts(new BoughtPosts())
                .build();
        userRepository.save(user);

        BadgeRequest request = BadgeRequest.builder()
                .name("친절왕")
                .description("거래 이후에 친절한 당신에게 주는 상")
                .achievement(true)
                .userId(user.getId())
                .badgeImageDto(BadgeImageDto.builder()
                        .url("image_url")
                        .type("BADGE")
                        .createdAt(LocalDateTime.now())
                        .deletedAt(LocalDateTime.now())
                        .updateAt(LocalDateTime.now())
                        .build())
                .build();
        badgeService.save(request);
    }

    @AfterEach
    void cleanup() {
        badgeRepository.deleteAll();
    }

    @Test
    @DisplayName("[GET] '/api/v1/badges'")
    void getAllTest() throws Exception {
        mockMvc.perform(get("/api/v1/badges")
                        .param("page", String.valueOf(0))
                        .param("size", String.valueOf(1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("[POST] '/api/v1/badges'")
    void saveTest() throws Exception {
        // Given
        User user = User.builder()
                .address("myAddress")
                .name("프로그래머스")
                .phoneNumber("010-9876-5432")
                .temperature(36.0)
                .email("programmers@gmail.com")
                .role(Role.USER)
                .soldPosts(new SoldPosts())
                .boughtPosts(new BoughtPosts())
                .build();
        userRepository.save(user);

        BadgeRequest request = BadgeRequest.builder()
                .name("불친절왕")
                .description("거래 이후에 불친절한 당신에게 주는 상")
                .achievement(false)
                .userId(user.getId())
                .badgeImageDto(BadgeImageDto.builder()
                        .url("image_url")
                        .type("AVATAR")
                        .createdAt(LocalDateTime.now())
                        .deletedAt(LocalDateTime.now())
                        .updateAt(LocalDateTime.now())
                        .build())
                .build();

        // When, Then
        mockMvc.perform(post("/api/v1/badges")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful()) // 201
                .andDo(print());
    }
}