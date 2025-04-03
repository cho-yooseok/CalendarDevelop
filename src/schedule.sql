-- 회원 테이블 생성
CREATE TABLE member (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,    -- 회원 고유 식별자 (자동 증가)
                        name VARCHAR(255) NOT NULL,    -- 회원 이름 (필수)
                        email VARCHAR(255) NOT NULL,    -- 이메일 주소 (필수)
                        password VARCHAR(255) NOT NULL,    -- 비밀번호 (필수)
                        created_at TIMESTAMP NOT NULL,    -- 회원 생성 시간
                        updated_at TIMESTAMP NOT NULL    -- 회원 정보 수정 시간
);

-- 일정 테이블 생성
CREATE TABLE schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,    -- 일정 고유 식별자 (자동 증가)
    title VARCHAR(255) NOT NULL,    -- 일정 제목 (필수)
    content VARCHAR(255) NOT NULL,    -- 일정 내용 (필수)
    member_id BIGINT NOT NULL,    -- 회원 외래키 (일정 소유자)
    created_at DATETIME NOT NULL,    -- 일정 생성 시간
    updated_at DATETIME NOT NULL,    -- 일정 수정 시간
    FOREIGN KEY (member_id) REFERENCES member(id)    -- member 테이블의 id를 참조하는 외래키 제약조건
);