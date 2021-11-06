package com.dev.ddaangn;

import com.dev.ddaangn.common.query.InstanceEvaluationQuery;
import com.dev.ddaangn.evaluation.domain.EvaluationsDetail;
import com.dev.ddaangn.evaluation.repository.EvaluationsDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;
import java.util.List;


@EnableJpaAuditing
@SpringBootApplication
public class DdaangnApplication implements CommandLineRunner {



    public static void main(String[] args) {
        SpringApplication.run(DdaangnApplication.class, args);
    }



    // evaluation Detail 데이터 미리 저장
    @Autowired
    private InstanceEvaluationQuery instanceEvaluationQuery;
    @Override
    public void run(String... args) throws Exception {

        instanceEvaluationQuery.insert();

    }
}
