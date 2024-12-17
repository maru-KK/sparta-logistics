# 📦 SPARTA-LOGISTICS
> 물류 관리 및 배송 시스템을 위한 MSA 기반 플랫폼</br>
> 개발 기간 : 2024.12.05 ~ 2024.12.16

<br/>

### 🧑🏻‍💻 개발 인원 및 역할

|팀원명|역할|
|:---:|:---:|
|<img src="https://avatars.githubusercontent.com/u/96504592?v=4" width="150px;" alt=""/><br /><sub><b>정현수</b></sub></a><br />|서비스 디스커버리 및 Gateway 구축, `인증`/`인가`/`주문`/`상품` 서비스 구현 <br/> Kafka, RabbitMQ 비동기 통신 환경 구축, Redis 캐싱 환경 구축 <br/> slack API 메시지 발송 기능 구현, DockerCompose 실행환경 구축|
|<img src="https://avatars.githubusercontent.com/u/112970256?v=4" width="150px;" alt=""/><br /><sub><b>손동필</b></sub></a><br />|`배송`/`배송 담당자`/`배송 경로` 서비스 구현, <br/> Kafka 비동기 통신, Redis 활용 배송 담당자 순차 배정 기능 구현|
|<img src="https://avatars.githubusercontent.com/u/110008586?v=4" width="150px;" alt=""/><br /><sub><b>김민주</b></sub></a><br />|`허브`/`허브 간 이동정보`/`업체` 서비스 구현, <br/> Redis 캐싱 환경 구축, OpenRouteService API 및 Gemini AI API 연동|

<br/><br/>

## ✨ 서버 아키텍처
<img width="1518" alt="image" src="https://github.com/user-attachments/assets/8e6f49c2-f388-4950-b1c5-8aa589b5e5da" />


<br/><br/>

## ✨️ 서비스 구성 및 실행방법

