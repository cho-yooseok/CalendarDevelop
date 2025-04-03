package com.example.CalendarDevelop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

/**
 * 캘린더 애플리케이션의 메인 클래스
 * 스프링 부트 애플리케이션을 실행하는 진입점
 * @EnableSpringDataWebSupport: 페이징 기능을 DTO를 통해 직렬화하도록 설정
 */
@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class CalendarDevelopApplication {

    /**
     * 애플리케이션의 진입점
     * 스프링 부트 애플리케이션을 실행
     * @param args 명령줄 인자
     */
    public static void main(String[] args) {
        SpringApplication.run(CalendarDevelopApplication.class, args);
    }
}