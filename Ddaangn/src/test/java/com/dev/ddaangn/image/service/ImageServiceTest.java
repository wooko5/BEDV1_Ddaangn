package com.dev.ddaangn.image.service;

import com.dev.ddaangn.image.repository.ImageRepository;
import com.dev.ddaangn.image.dto.ImageDto;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class ImageServiceTest {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;

    private Long id = 1L;

    @BeforeEach
    void setUp(){
        // Given
        ImageDto imageDto = ImageDto.builder()
                .id(id)
                .url("C:/Users/wooko/desktop/image-20211004165424872.png")
                .type("BADGE")
                .build();

        // When
        Long savedId = imageService.save(imageDto);

        // Then
        assertThat(savedId).isEqualTo(id);
    }

    @AfterEach
    void cleanUp(){
        imageRepository.deleteAll();
    }

    @Test
    @DisplayName("단일 조회를 테스트합니다.")
    void findByIdTest() throws NotFoundException {
        // Given
        Long imageId = id;

        // When
        ImageDto imageDto = imageService.findOneById(imageId);

        // Then
        assertThat(imageDto.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("전체 조회를 테스트합니다.")
    void findAllTest() {
        // Given
        PageRequest pageRequest = PageRequest.of(0, 10);

        // When
        Page<ImageDto> all = imageService.findAll(pageRequest);

        // Then
        assertThat(all.getTotalElements()).isEqualTo(id);
    }


//    @Test
//    @DisplayName("수정 작업을 테스트합니다.")
//    void updateTest() {
//        // Given
//
//
//        // When
//
//        // Then
//    }




}