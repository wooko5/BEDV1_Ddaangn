package com.dev.ddaangn.image.service;

import com.dev.ddaangn.common.error.ErrorMessage;
import com.dev.ddaangn.image.Image;
import com.dev.ddaangn.common.error.exception.NotFoundException;
import com.dev.ddaangn.image.repository.ImageRepository;
import com.dev.ddaangn.image.converter.ImageConverter;
import com.dev.ddaangn.image.dto.ImageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final ImageConverter imageConverter;

    public ImageService(ImageRepository imageRepository, ImageConverter imageConverter) {
        this.imageRepository = imageRepository;
        this.imageConverter = imageConverter;
    }

    @Transactional
    public Long save(ImageDto imageDto) {
        Image image = imageConverter.converterImage(imageDto);
        Image savedImage = imageRepository.save(image);
        return savedImage.getId();
    }

    @Transactional
    public ImageDto update(Long id, String url, String type) throws NotFoundException {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isEmpty()) {
            throw new NotFoundException(ErrorMessage.NOT_EXIST_IMAGE);
        }
        Image entity = image.get();
        entity.setUrl(url);
        entity.setType(type);
        return imageConverter.converterImageDto(entity);
    }

    @Transactional
    public ImageDto findOneById(Long id) {
        // 1. 이미지 조회를 위한 아이디를 인자로 받기
        // 2. imageRepository.findById(id) -> 조회 (영속화된 엔티티)
        return imageRepository.findById(id)
                .map(imageConverter::converterImageDto) // 3. entity -> dto
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_IMAGE));
    }

    @Transactional
    public Page<ImageDto> findAll(Pageable pageable) {
        return imageRepository.findAll(pageable)
                .map(imageConverter::converterImageDto);
    }

    @Transactional
    public void deleteOneById(Long id) throws NotFoundException {
        if (imageRepository.existsById(id)) { 
            throw new NotFoundException(ErrorMessage.NOT_EXIST_IMAGE);
        }
        imageRepository.deleteById(id);
    }
}
