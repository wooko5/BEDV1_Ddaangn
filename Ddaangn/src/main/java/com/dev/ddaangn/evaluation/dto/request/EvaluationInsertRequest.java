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
    private List<Long> evaluationDetails; // 항목에 들어갈 리스트들 ex) 최고에요 - 시간 약속을 잘 지켜요, 가격인하해줬어요.. 등
    private String evaluation;

    // requestDto -> Entity
    public Evaluation insertRequestDtoToEntity(User evaluator, User evaluated,String evaluation) {
        return Evaluation.builder()
                .evaluated(evaluated)
                .evaluator(evaluator)
                .evaluation(evaluation)
                .build();
    }
}
