package com.dev.ddaangn.evaluation.service;

import com.dev.ddaangn.common.error.ErrorMessage;
import com.dev.ddaangn.common.error.exception.NotFoundException;
import com.dev.ddaangn.evaluation.Evaluation;
import com.dev.ddaangn.evaluation.dto.request.EvaluationRequestDto;
//import com.dev.ddaangn.evaluation.dto.response.EvaluationResponseDto;
import com.dev.ddaangn.evaluation.repository.EvaluationRepository;
import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.post.dto.response.PostInsertResponse;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import com.dev.ddaangn.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;


    @Transactional
    public Long update(EvaluationRequestDto requestDto, SessionUser sessionUser) {

        // Todo
        if (sessionUser==null) {
            throw new NotFoundException(ErrorMessage.NOT_EXIST_MEMBER);
        }


        User evaluator = getUser(sessionUser.getId());
        User evaluated= getUser(requestDto.getGivenId());

        //
        Evaluation evaluation=requestDto.insertRequestDtoToEntity(evaluator,evaluated);
        evaluation.addEvaluated(evaluated);
        evaluation.addEvaluator(evaluator);

        // 이건 detail에서 사용할것!!!
//        String 평가=requestDto.get평가지표();

        // 일용
//        User user = getUser(request.getSellerId());
//        Post post = postConverter.insertRequestDtoToEntity(request, user);
//        post.addPost(user);
//        Post insertedPost = postRepository.save(post);
//        return new PostInsertResponse(insertedPost);
        //일용


        return evaluationRepository.save(evaluation).getId();
    }

    @Transactional
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_MEMBER));
    }

//    @Transactional
//    public Page<PostDetailResponse> findAll(Pageable pageable) {
//        return postRepository.findAll(pageable)
//                .map(PostDetailResponse::new);
//    }




}
