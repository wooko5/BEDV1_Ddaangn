package com.dev.ddaangn.badge.service;

import com.dev.ddaangn.badge.Badge;
import com.dev.ddaangn.badge.BadgeImage;
import com.dev.ddaangn.badge.dto.BadgeRequest;
import com.dev.ddaangn.badge.dto.BadgeResponse;
import com.dev.ddaangn.badge.repository.BadgeRepository;
import com.dev.ddaangn.image.dto.ImageDto;
import com.dev.ddaangn.image.repository.ImageRepository;
import com.dev.ddaangn.image.service.ImageService;
import com.dev.ddaangn.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class BadgeServiceTest {

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private BadgeService badgeService;

    private final Long id = 1L;

    private List<BadgeImage> badgeImages = new ArrayList<>();

    private User user = new User();

    @Test
    void setUp() {
        BadgeRequest request = BadgeRequest.builder() // Given
                .name("오늘의 친절상")
                .description("오늘의 친절상에 대한 상세 설명")
                .badgeImages(badgeImages)
                .userId(id)
                .build();

        BadgeResponse savedResponse = badgeService.save(request); // When

        assertThat(savedResponse).isEqualTo(id); // Then
    }
}