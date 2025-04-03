package com.example.CalendarDevelop.cys.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 회원 정보를 반환하기 위한 DTO 클래스
 * 클라이언트에게 회원 정보를 제공할 때 사용됨
 */
@Getter
public class MemberResponseDto {

    /**
     * 회원 ID
     */
    private final Long id;

    /**
     * 회원 이름
     */
    private final String name;

    /**
     * 회원 이메일
     */
    private final String email;

    /**
     * 회원 생성 시간
     */
    private final LocalDateTime createdAt;

    /**
     * 회원 정보 마지막 수정 시간
     */
    private final LocalDateTime updatedAt;

    /**
     * 회원 응답 DTO 생성자
     *
     * @param id 회원 ID
     * @param name 회원 이름
     * @param email 회원 이메일
     * @param createdAt 생성 시간
     * @param updatedAt 수정 시간
     */
    public MemberResponseDto(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}