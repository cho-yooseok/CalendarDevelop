package com.example.CalendarDevelop.cys.repository;

import com.example.CalendarDevelop.cys.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 일정 정보에 대한 데이터베이스 액세스를 제공하는 리포지토리 인터페이스
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /**
     * 특정 회원의 모든 일정을 조회
     *
     * @param memberId 조회할 회원 ID
     * @return 해당 회원의 모든 일정 목록
     */
    List<Schedule> findByMemberId(Long memberId);
}