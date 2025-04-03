package com.example.CalendarDevelop.cys.service;

import com.example.CalendarDevelop.cys.dto.request.ScheduleSaveRequestDto;
import com.example.CalendarDevelop.cys.dto.request.ScheduleUpdateRequestDto;
import com.example.CalendarDevelop.cys.dto.response.ScheduleResponseDto;
import com.example.CalendarDevelop.cys.entity.Member;
import com.example.CalendarDevelop.cys.entity.Schedule;
import com.example.CalendarDevelop.cys.repository.MemberRepository;
import com.example.CalendarDevelop.cys.repository.ScheduleRepository;
import com.example.CalendarDevelop.exception.ApplicationException;
import com.example.CalendarDevelop.exception.PasswordMismatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 일정 관련 비즈니스 로직을 처리하는 서비스 클래스
 * 일정 등록, 조회, 수정, 삭제 기능을 제공
 */
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    /**
     * 새로운 일정을 등록하는 메서드
     *
     * @param memberId 일정을 등록할 회원의 ID
     * @param requestDto 일정 등록에 필요한 정보(제목, 내용)를 담은 DTO
     * @return 등록된 일정 정보를 담은 응답 DTO
     * @throws ApplicationException 해당 ID의 회원이 존재하지 않는 경우 발생
     */
    @Transactional
    public ScheduleResponseDto save(Long memberId, ScheduleSaveRequestDto requestDto) {
        // 회원 ID로 회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ApplicationException("해당 id의 회원이 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        // 새로운 일정 객체 생성
        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContent(),
                member
        );

        // 일정 저장
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule);
    }

    /**
     * ID로 일정을 조회하는 메서드
     *
     * @param id 조회할 일정의 ID
     * @return 조회된 일정 정보를 담은 응답 DTO
     * @throws ApplicationException 해당 ID의 일정이 존재하지 않는 경우 발생
     */
    @Transactional(readOnly = true)
    public ScheduleResponseDto findById(Long id) {
        // ID로 일정 조회
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("해당 id의 일정이 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        return new ScheduleResponseDto(schedule);
    }

    /**
     * 회원 ID로 해당 회원의 모든 일정을 조회하는 메서드
     *
     * @param memberId 조회할 회원의 ID
     * @return 조회된 일정 목록을 담은 응답 DTO 리스트
     */
    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findByMemberId(Long memberId) {
        // 회원 ID로 해당 회원의 모든 일정 조회
        return scheduleRepository.findByMemberId(memberId).stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 일정 정보를 수정하는 메서드
     *
     * @param id 수정할 일정의 ID
     * @param memberId 수정 요청한 회원의 ID
     * @param requestDto 수정할 정보(제목, 내용)를 담은 DTO
     * @return 수정된 일정 정보를 담은 응답 DTO
     * @throws ApplicationException 해당 ID의 일정이 존재하지 않거나, 수정 권한이 없는 경우 발생
     */
    @Transactional
    public ScheduleResponseDto update(Long id, Long memberId, ScheduleUpdateRequestDto requestDto) {
        // ID로 일정 조회
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("해당 id의 일정이 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        // 일정 소유자 확인 (권한 체크)
        if (!schedule.getMember().getId().equals(memberId)) {
            throw new ApplicationException("일정을 수정할 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        // 일정 정보 업데이트
        schedule.update(requestDto.getTitle(), requestDto.getContent());

        return new ScheduleResponseDto(schedule);
    }

    /**
     * 일정을 삭제하는 메서드
     *
     * @param id 삭제할 일정의 ID
     * @param memberId 삭제 요청한 회원의 ID
     * @param password 삭제 확인을 위한 비밀번호
     * @throws ApplicationException 해당 ID의 일정이나 회원이 존재하지 않거나, 삭제 권한이 없는 경우 발생
     * @throws PasswordMismatchException 비밀번호가 일치하지 않는 경우 발생
     */
    @Transactional
    public void delete(Long id, Long memberId, String password) {
        // ID로 일정 조회
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("해당 id의 일정이 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        // 회원 ID로 회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ApplicationException("해당 id의 회원이 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        // 일정 소유자 확인 (권한 체크)
        if (!schedule.getMember().getId().equals(memberId)) {
            throw new ApplicationException("일정을 삭제할 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        // 비밀번호 확인
        if (!password.equals(member.getPassword())) {
            throw new PasswordMismatchException();
        }

        // 일정 삭제
        scheduleRepository.deleteById(id);
    }
}