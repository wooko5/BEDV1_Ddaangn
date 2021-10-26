package com.dev.ddaangn.evaluation;

import com.dev.ddaangn.post.Post;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.dev.ddaangn.evaluation.*;

@NoArgsConstructor
@Getter
@Entity(name = "evaluations")
@DynamicUpdate
public class Evaluation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "evaluatee_id", referencedColumnName = "id")
    private User evaluatee;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "evaluator_id", referencedColumnName = "id")
    private User evaluator;

    @OneToOne
    @JoinColumn(name="post_id")
    private Post post;

    @OneToMany(mappedBy = "evaluation")
    private List<EvaluationsDetail> evaluationsDetails = new ArrayList<>();
}
