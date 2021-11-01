package com.dev.ddaangn.badge.service;

import com.dev.ddaangn.badge.BadgeImage;
import com.dev.ddaangn.badge.dto.BadgeImageDto;
import com.dev.ddaangn.badge.dto.BadgeRequest;
import com.dev.ddaangn.badge.dto.BadgeResponse;
import com.dev.ddaangn.badge.repository.BadgeRepository;
import com.dev.ddaangn.user.Role;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class BadgeServiceTest {

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private BadgeService badgeService;

    @Autowired
    private UserRepository userRepository;

    private Long id = 1L;

    private List<BadgeImage> badgeImages = new ArrayList<>();

    private List<BadgeImageDto> badgeImageDtos = new ArrayList<>();

//    private MockMvc mvc;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;

    @Test
    void setUp() {
        User user = User.builder()
                .name("재욱")
                .email("yahoo@naver.com")
                .address("Seoul/Asia")
                .picture("c:wooko/img12312.png")
                .phoneNumber("010-6693-1234")
                .temperature(36.0)
                .role(Role.GUEST)
                .build();
        userRepository.save(user);

        BadgeRequest request = BadgeRequest.builder() // Given
                .name("오늘의 친절상")
                .description("오늘의 친절상에 대한 상세 설명")
                .achievement(true)
                .badgeImageDto(badgeImageDtos)
                .userId(user.getId())
                .build();

        BadgeResponse savedResponse = badgeService.save(request); // When
        assertThat(savedResponse.getName()).isEqualTo("오늘의 친절상"); // Then
    }

    @AfterEach
    void cleanUp() {
        badgeRepository.deleteAll();
        userRepository.deleteAll();
    }
}