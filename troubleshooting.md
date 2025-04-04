### 캘린더 개발 프로젝트 트러블 슈팅 가이드

---

##  1. 인증 관련 문제

###  문제: 로그인이 되지 않음
- **원인**: 이메일 또는 비밀번호가 일치하지 않음
- **해결 방법**:
    - 이메일과 비밀번호를 정확히 입력했는지 확인
    - `MemberRepository.findByEmailAndPassword()` 메서드 호출 결과 확인
    - DB에 해당 사용자가 존재하는지 직접 확인

###  문제: 세션이 유지되지 않음
- **원인**: 세션 타임아웃 또는 세션 관리 설정 문제
- **해결 방법**:
    - `application.properties`에서 세션 타임아웃 설정(현재 30분) 확인

  ```properties
  # Session Configuration
  server.servlet.session.timeout=30m
  ```

---

##  2. 데이터베이스 연결 문제

###  문제: 데이터베이스 연결 실패
- **원인**: 연결 설정 오류
- **해결 방법**:
    - MySQL 서버 실행 여부 확인
    - `application.properties`의 DB 접속 정보 재확인

  ```properties
  spring.application.name=CalendarDevelop
  spring.datasource.url=jdbc:mysql://localhost:3306/test
  spring.datasource.username=root
  spring.datasource.password=0000
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  
  # JPA Configuration
  spring.jpa.hibernate.ddl-auto=validate
  spring.jpa.show-sql=true
  spring.jpa.properties.hibernate.format_sql=true
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
  ```

---
