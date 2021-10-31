package com.dev.ddaangn.image.converter;

import com.dev.ddaangn.image.Image;
import com.dev.ddaangn.image.dto.ImageDto;
import org.springframework.stereotype.Component;

@Component
public class ImageConverter {

    public Image converterImage(ImageDto imageDto) { // ImageDto -> Image
        return Image.builder()
                .id(imageDto.getId())
                .url(imageDto.getUrl())
                .type(imageDto.getType())
                .build();
    }

    public ImageDto converterImageDto(Image image) { // Image -> ImageDto
        return ImageDto.builder()
                .id(image.getId())
                .url(image.getUrl())
                .type(image.getType())
                .build();
    }
}
