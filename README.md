# 📌 Aladin Study Cafe 

## 스터디카페 이용, 관리앱

- Notion : [Notion_Link](https://furry-ocean-0ef.notion.site/d484ac9d91d84327a01a10238da944fe)


## 1. 제작 기간 & 참여 인원

- 2022년 11월 ~ 2023년 2월
- ch-yang1273 
- padonan


## 2. 전체 개요도 
<img width="1280" alt="main_" src="https://user-images.githubusercontent.com/98295182/230893835-f88d6ed5-1222-40bc-a6fb-e42e61464554.png">

- RESTful API + JwtToken 기반의 서버 <-> 클라이언트 
- FCMToken, JwtToken은 Redis에서 별도의 관리
- Token을 제외한 비즈니스 로직상의 데이터는 RDB에서 관리
- 내부 비즈니스 로직과 관련된 API와 외부 검증이 필요한 대외 API 구현,  설계

#####   👉 실결제와 같은 로직으로 진행되는 [BootPay SandBox Api]('https://docs.bootpay.co.kr/?front=web&backend=java')를 이용해 
##### 결제 => 이용 => 관리(환불, 매출관리등) 까지의 전반적인 과정을 구현하였습니다.
##
##
##
## 2.1 토큰 관리 개요도
<img width="1158" alt="20230108_220843" src="https://user-images.githubusercontent.com/98295182/230894352-b50c4c9e-fd0b-4f03-8e75-31c9c4f00122.png">

- Jwt를 accessToken과 refreshToken 두가지로 관리
- accessToken 만료시 Redis상의 refreshToken을 찾아 accessToken 갱신
- 로그인시 FirebaseCloudMessageToken(FCMToken) 생성.
- Redis상의 FCMToken이 유효할시 FCMService를 이용한 푸시 알림 전송
- FCMToken이 유효하지 않을시 FCMToken를 재생성하여 지급

##### 👉 빠른 조회가 필요한 Token을 Redis에서 관리하고 Token을 제외한 데이터는 RDB에서 관리하여 서버의 응답속도를 높였습니다.
##
##
##
## 2.2 푸시 알림 개요도 
<img width="1179" alt="20230110_023912" src="https://user-images.githubusercontent.com/98295182/230894119-b33a5a33-1718-4780-84e0-51b499d07064.png">
1. A유저가 B유저에게 메시지를 보내고자 함
2. A유저가 B유저에게 보낼 메시지를 포함한 post를 서버에 요청
3. Redis상의 A유저의 FCMToken을 찾아 FCM서버에 전송 요청
4. B유저에게 알림 전송

##### 👉 사장님이 여러 고객들에게 알림 메시지 전송 가능한 기능을 구현하였습니다.
##
##
##
## 2.3 결제 흐름도
<img width="1280" alt="payment_flow" src="https://user-images.githubusercontent.com/98295182/230903396-cb6f1ad8-cb2a-4c55-900f-b777837d1e24.png">

- 좀 더 세부적인 결제 흐름을 만들고자 flow를 Order, Product, Ticket DAO로 나누어 관리하였습니다. 
1. OrderDAO 생성 => 유저가 결제를 시도한 경우 생성됩니다. 비검증 상태이고 테이블상에 해당 결제시도가 저장됩니다.
2. 결제 검증 => BootPay서버에 요청하여 해당 Order가 유효한 결제인지 검증합니다. 비검증일시 Exception을 날립니다.
3. 유효한 검증일시, 일련번호(제품 구별을 위한)를 지닌 Product를 생성합니다.
 - Product와 Ticket은 단일 트랜잭션으로 관리합니다. 둘 중 하나만 생성되는 경우를 방지합니다.
 - Product는 제품의 가격, 결제시간, 환불여부 등을 관리하고 Ticket은 유저가 직접적으로 이용에 필요한
 정보를 담습니다.
4. Product의 데이터를 기준으로 Ticket을 생성합니다.
5. Ticket 만료시 주기적인 스케줄링으로 ExpiredTicket에 Ticket을 내보냅니다. Ticket테이블의 데이터가 많아져
생길 수 있는 속도 저하를 우려하였습니다. Ticket은 고객 이용에 가장 많이 접근하는 테이블입니다.
6. 최종 결제 승인을 Flutter에 보내고 DB상에 결제처리를 합니다. Order의 상태는 Done(검증)이 됩니다.

##### 👉 결제 흐름을 세분화하고, 필요한 트랜잭션은 통합하여 관리해보며 효율적인 결제 구조에 대해 고민해보았습니다.
##
##
##
## 3. 사용 기술


# 📝 Back-end

- springBoot ‘2.7.5’
- jdk 11
- gradle '7.5.1'
- h2 ‘2.1.214’
- QueryDsl '5.0.0'
- jwt '0.11.5'
- swagger '1.6.13'
- bootpay.backend.java // 결제 모듈
- junit '5'

# 📝 Front-end


- Git : (https://github.com/padonan/asc_flutter)

- flutter ‘3.3.4’
- dart ‘2.18.2’
- flutter_native_splash: ^2.2.13
- intl: ^0.17.0
- qr_flutter: ^4.0.0
- dio: ^4.0.6
- flutter_secure_storage: ^6.0.0
- jwt_decoder: ^2.0.1
- bootpay: ^4.4.3
- flutter_form_builder: ^7.7.0
- toast: ^0.3.0
- url_launcher: ^6.1.7
- logger: ^1.1.0
- flutter_local_notifications: ^12.0.4
- build_runner: ^2.3.2
- json_serializable: ^6.5.4



## 4. ERD 구조

<img width="944" alt="20221227_194215" src="https://user-images.githubusercontent.com/98295182/209654739-6369b254-2c20-4edc-81ff-8618606fd360.png">







## 5. 핵심 기능




✨ 핵심 서비스는 스터디카페 이용, 결제권 구입, ADMIN의 스터디카페 관리입니다.



![로그인,회원가입](https://user-images.githubusercontent.com/98295182/209656957-abae63fe-bf1c-4300-ba21-f12f71c6e04a.gif)
![ezgif-2-cdb0ac2ff9](https://user-images.githubusercontent.com/98295182/209657000-303d2060-c12c-48aa-817e-ec53915c3384.gif)
![결제1](https://user-images.githubusercontent.com/98295182/209657016-8dac02b4-e134-42c8-bbca-8efe1b50e3be.gif)

![매출](https://user-images.githubusercontent.com/98295182/209657030-a6567c92-ded2-4b59-a743-908164ee65e8.gif)
