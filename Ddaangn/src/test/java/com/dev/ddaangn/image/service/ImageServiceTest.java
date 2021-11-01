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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;

@Slf4j
@SpringBootTest
class ImageServiceTest {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;

    private Long id = 1L;

    @BeforeEach
    void setUp() {
        ImageDto imageDto = ImageDto.builder()
                .url("C:/desktop/image-20211004165424872.png")
                .type("BADGE")
                .build();

        id = imageService.save(imageDto);
    }

    @AfterEach
    void cleanUp() {
        imageRepository.deleteAll();
    }

    @Test
    @DisplayName("단일 조회를 테스트합니다.")
    void findByIdTest() throws NotFoundException {
        Long imageId = id; // Given

        ImageDto imageDto = imageService.findOneById(imageId); // When

        assertThat(imageDto.getId()).isEqualTo(id); // Then
    }

    @Test
    @DisplayName("전체 조회를 테스트합니다.")
    void findAllTest() {
        final Long GIVEN_TOTAL_ELEMENT = 1L;
        PageRequest pageRequest = PageRequest.of(0, 10); // Given

        Page<ImageDto> all = imageService.findAll(pageRequest); // When

        assertThat(all.getTotalElements()).isEqualTo(GIVEN_TOTAL_ELEMENT); // Then
    }


    @Test
    @DisplayName("수정 작업을 테스트합니다.")
    void updateTest() throws NotFoundException {
        String url = "C:/desktop/image-2000.png"; // Given
        String type = "AVATAR";

        ImageDto updatedImageDto = imageService.update(id, url, type); // When
        
        assertThat(updatedImageDto.getId()).isEqualTo(id); // Then
        assertThat(updatedImageDto.getUrl()).isEqualTo(url);
        assertThat(updatedImageDto.getType()).isEqualTo(type);
    }

    @Test
    @DisplayName("삭제 작업을 테스트합니다.")
    void deleteTest() throws NotFoundException {
        imageService.deleteOneById(id);
        assertThat(imageRepository.findAll()).isEmpty();
    }
}