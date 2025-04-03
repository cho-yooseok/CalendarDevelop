package com.example.CalendarDevelop.cys.controller;

import com.example.CalendarDevelop.cys.dto.request.LoginRequestDto;
import com.example.CalendarDevelop.cys.dto.response.MemberResponseDto;
import com.example.CalendarDevelop.cys.entity.Member;
import com.example.CalendarDevelop.cys.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 인증 관련 컨트롤러
 * 로그인, 로그아웃 기능을 처리하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 로그인 처리 메서드
     * 사용자 이메일과 비밀번호를 검증하고 세션에 사용자 정보를 저장
     * @param requestDto 로그인 요청 정보 (이메일, 비밀번호)
     * @param session HTTP 세션
     * @return 로그인 결과 응답
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @Valid @RequestBody LoginRequestDto requestDto,
            HttpSession session
    ) {
        Member member = authService.login(requestDto, session);

        Map<String, Object> response = new HashMap<>();
        response.put("id", member.getId());
        response.put("name", member.getName());
        response.put("email", member.getEmail());
        response.put("message", "로그인에 성공했습니다.");

        return ResponseEntity.ok(response);
    }

    /**
     * 로그아웃 처리 메서드
     * 세션에서 사용자 정보를 제거하고 세션을 무효화
     * @param session HTTP 세션
     * @return 로그아웃 결과 응답
     */
    @GetMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpSession session) {
        authService.logout(session);

        Map<String, String> response = new HashMap<>();
        response.put("message", "로그아웃 되었습니다.");

        return ResponseEntity.ok(response);
    }
}