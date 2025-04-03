package com.example.CalendarDevelop.cys.controller;

import com.example.CalendarDevelop.cys.dto.request.ScheduleSaveRequestDto;
import com.example.CalendarDevelop.cys.dto.request.ScheduleUpdateRequestDto;
import com.example.CalendarDevelop.cys.dto.response.ScheduleResponseDto;
import com.example.CalendarDevelop.cys.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 일정 관련 컨트롤러
 * 일정 생성, 조회, 수정, 삭제 기능을 처리하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@Validated
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 일정 생성 메서드
     * 특정 회원의 새로운 일정을 등록
     * @param memberId 회원 ID
     * @param requestDto 일정 생성 요청 정보 (제목, 내용)
     * @return 생성된 일정 정보
     */
    @PostMapping("/members/{memberId}/schedules")
    public ResponseEntity<ScheduleResponseDto> save(
            @PathVariable Long memberId,
            @Valid @RequestBody ScheduleSaveRequestDto requestDto
    ) {
        return ResponseEntity.ok(scheduleService.save(memberId, requestDto));
    }

    /**
     * 일정 조회 메서드
     * ID를 통해 특정 일정을 조회
     * @param id 일정 ID
     * @return 조회된 일정 정보
     */
    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findById(id));
    }

    /**
     * 회원별 일정 조회 메서드
     * 특정 회원의 모든 일정을 조회
     * @param memberId 회원 ID
     * @return 조회된 일정 목록
     */
    @GetMapping("/members/{memberId}/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> findByMemberId(@PathVariable Long memberId) {
        return ResponseEntity.ok(scheduleService.findByMemberId(memberId));
    }

    /**
     * 일정 수정 메서드
     * 특정 회원의 일정을 수정
     * @param id 일정 ID
     * @param memberId 회원 ID (권한 확인용)
     * @param requestDto 수정할 일정 정보 (제목, 내용)
     * @return 수정된 일정 정보
     */
    @PutMapping("/members/{memberId}/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> update(
            @PathVariable Long id,
            @PathVariable Long memberId,
            @Valid @RequestBody ScheduleUpdateRequestDto requestDto
    ) {
        return ResponseEntity.ok(scheduleService.update(id, memberId, requestDto));
    }

    /**
     * 일정 삭제 메서드
     * 비밀번호 검증 후 특정 회원의 일정을 삭제
     * @param id 일정 ID
     * @param memberId 회원 ID (권한 확인용)
     * @param password 비밀번호 (검증용)
     */
    @DeleteMapping("/members/{memberId}/schedules/{id}")
    public void delete(
            @PathVariable Long id,
            @PathVariable Long memberId,
            @RequestParam String password
    ) {
        scheduleService.delete(id, memberId, password);
    }
}