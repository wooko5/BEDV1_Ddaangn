package com.dev.ddaangn.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.dev.ddaangn.user.config.SocialType.GOOGLE;

/**
 * Created by Neon K.I.D on 8/9/20
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/", "/oauth2/**", "/login/**", "/css/**",
                        "/images/**", "/js/**", "/console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login().defaultSuccessUrl("/loginSuccess").failureUrl("/loginFailure")
                .and()
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .and()
                .formLogin().successForwardUrl("/board")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .and()
                .csrf().disable();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(OAuth2ClientProperties properties) {
        List<ClientRegistration> registrations = properties.getRegistration().keySet().stream()
                .map(client -> getRegistration(properties, client))
                .filter(Objects::nonNull).collect(Collectors.toList());

        // 카카오 OAuth2 정보 추가
        registrations.add(
                ThirdOAuth2Provider.KAKAO.getBuilder("kakao")
                        .clientId("dce1233013b2d533befde5995c122c42")
                        .clientSecret("tmp")
                        .jwkSetUri("tmp")
                        .build()
        );

        return new InMemoryClientRegistrationRepository(registrations);
    }

    // Only Google
    private ClientRegistration getRegistration(OAuth2ClientProperties properties, String socialType) {
        if (socialType.equals(GOOGLE.getValue())) {
            OAuth2ClientProperties.Registration registration
                    = properties.getRegistration().get(GOOGLE.getValue());
            return CommonOAuth2Provider.GOOGLE.getBuilder(socialType)
                    .clientId(registration.getClientId())
                    .clientSecret(registration.getClientSecret())
                    .scope("email", "profile")
                    .build();
        }
        return null;
    }
}