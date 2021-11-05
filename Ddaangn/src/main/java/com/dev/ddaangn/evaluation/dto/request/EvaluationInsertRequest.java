package com.dev.ddaangn.evaluation.dto.request;

import com.dev.ddaangn.evaluation.domain.Evaluation;
import com.dev.ddaangn.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class EvaluationInsertRequest {
    private Long evaluatedId; // 평가 받는 User의 pk
    private List<Long> evaluationDetails;

    // requestDto -> Entity
    public Evaluation insertRequestDtoToEntity(User evaluator, User evaluated) {
        return Evaluation.builder()
                .evaluated(evaluated)
                .evaluator(evaluator)
                .build();
    }
}
