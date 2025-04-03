package com.example.CalendarDevelop.cys.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 회원 정보 수정을 위한 DTO 클래스
 * 사용자로부터 이름과 비밀번호를 받아 회원 정보 업데이트에 사용됨
 */
@Getter
public class MemberUpdateRequestDto {

    /**
     * 수정할 사용자 이름
     * 빈 값은 허용되지 않음
     */
    @NotBlank(message = "name은 필수값입니다.")
    private String name;

    /**
     * 수정을 위한 비밀번호 인증
     * 빈 값은 허용되지 않음
     */
    @NotBlank(message = "password은 필수값입니다.")
    private String password;
}