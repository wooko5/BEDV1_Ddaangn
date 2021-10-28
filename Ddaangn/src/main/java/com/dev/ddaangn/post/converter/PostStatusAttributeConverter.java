package com.dev.ddaangn.post.converter;

import com.dev.ddaangn.post.domain.PostStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PostStatusAttributeConverter implements AttributeConverter<PostStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PostStatus postStatus) {
        return postStatus.getValue();
    }

    @Override
    public PostStatus convertToEntityAttribute(Integer integer) {
        return PostStatus.of(integer);
    }
}
