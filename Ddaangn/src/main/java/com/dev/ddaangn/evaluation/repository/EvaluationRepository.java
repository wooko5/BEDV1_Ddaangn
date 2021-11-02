package com.dev.ddaangn.evaluation.repository;

import com.dev.ddaangn.evaluation.domain.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
}
