package com.example.CalendarDevelop.cys.service;

import com.example.CalendarDevelop.cys.dto.request.LoginRequestDto;
import com.example.CalendarDevelop.cys.entity.Member;
import com.example.CalendarDevelop.cys.repository.MemberRepository;
import com.example.CalendarDevelop.exception.AuthenticationException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 인증 관련 비즈니스 로직을 담당하는 서비스 클래스
 * 로그인, 로그아웃, 인증 상태 확인 등의 기능 제공
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    /**
     * 회원 정보 리포지토리
     */
    private final MemberRepository memberRepository;

    /**
     * 세션에 저장될 로그인 사용자 ID의 키
     */
    private static final String USER_SESSION_KEY = "LOGGED_IN_USER";

    /**
     * 로그인 처리 메서드
     *
     * @param requestDto 로그인 요청 정보
     * @param session HTTP 세션
     * @return 로그인된 회원 정보
     * @throws AuthenticationException 인증 실패 시 예외 발생
     */
    @Transactional(readOnly = true)
    public Member login(LoginRequestDto requestDto, HttpSession session) {
        Member member = memberRepository.findByEmailAndPassword(requestDto.getEmail(), requestDto.getPassword())
                .orElseThrow(() -> new AuthenticationException("이메일 또는 비밀번호가 일치하지 않습니다."));

        // 세션에 회원 ID 저장
        session.setAttribute(USER_SESSION_KEY, member.getId());

        return member;
    }

    /**
     * 로그아웃 처리 메서드
     *
     * @param session HTTP 세션
     */
    public void logout(HttpSession session) {
        session.removeAttribute(USER_SESSION_KEY);
        session.invalidate();
    }

    /**
     * 현재 로그인한 사용자의 ID를 반환
     *
     * @param session HTTP 세션
     * @return 로그인한 사용자 ID, 로그인 상태가 아니면 null
     */
    public static Long getLoggedInUserId(HttpSession session) {
        Object attribute = session.getAttribute(USER_SESSION_KEY);
        if (attribute == null) {
            return null;
        }
        return (Long) attribute;
    }
}