### 실행 방법 
> [WIKI보기](https://github.com/maru-KK/sparta-logistics/wiki/%EC%8B%A4%ED%96%89-%EB%B0%A9%EB%B2%95)

### 🛡 Gateway Service

> * 토큰을 통한 사용자 요청 인증/인가 처리
> * 로드밸런싱, 로깅


### 🛡 Auth Service

> * 로그인 및 회원가입
> * JWT 토큰 발급
> * 유저 데이터 관리


### 📦 Order Service

> * 주문 생성
> * 조회 및 검색 (필터링, 정렬)
> * Kafka 비동기 통신 환경 구축


### 📦 Product Service

> * 상품 생성 및 재고 수정
> * 조회 및 검색 (필터링, 정렬)


### 📦 Delivery Service

> * 배송 생성, 조회 및 검색
> * 배송 담당자 생성, 순차 배정
> * 배송 경로 생성, 조회 및 검색


### 📦 Hub-Company Service

> * 허브, 업체 생성
> * 조회 및 검색 (필터링, 정렬)
> * 허브 정보 캐싱


### 📦 Hub-Route Service

> * 허브 간 이동 정보 생성 및 조회
> * OpenRouteService API를 활용한 허브 간 이동 경로 탐색
> * 허브 간 이동 경로 캐싱


### 📦 Infra Service

> * Gemini AI API를 활용한 배송 출발 마감 시간 계산
> * Slack API를 활용한 주문 및 배달 메시지 발송

<br/>


<br/><br/>


## ✨️ 트러블 슈팅

#### 🚀 Jackson 직렬화 내부 동작 방식으로 인한 Redis 캐시 데이터 파싱 오류 [WIKI보기](https://github.com/maru-KK/sparta-logistics/wiki/%F0%9F%9A%80-%08Jackson-%EC%A7%81%EB%A0%AC%ED%99%94-%EB%82%B4%EB%B6%80-%EB%8F%99%EC%9E%91-%EB%B0%A9%EC%8B%9D%EC%9C%BC%EB%A1%9C-%EC%9D%B8%ED%95%9C-Redis-%EC%BA%90%EC%8B%9C-%EB%8D%B0%EC%9D%B4%ED%84%B0-%ED%8C%8C%EC%8B%B1-%EC%98%A4%EB%A5%98)
#### 🚀 배송 담당자의 타입에 따른 조건부 속성 문제 [WIKI보기](https://github.com/maru-KK/sparta-logistics/wiki/%F0%9F%9A%80-%EB%B0%B0%EC%86%A1-%EB%8B%B4%EB%8B%B9%EC%9E%90%EC%9D%98-%ED%83%80%EC%9E%85%EC%97%90-%EB%94%B0%EB%A5%B8-%EC%A1%B0%EA%B1%B4%EB%B6%80-%EC%86%8D%EC%84%B1-%EB%AC%B8%EC%A0%9C)
#### 🚀 이벤트 기반 통신구현 및 Kafka의 사용을 선택한 이유 (feat. RabbitMQ) [WIKI보기](https://github.com/maru-KK/sparta-logistics/wiki/%F0%9F%9A%80--%EC%9D%B4%EB%B2%A4%ED%8A%B8-%EA%B8%B0%EB%B0%98-%ED%86%B5%EC%8B%A0%EA%B5%AC%ED%98%84-%EB%B0%8F-Kafka%EC%9D%98-%EC%82%AC%EC%9A%A9%EC%9D%84-%EC%84%A0%ED%83%9D%ED%95%9C-%EC%9D%B4%EC%9C%A0-(feat.-RabbitMQ))
#### 🚀 RabbitMQ를 사용한 성능 개선(약 83% 성능 개선) [WIKI보기](https://github.com/maru-KK/sparta-logistics/wiki/%F0%9F%9A%80--RabbitMQ%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%9C-%EC%84%B1%EB%8A%A5-%EA%B0%9C%EC%84%A0)

<br/><br/>

## ✨ 사용기술 및 개발환경

**Development**

<p>
<img src="https://img.shields.io/badge/JDK 17-E38836?style=flat&logo=openJdk&logoColor=white">
<img src="https://img.shields.io/badge/SpringBoot 3.4-6DB33F?style=flat-square&logo=springboot&logoColor=white"/> 
<br />
<img src="https://img.shields.io/badge/JPA-6DB33F?style=flat-square&logo=hibernate&logoColor=white"/> 
<img src="https://img.shields.io/badge/SpringDataJPA-6DB33F?style=flat-square&logo=spring&logoColor=white"/>
<img src="https://img.shields.io/badge/QueryDSL-6DB33F?style=flat-square&logo=spring&logoColor=white"/>
</p>

**Database**

<p>
<img src="https://img.shields.io/badge/MySQL 8-08668E?style=flat&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/Redis-FF0000?style=flat&logo=redis&logoColor=white">
</p>

**Server**

<p>
<img src="https://img.shields.io/badge/Eureka-6DB33F?style=flat-square&logo=spring&logoColor=white"/>
<img src="https://img.shields.io/badge/SpringGateway-6DB33F?style=flat-square&logo=spring&logoColor=white"/>
<img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=flat-square&logo=springSecurity&logoColor=white"/>
<img src="https://img.shields.io/badge/Resilience4j-000000?style=flat-square&logo=&logoColor=white"/>
<br />
<img src="https://img.shields.io/badge/Kafka-231F20?style=flat-square&logo=apachekafka&logoColor=white"/>
<img src="https://img.shields.io/badge/Google Gemini api-8E75B2?style=flat-square&logo=googleGemini&logoColor=white"/>
<img src="https://img.shields.io/badge/Slack api-4A154B?style=flat-square&logo=slack&logoColor=white"/>
<br />
<img src="https://img.shields.io/badge/Zipkin-FE5F50?style=flat-square&logo=&logoColor=white"/>
<img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=docker&logoColor=white"/>
<img src="https://img.shields.io/badge/DockerCompose-2496ED?style=flat-square&logo=docker&logoColor=white"/>
</p>

<br/><br/>



## ✨ ERD
![image](https://github.com/user-attachments/assets/658074a7-e3f8-479e-85dc-303580ba470e)
