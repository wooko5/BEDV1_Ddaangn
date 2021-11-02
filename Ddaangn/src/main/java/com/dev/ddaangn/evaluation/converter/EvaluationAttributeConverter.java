package com.dev.ddaangn.evaluation.converter;

import com.dev.ddaangn.evaluation.role.EvaluationStatus;
import com.dev.ddaangn.post.domain.PostStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EvaluationAttributeConverter implements AttributeConverter<EvaluationStatus, Integer>{
    @Override
    public Integer convertToDatabaseColumn(EvaluationStatus evaluationStatus) {
        return evaluationStatus.getValue();
    }

    @Override
    public EvaluationStatus convertToEntityAttribute(Integer integer) {
        return EvaluationStatus.of(integer);
    }

}

