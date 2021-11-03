package com.dev.ddaangn.evaluation.domain;

import com.dev.ddaangn.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "mapping_evaluation_evaluation_detail")
public class EvaluationMappingDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "evaluation_id", referencedColumnName = "id")
    private Evaluation evaluation;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "evaluation_detail_id", referencedColumnName = "id")
    private EvaluationsDetail evaluationsDetail;

    public void addEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
        evaluation.addMappingEvaluationEvaluationDetail(this);
    }

    public void addEvaluationsDetail(EvaluationsDetail evaluationsDetail) {
        this.evaluationsDetail = evaluationsDetail;
        evaluationsDetail.addMappingEvaluationEvaluationDetail(this);
    }
}
