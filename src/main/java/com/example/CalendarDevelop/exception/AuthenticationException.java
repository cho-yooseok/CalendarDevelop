package com.example.CalendarDevelop.exception;

import org.springframework.http.HttpStatus;

/**
 * 인증 관련 예외를 처리하는 클래스
 * ApplicationException을 상속받아 인증 실패 시 발생하는 예외를 전문적으로 처리
 * HTTP 상태 코드는 401 (UNAUTHORIZED)로 고정
 */
public class AuthenticationException extends ApplicationException {

    /**
     * AuthenticationException 생성자
     *
     * @param message 인증 실패 메시지
     */
    public AuthenticationException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}