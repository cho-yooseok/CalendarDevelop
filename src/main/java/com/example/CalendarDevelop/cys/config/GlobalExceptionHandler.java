package com.example.CalendarDevelop.cys.config;

import com.example.CalendarDevelop.exception.ApplicationException;
import com.example.CalendarDevelop.exception.AuthenticationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 글로벌 예외 처리 클래스
 * 애플리케이션 전체에서 발생하는 예외를 일관된 방식으로 처리하는 클래스
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ApplicationException 처리 메서드
     * 애플리케이션에서 정의한 예외 처리
     * @param ex 발생한 ApplicationException
     * @return 에러 응답
     */
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Map<String, Object>> handleApplicationException(ApplicationException ex) {
        return getErrorResponse(ex.getStatus(), ex.getMessage());
    }

    /**
     * AuthenticationException 처리 메서드
     * 인증 관련 예외 처리
     * @param ex 발생한 AuthenticationException
     * @return 에러 응답
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleAuthenticationException(AuthenticationException ex) {
        return getErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    /**
     * 유효성 검사 실패 예외 처리 메서드
     * @Valid 어노테이션으로 검증 실패 시 발생하는 예외 처리
     * @param ex 발생한 MethodArgumentNotValidException
     * @return 에러 응답 (필드별 에러 메시지가 포함된 응답)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now().toString());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", HttpStatus.BAD_REQUEST.name());
        errorResponse.put("code", "C001");
        errorResponse.put("message", "잘못된 입력값입니다");
        errorResponse.put("path", "/api/login");

        // 필드별 에러 메시지 수집
        var fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> {
                    Map<String, String> error = new HashMap<>();
                    error.put("field", fe.getField());
                    error.put("message", fe.getDefaultMessage());
                    return error;
                })
                .collect(Collectors.toList());

        errorResponse.put("fieldErrors", fieldErrors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * 에러 응답 생성 유틸리티 메서드
     * 공통 에러 응답 형식을 생성
     * @param status HTTP 상태 코드
     * @param message 에러 메시지
     * @return 에러 응답
     */
    private ResponseEntity<Map<String, Object>> getErrorResponse(HttpStatus status, String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", status.name());
        errorResponse.put("code", status.value());
        errorResponse.put("message", message);

        return new ResponseEntity<>(errorResponse, status);
    }
}