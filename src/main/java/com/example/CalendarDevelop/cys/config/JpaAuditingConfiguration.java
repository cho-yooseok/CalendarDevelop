package com.example.CalendarDevelop.cys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA Auditing 설정 클래스
 * 엔티티의 생성일시, 수정일시 등을 자동으로 관리하기 위한 JPA Auditing 기능 활성화
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {
}