package com.dev.ddaangn.evaluation.domain;

import com.dev.ddaangn.common.BaseEntity;
import com.dev.ddaangn.evaluation.role.EvaluationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity(name = "evaluations_details")
@Builder
@AllArgsConstructor
@DynamicUpdate
public class EvaluationsDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private Boolean positive;

//    TODO
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "evaluation_id", referencedColumnName = "id")
//    private Evaluation evaluation;

    @OneToMany(mappedBy = "evaluationsDetail")
    private List<EvaluationMappingDetail> mappingEvaluationEvaluationDetails = new ArrayList<>();

    public void addMappingEvaluationEvaluationDetail(EvaluationMappingDetail item) {
        mappingEvaluationEvaluationDetails.add(item);
    }

    public EvaluationsDetail(String description, Boolean positive) {
        this.description = description;
        this.positive = positive;
    }
}
