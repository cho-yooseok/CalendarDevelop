package com.example.CalendarDevelop.cys.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 기본 시간 엔티티 추상 클래스
 * 모든 엔티티에서 공통으로 사용하는 생성일시, 수정일시 필드를 관리하는 클래스
 * JPA Auditing 기능을 통해 시간 필드가 자동으로 관리됨
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    /**
     * 생성일시
     * 엔티티가 생성될 때 자동으로 설정되며, 이후에는 변경 불가
     */
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * 수정일시
     * 엔티티가 수정될 때마다 자동으로 업데이트됨
     */
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}