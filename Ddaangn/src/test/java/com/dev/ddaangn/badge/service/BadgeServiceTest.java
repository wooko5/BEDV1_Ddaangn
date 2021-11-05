package com.dev.ddaangn.badge.service;

import com.dev.ddaangn.badge.dto.BadgeImageDto;
import com.dev.ddaangn.badge.dto.BadgeRequest;
import com.dev.ddaangn.badge.dto.BadgeResponse;
import com.dev.ddaangn.badge.repository.BadgeRepository;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.repository.UserRepository;
import com.dev.ddaangn.user.role.LoginRole;
import com.dev.ddaangn.user.vo.BoughtPosts;
import com.dev.ddaangn.user.vo.SoldPosts;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class BadgeServiceTest {

    private final Long USER_ID = 1L;
    @Autowired
    private BadgeRepository badgeRepository;
    @Autowired
    private BadgeService badgeService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .id(USER_ID)
                .address("myAddress")
                .name("재욱")
                .phoneNumber("010-1234-5678")
                .temperature(36.0)
                .email("weewe@naver.com")
                .role(LoginRole.GUEST)
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
    void cleanUp() {
        badgeRepository.deleteAll();
    }

    @Test
    @DisplayName("Badge를 전체 조회할 수 있다")
    void findAllTest() {
        List<BadgeResponse> badgeResponses = badgeService.findAll();
        assertThat(badgeResponses.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Badge를 생성할 수 있다")
    void insertTest() {
        User user = User.builder()
                .address("myAddress")
                .name("프로그래머스")
                .phoneNumber("010-9876-5432")
                .temperature(36.0)
                .email("programmers@gmail.com")
                .role(LoginRole.USER)
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

        BadgeResponse savedBadge = badgeService.save(request);
        assertThat(savedBadge.getName()).isEqualTo("불친절왕");
        assertThat(savedBadge.getDescription()).isEqualTo("거래 이후에 불친절한 당신에게 주는 상");
        assertThat(savedBadge.isAchievement()).isFalse();
    }

}