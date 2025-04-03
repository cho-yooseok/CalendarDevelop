package com.example.CalendarDevelop.cys.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 회원 엔티티 클래스
 * 회원 정보를 데이터베이스에 저장하는 엔티티
 */
@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    /**
     * 회원 ID (기본키)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 회원 이름
     */
    @Column(nullable = false)
    private String name;

    /**
     * 회원 이메일 (고유 키)
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * 회원 비밀번호
     */
    @Column(nullable = false)
    private String password;

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
     * 생성자: ID, 이름, 이메일만 설정
     */
    public Member(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * 생성자: 이름, 이메일, 비밀번호만 설정
     * 주로 새 회원 등록 시 사용
     */
    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * 생성자: ID, 이름, 이메일, 생성일시, 수정일시 설정
     */
    public Member(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * 회원 정보 업데이트 메서드
     * 이름과 비밀번호를 업데이트하고, 수정일시도 현재 시간으로 갱신
     * @param name 새 이름
     * @param password 새 비밀번호
     */
    public void update(String name, String password) {
        this.name = name;
        this.password = password;
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