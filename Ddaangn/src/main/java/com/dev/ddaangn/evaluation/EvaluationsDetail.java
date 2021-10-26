package com.dev.ddaangn.evaluation;

import com.dev.ddaangn.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity(name = "evaluations_details")
@DynamicUpdate
public class EvaluationsDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private Boolean positive;

    //TODO
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "evaluation_id", referencedColumnName = "id")
    private Evaluation evaluation;
}
