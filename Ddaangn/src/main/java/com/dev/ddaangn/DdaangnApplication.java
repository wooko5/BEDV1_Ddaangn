package com.dev.ddaangn;

import com.dev.ddaangn.user.config.UserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.List;

@RequiredArgsConstructor
@EnableJpaAuditing
@SpringBootApplication
//@ComponentScan(basePackages = {"com.dev.ddaangn.user"})
public class DdaangnApplication {

    private final UserArgumentResolver userArgumentResolver;

    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        super.UserArgumentResolver(argumentResolvers);
        argumentResolvers.add(userArgumentResolver);
    }


    public static void main(String[] args) {
        SpringApplication.run(DdaangnApplication.class, args);
    }

}
