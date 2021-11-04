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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

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
        BadgeRequest request = BadgeRequest.builder()
                .name("불친절왕")
                .description("거래 이후에 불친절한 당신에게 주는 상")
                .achievement(true)
                .userId(user.getId())
                .badgeImageDto(BadgeImageDto.builder()
                        .url("image_url_new")
                        .type("AVATAR")
                        .createdAt(LocalDateTime.now())
                        .deletedAt(LocalDateTime.now())
                        .updateAt(LocalDateTime.now())
                        .build())
                .build();
        badgeService.save(request);

        mockMvc.perform(get("/api/v1/badges")
                        .param("page", String.valueOf(0))
                        .param("size", String.valueOf(10))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("badge-getAll",
                        responseFields(
                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("data"),
                                fieldWithPath("data.[].badgeId").type(JsonFieldType.NUMBER).description("badgeId"),
                                fieldWithPath("data.[].name").type(JsonFieldType.STRING).description("name"),
                                fieldWithPath("data.[].description").type(JsonFieldType.STRING).description("description"),
                                fieldWithPath("data.[].achievement").type(JsonFieldType.BOOLEAN).description("achievement"),
                                fieldWithPath("data.[].userId").type(JsonFieldType.NUMBER).description("userId"),
                                fieldWithPath("data.[].badgeImage.createdAt").type(JsonFieldType.STRING).description("badgeImage.createdAt"),
                                fieldWithPath("data.[].badgeImage.updateAt").type(JsonFieldType.STRING).description("badgeImage.updateAt"),
                                fieldWithPath("data.[].badgeImage.deletedAt").type(JsonFieldType.NULL).description("badgeImage.deletedAt"),
                                fieldWithPath("data.[].badgeImage.id").type(JsonFieldType.NUMBER).description("badgeImage.id"),
                                fieldWithPath("data.[].badgeImage.url").type(JsonFieldType.STRING).description("badgeImage.url"),
                                fieldWithPath("data.[].badgeImage.type").type(JsonFieldType.STRING).description("badgeImage.type"),
                                fieldWithPath("serverDateTime").type(JsonFieldType.STRING).description("serverDateTime")
                        )
                ));
    }

    @Test
    @DisplayName("[POST] '/api/v1/badges'")
    void saveTest() throws Exception {
        BadgeRequest request = BadgeRequest.builder() // Given
                .name("불친절왕")
                .description("거래 이후에 불친절한 당신에게 주는 상")
                .achievement(false)
                .userId(user.getId())
                .badgeImageDto(BadgeImageDto.builder()
                        .url("image_url_new")
                        .type("AVATAR")
                        .createdAt(LocalDateTime.now())
                        .deletedAt(LocalDateTime.now())
                        .updateAt(LocalDateTime.now())
                        .build())
                .build();
        badgeService.save(request);

        mockMvc.perform(post("/api/v1/badges") // When, Then
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful()) // 201
                .andDo(print())
                .andDo(document("badge-save",
                        requestFields(
                                fieldWithPath("badgeId").type(JsonFieldType.NULL).description("badgeId"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("name"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("description"),
                                fieldWithPath("achievement").type(JsonFieldType.BOOLEAN).description("achievement"),
                                fieldWithPath("badgeImageDto.createdAt").type(JsonFieldType.STRING).description("badgeImageDto.createdAt"),
                                fieldWithPath("badgeImageDto.updateAt").type(JsonFieldType.STRING).description("badgeImageDto.updateAt"),
                                fieldWithPath("badgeImageDto.deletedAt").type(JsonFieldType.STRING).description("badgeImageDto.deletedAt"),
                                fieldWithPath("badgeImageDto.id").type(JsonFieldType.NULL).description("badgeImageDto.id"),
                                fieldWithPath("badgeImageDto.url").type(JsonFieldType.STRING).description("badgeImageDto.url"),
                                fieldWithPath("badgeImageDto.type").type(JsonFieldType.STRING).description("badgeImageDto.type"),
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("userId")
                        ),
                        responseFields(
                                fieldWithPath("data").type(JsonFieldType.NUMBER).description("데이터"),
                                fieldWithPath("serverDateTime").type(JsonFieldType.STRING).description("응답시간")
                        )
                ));
    }
}