package com.dev.ddaangn.evaluation.service;

import com.dev.ddaangn.common.error.ErrorMessage;
import com.dev.ddaangn.common.error.exception.NotFoundException;
import com.dev.ddaangn.evaluation.domain.Evaluation;
import com.dev.ddaangn.evaluation.domain.EvaluationMappingDetail;
import com.dev.ddaangn.evaluation.domain.EvaluationsDetail;
import com.dev.ddaangn.evaluation.dto.request.EvaluationInsertRequest;
import com.dev.ddaangn.evaluation.dto.response.EvaluationDetailResponse;
import com.dev.ddaangn.evaluation.dto.response.EvaluationMappingDetailResponse;
import com.dev.ddaangn.evaluation.repository.EvaluationMappingDetailRepository;
import com.dev.ddaangn.evaluation.repository.EvaluationRepository;
import com.dev.ddaangn.evaluation.repository.EvaluationsDetailRepository;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import com.dev.ddaangn.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;
    private final EvaluationsDetailRepository evaluationsDetailRepository;
    private final EvaluationMappingDetailRepository mappingEvaluationEvaluationDetailRepository;

    @Transactional
    public List<EvaluationMappingDetailResponse> createEvaluation(EvaluationInsertRequest request, SessionUser sessionUser) {
        // 1. User찾고
        User evaluator = getUser(sessionUser.getId());
        User evaluated = getUser(request.getEvaluatedId());
        String evaluate=request.getEvaluation();

        // 2. Evaluation Entity
        Evaluation evaluation = request.insertRequestDtoToEntity(evaluator, evaluated,evaluate);
        // 3. EvaluationDetails 찾고
        // 4. evaluation이랑 List<> detail에 대해 mappingEvaluationEvaluationDetail 엔티티들 만들기.
        return getEvaluationsDetail(request.getEvaluationDetails()).stream().map(detail ->
                mappingEvaluationEvaluationDetailRepository.save(
                        EvaluationMappingDetail.builder()
                                .evaluationsDetail(detail)
                                .evaluation(evaluation)
                                .build()
                )).map(EvaluationMappingDetailResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_MEMBER));
    }

    @Transactional
    public List<EvaluationsDetail> getEvaluationsDetail(List<Long> detailIds) {
        // TODO: 없는 ID에 대해서는 어떡하게?
        return evaluationsDetailRepository.findAllById(detailIds);
    }

//    @Transactional
//    public Pageable<EvaluationDetailResponse> getEvaluationsDetails(Pageable pageable) {
//        return evaluationsDetailRepository.findAll();
//    }




}
