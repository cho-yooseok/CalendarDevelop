package com.example.CalendarDevelop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 애플리케이션에서 발생하는 일반적인 예외를 처리하는 클래스
 * 사용자 정의 메시지와 HTTP 상태 코드를 포함함
 */
@Getter
public class ApplicationException extends RuntimeException {
    private final HttpStatus status;

    /**
     * ApplicationException 생성자
     *
     * @param message 예외 메시지
     * @param status HTTP 상태 코드
     */
    public ApplicationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}