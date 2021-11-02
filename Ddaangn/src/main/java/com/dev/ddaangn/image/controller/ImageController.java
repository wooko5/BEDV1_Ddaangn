package com.dev.ddaangn.image.controller;

import com.dev.ddaangn.common.api.ApiResponse;
import com.dev.ddaangn.image.dto.ImageDto;
import com.dev.ddaangn.image.service.ImageService;
import com.dev.ddaangn.common.error.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    @GetMapping() // 이미지 다건 조회
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Page<ImageDto>> getAll(Pageable pageable) {
        Page<ImageDto> all = imageService.findAll(pageable);
        return ApiResponse.ok(all);
    }


    @GetMapping("/{id}") // 이미지 단건 조회
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ImageDto> getOne(@PathVariable Long id){
        ImageDto imageDto = imageService.findOneById(id);
        return ApiResponse.ok(imageDto);
    }

    // 이미지 작성
    @PostMapping() // 게시글 생성
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> save(@RequestBody ImageDto imageDto) {
        Long id = imageService.save(imageDto);
        return ApiResponse.ok(id);
    }

    // 이미지 수정
    @PutMapping("/{id}")
    public ApiResponse<ImageDto> update(@RequestBody ImageDto imageDto, @PathVariable Long id) throws NotFoundException {
        ImageDto updatedImage = imageService.update(id, imageDto.getUrl(), imageDto.getType());
        return ApiResponse.ok(updatedImage);
    }

    // 이미지 삭제
    @DeleteMapping("/{id}")
    public ApiResponse<Long> delete(@PathVariable Long id) throws NotFoundException {
        imageService.deleteOneById(id);
        return ApiResponse.ok(id);
    }

}
