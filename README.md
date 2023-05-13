# Learnovation
<img src="https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/9b52ef5f-260c-441c-bc95-0cb8833e6230">

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
![제목 없음](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/f5e13177-a09e-4b4a-b40b-aa9b893352d3)

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
![메인1](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/53ea2606-421c-4df5-bb4e-a47d1ef6a729)
![메인2](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/277be1b9-f447-4668-a290-0e626bc82ea9)
![메인3](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/52af6e6b-3b4b-4b36-888c-2ef787bf2995)

## 2. 회원관리
![회원가입](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/59478c7c-3f26-474f-92c3-979799d448fc)
![비밀번호 찾기](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/6acd0294-2ee0-43d3-a5b5-dba77d7ec94c)
![로그인](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/6662c9ba-75cd-4657-9e79-7eaadb466ada)

## 3. 온라인 강의
![강의 등록](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/2e45c933-4f35-40a2-ac87-99c86242b70f)
![강의 목ㄱ록](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/ed4792e0-05ae-4fd1-9cdc-0c9d7e9cff1c)
![구매전](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/cfe6d671-7e6a-43ee-8fd7-2bc1743bf227)
![구매후](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/19523b3b-43f9-4101-8f08-074ad580ba7b)

## 4. 수강 후기
![제목 없음](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/7e00b789-a08a-47a3-b4e7-c83b64536e99)

## 5. 결제
![결제 장면](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/07c9fde5-f2b7-4fca-89f4-422830aa0e35)
![장바구니](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/56fa6038-2c46-43d2-b00d-610cfc7afe70)

## 6. 직업 정보
![리스트](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/f0c28c0b-c9e7-47a0-8fa4-eb95ad2e2cab)
![상세](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/d8661703-4dc9-459a-9bc6-9abcc35f9475)

## 7. 채용 정보
![리스트](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/48e87873-dc58-4515-9a08-8cfee22f8496)
![상세](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/5491c31c-d4b0-4b3c-8c77-cc7101cc4521)

## 8. 알림
![제목 없음](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/3c74f196-2a6e-478e-9db1-1a15f0365a74)

## 9. 챗봇
![실시간](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/2b955c1f-68be-4461-9229-4edc13d7a332)
![패턴 분석](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/768cc98d-29f6-4ebc-9122-eea631ae529c)

## 10. 매칭게시판
![작성](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/f26d93a5-358b-4dca-8cb3-227352785b76)
![상세](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/8faa82ac-9574-493d-b8ed-d82c1d5d1065)
![리스트](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/56b4fa85-ab8f-4636-9906-a5c3ff6779f6)

## 11. 그룹 채팅
![리스트](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/a1be827d-496a-46f5-b112-97961ea38285)
![채팅](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/2fd22f52-1dd6-48bc-a3d7-2c9334dc5142)

## 12. 질문게시판
![글 쓰기](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/a72a66f3-bf8c-4842-8676-744f93df8702)
![상세](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/f4484f8e-2423-4766-ab59-5a6778c631d2)
![리스트](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/e2f73b49-49fc-4055-8d5b-3eb7c3d6951a)

## 13. 자유게시판
![작성](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/a5f16c36-31e5-4613-a992-63912b7f4de9)
![상세](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/78b95648-b8e5-4e83-87ce-d2c294a44b77)
![신고](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/d970e802-ed3d-48ff-b39f-0ae3a429849a)
![페이지](https://github.com/2211SpringCloudWebApp/Learnovation/assets/119032722/5b6e6baf-9fe9-48b2-8cc9-900754dd7eba)














