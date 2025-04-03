package com.example.CalendarDevelop.cys.repository;

import com.example.CalendarDevelop.cys.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 회원 정보에 대한 데이터베이스 액세스를 제공하는 리포지토리 인터페이스
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 이메일과 비밀번호로 회원을 조회
     *
     * @param email 회원 이메일
     * @param password 회원 비밀번호
     * @return 일치하는 회원 (없을 경우 빈 Optional)
     */
    Optional<Member> findByEmailAndPassword(String email, String password);

    /**
     * 이메일 중복 확인을 위한 메서드
     *
     * @param email 확인할 이메일
     * @return 해당 이메일이 존재하면 true, 없으면 false
     */
    boolean existsByEmail(String email);
}