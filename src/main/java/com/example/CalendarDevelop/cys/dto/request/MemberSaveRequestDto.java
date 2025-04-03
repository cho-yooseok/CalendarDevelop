package com.example.CalendarDevelop.cys.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 회원 가입을 위한 DTO 클래스
 * 사용자로부터 이름, 이메일, 비밀번호를 받아 회원 생성에 사용됨
 */
@Getter
public class MemberSaveRequestDto {

    /**
     * 사용자 이름
     * 빈 값은 허용되지 않음
     */
    @NotBlank(message = "name은 필수값입니다.")
    private String name;

    /**
     * 사용자 이메일
     * 빈 값은 허용되지 않으며, 이메일 형식을 준수해야 함
     */
    @NotBlank(message = "email은 필수값입니다.")
    @Email(message = "email 형식이 올바르지 않습니다.")
    private String email;

    /**
     * 사용자 비밀번호
     * 빈 값은 허용되지 않음
     */
    @NotBlank(message = "password는 필수값입니다.")
    private String password;
}