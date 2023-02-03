# README

# MoaMoa

## 소개

## 사용법

1. 리포지토리를 복제하거나 다운로드합니다.
2. build.gradle로 project open
3. MoamoaApplication을 실행
4. [http://localhost:8080](http://localhost:8080) 으로 요청 send

## 특징

## 기술스택

- Spring
- Spring Boot
- Spring Security
- JPA
- Query DSL

## 폴더 구조

```
src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── ssafy
    │   │           └── moamoa
    │   │               ├── config
    │   │               │   └── security
    │   │               ├── controller
    │   │               ├── domain
    │   │               │   ├── dto
    │   │               │   └── entity
    │   │               ├── exception
    │   │               ├── filter
    │   │               ├── interceptor
    │   │               ├── repository
    │   │               │   ├── projection
    │   │               │   └── querydsl
    │   │               └── service
    │   └── resources
    └── test
        ├── java
        │   └── com
        │       └── ssafy
        │           └── moamoa
        │               ├── domain
        │               │   └── entity
        │               ├── repository
        │               │   └── querydsl
        │               └── service
        └── resources
```

## 개발 기간

2023.01.16~