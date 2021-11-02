package com.dev.ddaangn.evaluation.dto.request;

import com.dev.ddaangn.evaluation.Evaluation;
import com.dev.ddaangn.evaluation.role.EvaluationStatus;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.config.auth.LoginUser;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EvaluationRequestDto {

    Long givenId;

    User givingUser;
    User givenUser;

    // 평가 지표
//    String 평가지표;

    @Builder
    public EvaluationRequestDto(User givingUser, User givenUser) {
        this.givingUser = givingUser;
        this.givenUser = givenUser;
    }

    // reuqestDto에는 누굴평가할것인지에 대한 정보가 들어간다다
    // requestDto -> Entity
    public Evaluation insertRequestDtoToEntity(User givingUser, User givenUser) {
        return Evaluation.builder()
                .givenUser(givingUser)
                .givingUser(givenUser)
//                .evaluationsDetails()
//                .post()
                .build();
    }






}
