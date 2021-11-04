package com.dev.ddaangn.evaluation.domain;

import com.dev.ddaangn.common.BaseEntity;
import com.dev.ddaangn.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "evaluations")
@DynamicUpdate
@Builder
public class Evaluation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "evaluated_id", referencedColumnName = "id")
    private User evaluated;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "evaluator_id", referencedColumnName = "id")
    private User evaluator;

    @OneToMany(mappedBy = "evaluation")
    private List<EvaluationMappingDetail> mappingEvaluationEvaluationDetails = new ArrayList<>();

    // 평가 필드
    private String evaluation;



    public void addMappingEvaluationEvaluationDetail(EvaluationMappingDetail item) {
        mappingEvaluationEvaluationDetails.add(item);
    }

    public void addEvaluated(User user) {
        this.evaluated = user;
        user.getGivenEvaluations().add(this);
    }

    public void addEvaluator(User user) {
        this.evaluator = user;
        user.getGivingEvaluations().add(this);
    }


//    public void setEvaluator(User user) {
//        if (Objects.nonNull(this.evaluator)) {
//            this.evaluator.getGivingEvaluations().remove(this);
//        }
//        this.evaluator=evaluator;
//        user.getGivingEvaluations().add(this);
//    }


//    @OneToOne
//    @JoinColumn(name="post_id")
//    private Post post;
//
//    @OneToMany(mappedBy = "evaluation")
//    private List<EvaluationsDetail> evaluationsDetails = new ArrayList<>();


}
