package com.example.CalendarDevelop.cys.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 일정 엔티티 클래스
 * 회원별 일정 정보를 데이터베이스에 저장하는 엔티티
 */
@Entity
@Table(name = "schedule")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    /**
     * 일정 ID (기본키)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 일정 제목
     */
    @Column(nullable = false)
    private String title;

    /**
     * 일정 내용
     */
    @Column(nullable = false)
    private String content;

    /**
     * 일정 소유자 (회원)
     * 지연 로딩 설정으로 일정만 조회하고 회원은 필요시에만 로딩
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    /**
     * 생성일시
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * 수정일시
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * 생성자: 제목, 내용, 회원(소유자) 설정
     * 주로 새 일정 등록 시 사용
     */
    public Schedule(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    /**
     * 일정 정보 업데이트 메서드
     * 제목과 내용을 업데이트하고, 수정일시도 현재 시간으로 갱신
     * @param title 새 제목
     * @param content 새 내용
     */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 엔티티 생성 전 호출되는 라이프사이클 메서드
     * 생성일시와 수정일시를 현재 시간으로 설정
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 엔티티 업데이트 전 호출되는 라이프사이클 메서드
     * 수정일시를 현재 시간으로 갱신
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}