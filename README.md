### Spring Boot -- Board Project (11/20 ~ 11/30)

---

스프링부트로 만든 게시판입니다.


#### __※ 개발환경__
 <br>

 → IDE : InteliJ IDEA Community

 → Spring Boot 2.7.6

 → JDK 11

 → MySQL 8.0.35

 →  Spring Data JPA

 → Thymleaf
  <br>
   <br>

#### 주요기능(CRUD)
 → 1. 게시물 등록

 → 2. 게시물 수정

 → 3. 게시물 조회

 → 4. DB 저장

 → 5. 게시글 삭제
  <br>
   <br>



#### ☆ 수정 처리
<br>

1. 글삭제
2. 게시글 내용및 업데이트 시간 수정
3. 페이지 당 5개의 게시물 표시
4. 파일(이미지)첨부하기
5. 단일 파일 첨부
6. 다중 파일 첨부

 <br>
  <br>

__※ 향후 업데이트 예정 기능__
1. 댓글 수정 삭제

2. 실시간 채팅
...


<br>__제작 과정에 필요했던 지식__

1. CRUD 의 개념

 → 엔티티 등록

 → DB(MySQL)에 저장

 → CRUD 구현 클래스 작성
 <br>

2. 스프링부트 어노테이션 사용

  → @RequestPharam, Model 등의 데이터 공유방안

  → CRUD구현 클래스에 사용되는 어노테이션(@Controller , @Service, @Getter....)

  <br>

3. HTML과 스프링부트 간의 데이터 공유


4. 자바 문법

   → 데이터 타입

   → 예외처리

   → 람다식 활용
 <br>...

---

### __화면구성__
---

<p align="center">

__1.메인화면__

[localhost:8080](http://localhost:8080/)


  <img src="./imgs/1.jpg" alt="image_01"><br><br>
__2. 작성 화면__

  <img src="./imgs/2.jpg" alt="image_02"><br><br>
__4. 게시판 화면__

  <img src="./imgs/3.jpg" alt="image_03"><br><br>
__5. 게시글 삭제 기능__

  <img src="./imgs/5.jpg" alt="image_04"><br><br>
__6. 게시글 수정 화면__

  <img src="./imgs/5-1.jpg" alt="image_04"><br><br>
__7. 게시글 상세 및 댓글 작성화면__

  <img src="./imgs/6.jpg" alt="image_05" width="600" height="300"><br>
  </p>


---
__업데이트 내역__

-1.0.00 : 최초 버전: CRUD 구현(11/20 ~ 11/22)

-1.0.05 : 댓글 기능 구현 (11/23)

-1.0.10 : 파일 첨부 구현 (11/24)

-1.0.15 : view 시각화 업데이트 (11/26)

-1.0.20 : 게시글 파일 수정구현(11/26)
