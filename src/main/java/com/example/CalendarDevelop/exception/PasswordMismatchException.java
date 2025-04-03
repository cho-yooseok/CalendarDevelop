package com.example.CalendarDevelop.exception;

import org.springframework.http.HttpStatus;

/**
 * 비밀번호 불일치 예외를 처리하는 클래스
 * ApplicationException을 상속받아 비밀번호 확인 실패 시 발생하는 예외를 전문적으로 처리
 * HTTP 상태 코드는 400 (BAD_REQUEST)로 고정
 */
public class PasswordMismatchException extends ApplicationException {

    /**
     * PasswordMismatchException 생성자
     * 기본 메시지 "비밀번호가 일치하지 않습니다."로 설정
     */
    public PasswordMismatchException() {
        super("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
    }
}