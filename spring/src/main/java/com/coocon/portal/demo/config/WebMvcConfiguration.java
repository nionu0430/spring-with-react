package com.coocon.portal.demo.config;

import com.coocon.portal.demo.filter.LoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
/*
    @Bean
    public FilterRegistrationBean filterBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new LoggingFilter());
        return  registrationBean;
    }

 */
}
