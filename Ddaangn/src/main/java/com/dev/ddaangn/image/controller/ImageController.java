package com.dev.ddaangn.image.controller;

import com.dev.ddaangn.common.api.ApiResponse;
import com.dev.ddaangn.image.dto.ImageDto;
import com.dev.ddaangn.image.service.ImageService;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    @GetMapping("/") // 이미지 다건 조회
    public ApiResponse<Page<ImageDto>> getAll(Pageable pageable) {
        Page<ImageDto> all = imageService.findAll(pageable);
        return ApiResponse.ok(all);
    }


    @GetMapping("/api/v1/images/{id}") // 이미지 단건 조회
    public ApiResponse<ImageDto> getOne(@PathVariable Long id) throws NotFoundException {
        ImageDto imageDto = imageService.findOneById(id);
        return ApiResponse.ok(imageDto);
    }

    // 이미지 작성
    @PostMapping("/api/v1/images") // 게시글 생성
    public ApiResponse<Long> save(@RequestBody ImageDto imageDto) {
        Long id = imageService.save(imageDto);
        return ApiResponse.ok(id);
    }

    // 이미지 수정
    @PutMapping("/api/v1/images/{id}")
    public ApiResponse<ImageDto> update(@RequestBody ImageDto imageDto) throws NotFoundException {
        ImageDto updatedImage = imageService.update(imageDto);
        return ApiResponse.ok(updatedImage);
    }

    // 이미지 삭제
    @DeleteMapping("/api/v1/images/{id}")
    public ApiResponse<Long> delete(@PathVariable Long id) throws NotFoundException {
        imageService.deleteOneById(id);
        return ApiResponse.ok(id);
    }

}
