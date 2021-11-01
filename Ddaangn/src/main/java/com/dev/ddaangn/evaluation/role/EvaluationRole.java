package com.dev.ddaangn.evaluation.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EvaluationRole {

    EXCELLENT("EXCELLENT","완전만족"),
    GOOD("GOOD","완전만족"),
    BAD("BAD","불만족");


    private final String key;
    private final String title;




}
