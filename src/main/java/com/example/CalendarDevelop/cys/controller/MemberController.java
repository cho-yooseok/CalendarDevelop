package com.example.CalendarDevelop.cys.controller;

import com.example.CalendarDevelop.cys.dto.request.MemberSaveRequestDto;
import com.example.CalendarDevelop.cys.dto.request.MemberUpdateRequestDto;
import com.example.CalendarDevelop.cys.dto.response.MemberResponseDto;
import com.example.CalendarDevelop.cys.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 회원 관련 컨트롤러
 * 회원 생성, 조회, 수정, 삭제 기능을 처리하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 생성 메서드
     * 새로운 회원을 등록
     * @param requestDto 회원 생성 요청 정보 (이름, 이메일, 비밀번호)
     * @return 생성된 회원 정보
     */
    @PostMapping("/members")
    public ResponseEntity<MemberResponseDto> save(
            @Valid @RequestBody MemberSaveRequestDto requestDto
    ) {
        return ResponseEntity.ok(memberService.save(requestDto));
    }

    /**
     * 회원 조회 메서드
     * ID를 통해 회원 정보를 조회
     * @param id 회원 ID
     * @return 회원 정보
     */
    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    /**
     * 회원 정보 수정 메서드
     * 기존 회원 정보를 수정
     * @param id 회원 ID
     * @param requestDto 수정할 회원 정보 (이름, 비밀번호)
     * @return 수정된 회원 정보
     */
    @PutMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody MemberUpdateRequestDto requestDto
    ) {
        return ResponseEntity.ok(memberService.update(id, requestDto));
    }

    /**
     * 회원 삭제 메서드
     * 비밀번호 검증 후 회원 정보 삭제
     * @param id 회원 ID
     * @param password 비밀번호 (검증용)
     */
    @DeleteMapping("/members/{id}")
    public void delete(
            @PathVariable Long id,
            @RequestParam String password
    ) {
        memberService.delete(id, password);
    }
}