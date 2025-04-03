package com.example.CalendarDevelop.cys.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 일정 생성을 위한 DTO 클래스
 * 사용자로부터 제목과 내용을 받아 일정 생성에 사용됨
 */
@Getter
public class ScheduleSaveRequestDto {

    /**
     * 일정 제목
     * 빈 값은 허용되지 않음
     */
    @NotBlank(message = "제목은 필수값입니다.")
    private String title;

    /**
     * 일정 내용
     * 빈 값은 허용되지 않음
     */
    @NotBlank(message = "내용은 필수값입니다.")
    private String content;
}