package com.example.CalendarDevelop.cys.config;

import com.example.CalendarDevelop.cys.config.filter.AuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 필터 설정 클래스
 * 애플리케이션에서 사용할 필터를 등록하는 설정 클래스
 */
@Configuration
public class FilterConfig {

    /**
     * 인증 필터를 등록하는 빈
     * 모든 URL 요청에 대해 인증 필터를 적용하며, 필터 순서는 1번으로 설정
     * @return 인증 필터 등록 빈
     */
    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter());
        registrationBean.addUrlPatterns("/*");  // 모든 URL에 필터 적용
        registrationBean.setOrder(1);  // 필터 순서 설정 (낮은 번호가 먼저 실행)
        return registrationBean;
    }
}