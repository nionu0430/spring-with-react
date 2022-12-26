package com.coocon.portal.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SpringSecurityConfig {

    private static final String[] AUTH_WHITELIST ={
            "/static/*"
    };

    @Bean
    public BCryptPasswordEncoder encodePassword(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //.exceptionHandling()
                //.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                //.accessDeniedHandler(jwtAccessDeniedHandler)
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()

                //기본적으로 session을 사용하는 Spring security 설정을 Stateless 상태로 변경
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                //
                .and()
                .authorizeRequests()
                .antMatchers("/auth/*").permitAll() //auth/* request에 대한 전체 허가
                .anyRequest().authenticated() //auth 제외 전체 인증 필요

                //.and()
                //.antMatcher("/admin/*")

                .and()
                .formLogin().loginPage("/auth/signIn")
                .defaultSuccessUrl("/"); //로그인 성공 후 기본



                //.and()
                //.apply(new JwtSecurityConfig(tokenProvider));
        return http.build();
    }

}
