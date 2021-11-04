package com.dev.ddaangn.evaluation.role;

import com.dev.ddaangn.common.error.ErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum EvaluationStatus {

    EXCELLENT("매우 만족", 1),
    GOOD("만족", 2),
    BAD("불만", 3);

    private final String status;
    private final int val;

}
