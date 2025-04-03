package com.example.CalendarDevelop.cys.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 로그인 요청을 위한 DTO 클래스
 * 사용자로부터 이메일과 비밀번호를 받아 로그인 처리를 위해 사용됨
 */
@Getter
public class LoginRequestDto {

    /**
     * 사용자 이메일
     * 반드시 입력되어야 하며, 이메일 형식을 준수해야 함
     */
    @NotBlank(message = "이메일 입력은 필수입니다")
    @Email(message = "이메일 형식이 올바르지 않습니다")
    private String email;

    /**
     * 사용자 비밀번호
     * 반드시 입력되어야 함
     */
    @NotBlank(message = "비밀번호 입력은 필수입니다")
    private String password;
}