package com.dev.ddaangn.image.service;

import com.dev.ddaangn.image.repository.ImageRepository;
import com.dev.ddaangn.image.dto.ImageDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class ImageServiceTest {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;

    private Long id = 1L;

    @Test
    void save(){
        // Given
        ImageDto imageDto = ImageDto.builder()
                .id(id)
                .url("https://wooko5.com")
                .type("BADGE")
                .build();

        // When
        Long savedId = imageService.save(imageDto);

        // Then
        assertThat(savedId).isEqualTo(id);
    }





}