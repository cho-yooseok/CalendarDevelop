package com.example.CalendarDevelop.cys.service;

import com.example.CalendarDevelop.exception.ApplicationException;
import com.example.CalendarDevelop.exception.PasswordMismatchException;
import com.example.CalendarDevelop.cys.dto.request.MemberSaveRequestDto;
import com.example.CalendarDevelop.cys.dto.request.MemberUpdateRequestDto;
import com.example.CalendarDevelop.cys.dto.response.MemberResponseDto;
import com.example.CalendarDevelop.cys.entity.Member;
import com.example.CalendarDevelop.cys.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회원 관련 비즈니스 로직을 처리하는 서비스 클래스
 * 회원 등록, 조회, 수정, 삭제 기능을 제공
 */
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 새로운 회원을 등록하는 메서드
     *
     * @param requestDto 회원 등록에 필요한 정보(이름, 이메일, 비밀번호)를 담은 DTO
     * @return 등록된 회원 정보를 담은 응답 DTO
     * @throws ApplicationException 이미 존재하는 이메일인 경우 발생
     */
    @Transactional
    public MemberResponseDto save(MemberSaveRequestDto requestDto) {
        // 이메일 중복 검사
        if (memberRepository.existsByEmail(requestDto.getEmail())) {
            throw new ApplicationException("이미 존재하는 이메일입니다.", HttpStatus.BAD_REQUEST);
        }

        // 새로운 회원 객체 생성
        Member member = new Member(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        // 회원 저장
        Member savedMember = memberRepository.save(member);

        // 저장된 회원 정보를 응답 DTO로 변환하여 반환
        return new MemberResponseDto(
                savedMember.getId(),
                savedMember.getName(),
                savedMember.getEmail(),
                savedMember.getCreatedAt(),
                savedMember.getUpdatedAt()
        );
    }

    /**
     * ID로 회원을 조회하는 메서드
     *
     * @param id 조회할 회원의 ID
     * @return 조회된 회원 정보를 담은 응답 DTO
     * @throws ApplicationException 해당 ID의 회원이 존재하지 않는 경우 발생
     */
    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long id) {
        // ID로 회원 조회
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new ApplicationException("해당 id의 회원이 존재하지 않습니다.", HttpStatus.NOT_FOUND)
        );

        // 조회된 회원 정보를 응답 DTO로 변환하여 반환
        return new MemberResponseDto(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getCreatedAt(),
                member.getUpdatedAt()
        );
    }

    /**
     * 회원 정보를 수정하는 메서드
     *
     * @param id 수정할 회원의 ID
     * @param requestDto 수정할 정보(이름, 비밀번호)를 담은 DTO
     * @return 수정된 회원 정보를 담은 응답 DTO
     * @throws ApplicationException 해당 ID의 회원이 존재하지 않는 경우 발생
     */
    @Transactional
    public MemberResponseDto update(Long id, MemberUpdateRequestDto requestDto) {
        // ID로 회원 조회
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new ApplicationException("해당 id의 회원이 존재하지 않습니다.", HttpStatus.NOT_FOUND)
        );

        // 회원 정보 업데이트
        member.update(requestDto.getName(), requestDto.getPassword());

        // JPA를 사용하면 엔티티 변경 사항이 자동으로 저장되므로 명시적으로 save를 호출할 필요가 없음

        // 수정된 회원 정보를 응답 DTO로 변환하여 반환
        return new MemberResponseDto(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getCreatedAt(),
                member.getUpdatedAt()
        );
    }

    /**
     * 회원을 삭제하는 메서드
     *
     * @param id 삭제할 회원의 ID
     * @param password 삭제 확인을 위한 비밀번호
     * @throws ApplicationException 해당 ID의 회원이 존재하지 않는 경우 발생
     * @throws PasswordMismatchException 비밀번호가 일치하지 않는 경우 발생
     */
    @Transactional
    public void delete(Long id, String password) {
        // ID로 회원 조회
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new ApplicationException("해당 id의 회원이 존재하지 않습니다.", HttpStatus.NOT_FOUND)
        );

        // 비밀번호 확인
        if (!password.equals(member.getPassword())) {
            throw new PasswordMismatchException();
        }

        // 회원 삭제
        memberRepository.deleteById(id);
    }
}