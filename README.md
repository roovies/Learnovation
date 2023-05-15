# Learnovation
![logo (1)](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/c2f2c960-29d9-4a81-945a-df2e2ed46a74)


# 목차
* [프로젝트 소개](https://github.com/2211SpringCloudWebApp/Learnovation#-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%86%8C%EA%B0%9C)
* [개발 기간](https://github.com/2211SpringCloudWebApp/Learnovation#-%EA%B0%9C%EB%B0%9C-%EA%B8%B0%EA%B0%84)
* [멤버 구성](https://github.com/2211SpringCloudWebApp/Learnovation#-%EB%A9%A4%EB%B2%84-%EA%B5%AC%EC%84%B1)
* [개발 환경](https://github.com/2211SpringCloudWebApp/Learnovation#-%EA%B0%9C%EB%B0%9C-%ED%99%98%EA%B2%BD)
* [기술 스택](https://github.com/2211SpringCloudWebApp/Learnovation#-%EA%B8%B0%EC%88%A0-%EC%8A%A4%ED%83%9D)
* [주요 기능 및 상세](https://github.com/2211SpringCloudWebApp/Learnovation#%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EB%B0%8F-%EC%83%81%EC%84%B8)
  * 회원관리
  * 온라인 강의
  * 상품 결제
  * 직업 정보 탐색
  * 채용공고 확인
  * 알림
  * 채팅 기능
  * 자동응답 챗봇
  * 매칭게시판
  * 공지사항
  * 자유게시판
  * 질문게시판
* [실행화면](https://github.com/2211SpringCloudWebApp/Learnovation#-%EC%8B%A4%ED%96%89%ED%99%94%EB%A9%B4)

# 💝 프로젝트 소개

Learnovation은 IT 기술 분야에 관심이 있는 사람들을 위한 종합 플랫폼으로, 강좌, 동영상, 실습 자료 등을 통해 다양한 IT 교육을 제공하며, 커뮤니티를 통해 진로 정보와 토론을 지원하고 취업 및 채용 정보를 제공합니다.

# 📆 개발 기간
```
2023.03.27 ~ 2023.05.09
```

# 👪 멤버 구성
![member](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032680/c786b014-f362-4717-998a-ac8dd33fadcb)

# 💻 개발 환경
<img src="https://img.shields.io/badge/intellij IDEA-000000?style=for-the-badge&logo=intellij IDEA&logoColor=white"> <img src="https://img.shields.io/badge/Visual Studio Code-007ACC?style=for-the-badge&logo=Visual Studio Code&logoColor=white"> <img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white"> <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"> <img src="https://img.shields.io/badge/JPA-ECD53F?style=for-the-badge&logo=JPA&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

# 🔧 기술 스택
<img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=Thymeleaf&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
<img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">

# 주요 기능 및 상세
## 1. 회원관리

|기능|내용|
|------|---|
|회원가입|개인정보를 입력하여 회원가입(유효성검사와 Ajax를 활용), 카카오을 이용하여 기본 정보 입력받고 추가 정보 입력받아 회원가입|
|로그인|시큐리티를 이용하여 로그인 구현, 시큐리티 롤 타입 체크를 이용해 관리자와 회원 구분, 카카오로 회원가입 한 경우 카카오로 로그인 가능|
|아이디, 비밀번호 찾기|회원가입시 입력했던 정보를 통해 아이디를 알려주고, 비밀번호는 변경할 수 있다.|

## 2. 온라인 강의
|기능|내용|
|------|---|
|강의 등록|관리자는 관리자 페이지에서 인강을 등록할 수 있다. 이메일을 검증하여 강사를 지정하고 썸네일을 등록할 수 있다. 그리고 동적으로 목차와 단원을 추가할 수 있고, 각 단원은 단원에 맞는 강의 영상을 업로드 해야 한다.| 
|강의 목록|판매중인 강의 목록을 볼 수 있다. 강의의 제목으로 검색이 가능하고 강의 카테고리에 따라 분류하여 볼 수 있음| 
|강의 구매 전 상세|상세 페이지에서 바로구매 / 장바구니 / 강의 미리보기 / 수강평과 커리큘럼을 볼 수 있다.|
|강의 구매 후 상세|강의 구매후 상세 페이지에서는 강의의 커리큘럼에 따라 영상을 재생할 수 있다.|

## 3. 수강후기
|기능|내용|
|------|---|
|수강후기 등록|구매한 강의에 대해 수강후기를 작성할 수 있고, 별점을 드래그하여 0.5점 단위로 별점을 부여할 수 있다.|
|수강후기 조회|등록한 수강후기는 강의 대시보드에서 확인할 수 있고, 수정과 삭제가 가능하다.|
|수강후기 수정/삭제|자신의 수강후기를 수정하거나 삭제할 수 있다.|

## 4. 강의 구매

|기능|내용|
|------|---|
|결제|결제하기 선택 시 결제 페이지로 이동하며, 결제 api를 통해 결제할 수 있다.|
|장바구니|회원은 상품 상세에서 장바구니 등록 가능하며 장바구니 목록에서 상품 선택 후 결제 OR 삭제 가능하다.|

## 5. 직업 정보 탐색

|기능|내용|
|------|---|
|직업 정보|직업 정보를 그래프화 하여 보다 편리하게 볼 수 있다.|
|자격증 정보|필요한 자격증을 클릭하면 해당 자격증에 대한 사이트로 리다이렉트 된다.|
|페이징|한 페이지 당 10개의 글을 볼 수 있다.|

## 6. 채용 공고 확인
|기능|내용|
|------|---|
|채용 정보|채용하는 회사의 정보와 모집 요강을 확인 할 수 있다.|
|검색|지역, 직종, 경력, 급여, 사원수로 검색 할 수 있다.|
|페이징|한 페이지 당 여러 개의 글을 볼 수 있다.|

## 7. 알림
|기능|내용|
|------|---|
|초대 알림|내가 그룹에 초대되면 알림을 받을 수 있다.|
|댓글 알림|내가 작성한 그룹게시판에 댓글이 달리면 알림을 받을 수 있다.|

## 8. 자동응답 챗봇
|기능|내용|
|------|---|
|자동응답|자연어 패턴분석을 통해 사용자가 보낸 메세지에 따라 사이트 이용에 대한 편의기능을 답해줌|
|채팅|챗봇과 대화중 상담원 연결을 칠 경우 상담원과의 1:1 채팅기능을 사용 할 수 있다.|

## 9. 매칭 게시판
|기능|내용|
|------|---|
|매칭 게시판|회원들이 그룹을 만들 수 있는 게시판이다. 글 작성후 작성된 댓글에서 초대할 수 있다. 글 작성과 수정을 할 때는 에디터 api를 사용하여 사진 첨부와 글을 꾸밀 수 있다.|
|댓글|댓글과 대댓글 작성, 수정, 삭제 가능하고 여러명이 작성 가능하다.(Ajax 활용)|
|페이징|한 페이지당 10개의 게시물을 보여준다.|

## 10. 그룹
|기능|내용|
|------|---|
|그룹|회원들은 그룹창에서 그룹을 만들 수 있으며 그룹리스트에서 그룹 채팅방으로 접속이 가능하다.|
|채팅|그룹 채팅방에 접속한 회원들은 그룹원끼리 단체 채팅이 가능하다|

## 11. 자유게시판
|기능|내용|
|------|---|
|자유게시판|회원들이 자유롭게 이용하는 게시판이다. 후기게시판은 글 작성(구매자), 수정, 삭제, 좋아요, 신고가 가능하다. 글 작성과 수정을 할 때는 에디터 api를 사용하여 사진 첨부와 글을 꾸밀 수 있다.|
|신고|회원들은 구매회원들의 후기글을 보고 신고사유(욕설/혐오/차별적 표현, 부적절한 내용, 기타)와 함께 글을 작성하여 신고 가능하다.|
|댓글|댓글과 대댓글 작성, 수정, 삭제 가능하고 여러명이 작성 가능하다.(Ajax 활용)|
|페이징|한 페이지당 10개의 게시물을 보여준다.|

## 12. 질문게시판
|기능|내용|
|------|---|
|질문게시판|회원들이 질문 할 때 이용하는 게시판이다. 글 작성, 수정, 삭제가 가능하다. 글 작성과 수정을 할 때는 에디터 api를 사용하여 글을 꾸밀 수 있다.|
|페이징|한 페이지당 10개의 게시물을 보여준다.|

## 13. 공지사항
|기능|내용|
|------|---|
|질문게시판|회원들이 질문 할 때 이용하는 게시판이다. 글 작성, 수정, 삭제가 가능하다. 글 작성과 수정을 할 때는 에디터 api를 사용하여 사진 첨부와 글을 꾸밀 수 있다.|
|댓글|댓글과 대댓글 작성, 수정, 삭제 가능하고 여러명이 작성 가능하다.(Ajax 활용)|
|페이징|한 페이지당 20개의 게시물을 보여준다.|



# 📹 실행화면
## 1. 메인화면
![메인1](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/5dea3e67-42d7-4ade-bca3-aa35a1142e1c)
![메인2](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/c5aad9f7-59fe-434b-9580-cd80f3623132)
![메인3](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/6ba64e7b-5cd4-4be7-969d-4541cabf53e7)


## 2. 회원관리
![회원가입](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/7ac59f45-3df5-4a39-a020-bc226b1a5801)
![비밀번호 찾기](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/1083d381-956a-42fc-bbe0-df730d2e9833)
![로그인](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/0addb289-20f1-4c46-bdbe-3f3f6a34fb6a)

## 3. 온라인 강의
![강의 등록](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/c7e0bc8b-2334-4ed4-8a00-0870e0c80ebb)
![강의 목ㄱ록](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/d35bf38e-1c41-4361-84a1-50e30aa9e9ca)
![구매전](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/ead42da8-4670-454d-84d3-4115ebc87811)
![구매후](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/ec437b63-ad5b-4559-a080-81a5f80029ef)

## 4. 수강 후기
![제목 없음](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/b2a8c893-91e5-4c3e-a659-6cf9500e9fe7)

## 5. 결제
![결제 장면](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/9f63b971-ca0d-49e9-bc27-0db37fcbc48e)
![장바구니](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/f00a3604-a8dc-4982-8f75-9b0c46e07474)

## 6. 직업 정보
![리스트](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/8857c60c-3ae9-4dee-b0f0-8f0f8985bae6)
![상세](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/45d31b8f-4b32-401a-92f5-bc992e6c81c4)

## 7. 채용 정보
![리스트](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/64ff5879-7e50-4fd6-9917-795f7f316163)
![상세](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/7f5a5b73-b3bb-4a47-af79-58b3f413f1d7)

## 8. 알림
![제목 없음](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/238dde14-e1cd-43e8-afa3-f455efdc328a)


## 9. 챗봇
![실시간](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/c8965136-0a96-43f4-bc4b-1b4972229f73)
![패턴 분석](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/5e1ab188-4038-495e-8d7a-ebace6fdc816)

## 10. 매칭게시판
![작성](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/e2a16f93-f9e6-49fb-8433-6210607ebeb9)
![상세](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/635d2990-0891-4064-9ab1-6be519b7bf58)
![리스트](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/564f79a7-7b56-4b8a-a725-c6ffb264fbbf)

## 11. 그룹 채팅
![리스트](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/a47a31b8-61e9-4378-9c3d-a84eec913598)
![채팅](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/296bc508-b120-45ac-9adc-fee312b86851)

## 12. 질문게시판
![글 쓰기](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/1a61df34-d52c-45d4-8053-6db01e6ba4d2)
![상세](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/f843c982-c25a-4518-ad2f-3e699a3613d7)
![리스트](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/91eb560b-01e3-4a5d-8043-13c7eee73aea)

## 13. 자유게시판
![작성](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/2d381a5f-bc27-45ee-9639-255d3852a030)
![상세](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/ad1075df-7327-4d7e-8da8-14ca4b0f775a)
![신고](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/1cfb9862-07da-4c5f-8625-301adf262079)
![페이지](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/58dbb0dd-8fbd-436e-9ed2-55eeb7b4f3c3)





