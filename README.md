# Commute System

## 주제
간단한 출퇴근 시스템 구현

## 기술 스택
- Java 17
- Spring Boot 3.2.2
- JPA
- MySQL

## 기능 개발

### 1단계
**a. 팀 등록 기능**
- `POST /api/v1/teams` - 팀 생성

**b. 직원 등록 기능**
- `POST /api/v1/employees` - 직원 생성

**c. 팀 조회 기능**
- `GET /api/v1/teams` - 전체 팀 목록 조회
- `PUT /api/v1/teams/{teamId}/name` - 팀 이름 수정

**d. 직원 조회 기능**
- `GET /api/v1/employees` - 전체 직원 목록 조회
- `PUT /api/v1/employees/{employeeId}` - 직원 정보 수정
- `PUT /api/v1/employees/{employeeId}/team` - 직원 팀 변경

### 2단계
**a. 출근 기능**
- `POST /api/v1/employees/{employeeId}/commutes/start-work` - 출근 등록

**b. 퇴근 기능**
- `PUT /api/v1/employees/{employeeId}/commutes/end-work` - 퇴근 등록

**c. 특정 직원의 날짜별 근무시간을 조회하는 기능**
- `GET /api/v1/employees/{employeeId}/commutes/detail?yearMonth=YYYY-MM` - 월별 근무 상세 조회

### 3단계
**a. 연차 신청**
- `POST /api/v1/employees/{employeeId}/dayoffs/request` - 연차 신청

**b. 연차 조회**
- `GET /api/v1/employees/{employeeId}/dayoffs/remain` - 남은 연차 일수 조회

**c. 특정 직원의 날짜별 근무시간을 조회하는 기능 V2**
- (2단계의 근무시간 조회 기능에서 확장)

### 4단계
**a. 초과 근무 계산**
- `GET /api/v1/employees/commutes/overtime?yearMonth=YYYY-MM` - 월별 초과 근무 직원 목록 조회
