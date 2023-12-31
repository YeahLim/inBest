<details>
<summary>4주차</summary>

# 23-09-22
- rabbitmq로 pub/sub 기능 설계
- 모의주식 그룹 생성 STOMP API 명세서 작성

# 23-09-21
- pub/sub 기능을 위한 rabbitmq-test 배포
- connection confused 에러 해결
- activamq, webflux 학습
- redis 구조 복습

# 23-09-20
- stomp Config 설정
- virtual host 학습
- rabbitmq, stomp plugin 추가
- rabbitmq 재배포


# 23-09-19
- Message Broker 추가 학습
- RabbitMq Config 설정

# 23-09-18
- RabbitMq, kafka 학습 및 비교
- Message Broker 선택

</details>


<details>
<summary>3주차</summary>

# 23-09-15
- 중간 발표
- 전문가 리뷰
- infra dockerfile 리팩토링 

# 23-09-14
- Redis 배포
- 배포 서버 gateway의 lb 안되는 현상 해결
    - 각 eureka client의 host 변경으로 해결
- gunicorn으로 시도하려다, django CI/CD 복구
- rabbitMQ 배포 

# 23-09-13
- 배포 서버에서 gateway 안되는 문제
    - loadbalancing 안할 경우 정상 작동하는 것 발견


# 23-09-12
- eureka에 service-url인식안되는 문제 해결
    - service-url -> serviceUrl로 변경
- django 서비스 CI/CD 구축 완료

# 23-09-11
- EC2 정식 서버 배포로 jenkins 설정
- eureka, gateway, user 서비스 CI/CD 구축 완료

</details>

<details>
<summary>2주차</summary>

# 23-09-08
- pip 에러 해결
- django를 gateway에 등록

# 23-09-07
- branch 별로 프로젝트 생성
- branch 별로 eureka 및 gateway 등록 

# 23-09-06
- 실제 서비스를 eureka server와 gateway에 등록
    - springboot service 등록
    - django service 등록 중 문제 발생

# 23-09-05
- Spring Cloud Gateway 구현
- Gateway, Eureka Server, Client(서비스) 연동
    - segment, rewrite로 url prefix 문제 해결

# 23-09-04
- MSA 학습
- Spring Cloud 학습
- Spring Cloud Netflix Eureka 구현
    - 서버 구현
    - 클라이언트 등록
</details>
