# CalendarDevelop

## 프로젝트 소개

이 프로젝트는 내일배움캠프 Spring 6기 과제로 진행한 일정 관리 시스템 애플리케이션입니다.  
기존 JDBC 방식에서 JPA로 리팩토링하여 제작되었으며, 사용자 등록, 로그인, 일정 관리 기능을 제공합니다.

---

## ERD 설계

![ERD 이미지](src/CalendarDevelopERD%20-%20%EB%B3%B5%EC%82%AC%EB%B3%B8%20%282%29.png)

---

## API 명세서

https://documenter.getpostman.com/view/42104801/2sB2cU9Mdk

---

## 기술 스택

- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- MySQL
- Lombok
- Spring Validation

---

## 주요 기능

### 회원 관리
- 회원 가입: `POST /members`
- 회원 정보 조회: `GET /members/{id}`
- 회원 정보 수정: `PUT /members/{id}`
- 회원 탈퇴: `DELETE /members/{id}?password=xxx`

### 인증
- 로그인: `POST /login`
- 로그아웃: `GET /logout`

### 일정 관리
- 일정 등록: `POST /members/{memberId}/schedules`
- 일정 조회: `GET /schedules/{id}`
- 사용자별 일정 목록 조회: `GET /members/{memberId}/schedules`
- 일정 수정: `PUT /members/{memberId}/schedules/{id}`
- 일정 삭제: `DELETE /members/{memberId}/schedules/{id}?password=xxx`

---

## 프로젝트 구조

```
com.example.CalendarDevelop
├── CalendarDevelopApplication.java
│
├── cys
│   ├── config
│   │   ├── FilterConfig.java
│   │   ├── GlobalExceptionHandler.java
│   │   ├── JpaAuditingConfiguration.java
│   │   └── filter
│   │       └── AuthenticationFilter.java
│   │
│   ├── controller
│   │   ├── AuthController.java
│   │   ├── MemberController.java
│   │   └── ScheduleController.java
│   │
│   ├── dto
│   │   ├── request
│   │   │   ├── LoginRequestDto.java
│   │   │   ├── MemberSaveRequestDto.java
│   │   │   ├── MemberUpdateRequestDto.java
│   │   │   ├── ScheduleSaveRequestDto.java
│   │   │   └── ScheduleUpdateRequestDto.java
│   │   └── response
│   │       ├── MemberResponseDto.java
│   │       └── ScheduleResponseDto.java
│   │
│   ├── entity
│   │   ├── BaseTimeEntity.java
│   │   ├── Member.java
│   │   └── Schedule.java
│   │
│   ├── repository
│   │   ├── MemberRepository.java
│   │   └── ScheduleRepository.java
│   │
│   └── service
│       ├── AuthService.java
│       ├── MemberService.java
│       └── ScheduleService.java
│
├── exception
│   ├── ApplicationException.java
│   ├── AuthenticationException.java
│   └── PasswordMismatchException.java
│
└── resources
    ├── application.properties
    └── schedule.sql
```


---

## 개발자 가이드

### 데이터베이스 설정

```sql
CREATE DATABASE test;
USE test;
```

---

### 테이블 생성 (src/schedule.sql)

```sql
CREATE TABLE member (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   created_at TIMESTAMP NOT NULL,
   updated_at TIMESTAMP NOT NULL
);

CREATE TABLE schedule (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   title VARCHAR(255) NOT NULL,
   content VARCHAR(255) NOT NULL,
   member_id BIGINT NOT NULL,
   created_at DATETIME NOT NULL,
   updated_at DATETIME NOT NULL,
   FOREIGN KEY (member_id) REFERENCES member(id)
);
```

---

### application.properties 설정

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=your-username
spring.datasource.password=your-password
```

---

### 의존성 추가 (build.gradle)

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'com.mysql:mysql-connector-j'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```
