package com.example.CalendarDevelop.cys.dto.response;

import com.example.CalendarDevelop.cys.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 일정 정보를 반환하기 위한 DTO 클래스
 * 클라이언트에게 일정 정보를 제공할 때 사용됨
 */
@Getter
public class ScheduleResponseDto {

    /**
     * 일정 ID
     */
    private final Long id;

    /**
     * 일정 제목
     */
    private final String title;

    /**
     * 일정 내용
     */
    private final String content;

    /**
     * 일정 소유자(회원) 이름
     */
    private final String memberName;

    /**
     * 일정 소유자(회원) ID
     */
    private final Long memberId;

    /**
     * 일정 생성 시간
     */
    private final LocalDateTime createdAt;

    /**
     * 일정 마지막 수정 시간
     */
    private final LocalDateTime updatedAt;

    /**
     * 일정 엔티티로부터 응답 DTO 생성
     *
     * @param schedule 일정 엔티티
     */
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.memberName = schedule.getMember().getName();
        this.memberId = schedule.getMember().getId();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}