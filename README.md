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
|강의 목록|판매중인 강의 목록을 볼 수 있다. 강의의 제목으로 검색이 가능하고 강의 카테고리에 따라 분류하여 볼 수 있음| 
|강의 구매 전 상세|상세 페이지에서 바로구매 / 장바구니 / 강의 미리보기 / 수강평과 커리큘럼을 볼 수 있다.|
|강의 구매 후 상세|강의 구매후 상세 페이지에서는 강의의 커리큘럼에 따라 영상을 재생할 수 있다.|

## 3. 강의 구매

|기능|내용|
|------|---|
|결제|결제하기 선택 시 결제 페이지로 이동하며, 결제 api를 통해 결제할 수 있다.|
|장바구니|회원은 상품 상세에서 장바구니 등록 가능하며 장바구니 목록에서 상품 선택 후 결제 OR 삭제 가능하다.|

## 4. 직업 정보 탐색

|기능|내용|
|------|---|
|직업 정보|직업 정보를 그래프화 하여 보다 편리하게 볼 수 있다.|
|자격증 정보|필요한 자격증을 클릭하면 해당 자격증에 대한 사이트로 리다이렉트 된다.|
|페이징|한 페이지 당 10개의 글을 볼 수 있다.|

## 5. 채용 공고 확인
|기능|내용|
|------|---|
|채용 정보|채용하는 회사의 정보와 모집 요강을 확인 할 수 있다.|
|검색|지역, 직종, 경력, 급여, 사원수로 검색 할 수 있다.|
|페이징|한 페이지 당 여러 개의 글을 볼 수 있다.|

## 6. 알림
|기능|내용|
|------|---|
|초대 알림|내가 그룹에 초대되면 알림을 받을 수 있다.|
|댓글 알림|내가 작성한 그룹게시판에 댓글이 달리면 알림을 받을 수 있다.|

## 7. 자동응답 챗봇
|기능|내용|
|------|---|
|자동응답|자연어 패턴분석을 통해 사용자가 보낸 메세지에 따라 사이트 이용에 대한 편의기능을 답해줌|
|채팅|챗봇과 대화중 상담원 연결을 칠 경우 상담원과의 1:1 채팅기능을 사용 할 수 있다.|

## 8. 매칭 게시판
|기능|내용|
|------|---|
|매칭 게시판|회원들이 그룹을 만들 수 있는 게시판이다. 글 작성후 작성된 댓글에서 초대할 수 있다. 글 작성과 수정을 할 때는 에디터 api를 사용하여 사진 첨부와 글을 꾸밀 수 있다.|
|댓글|댓글과 대댓글 작성, 수정, 삭제 가능하고 여러명이 작성 가능하다.(Ajax 활용)|
|페이징|한 페이지당 10개의 게시물을 보여준다.|

## 9. 그룹
|기능|내용|
|------|---|
|그룹|회원들은 그룹창에서 그룹을 만들 수 있으며 그룹리스트에서 그룹 채팅방으로 접속이 가능하다.|
|채팅|그룹 채팅방에 접속한 회원들은 그룹원끼리 단체 채팅이 가능하다|

## 10. 자유게시판
|기능|내용|
|------|---|
|자유게시판|회원들이 자유롭게 이용하는 게시판이다. 후기게시판은 글 작성(구매자), 수정, 삭제, 좋아요, 신고가 가능하다. 글 작성과 수정을 할 때는 에디터 api를 사용하여 사진 첨부와 글을 꾸밀 수 있다.|
|신고|회원들은 구매회원들의 후기글을 보고 신고사유(욕설/혐오/차별적 표현, 부적절한 내용, 기타)와 함께 글을 작성하여 신고 가능하다.|
|댓글|댓글과 대댓글 작성, 수정, 삭제 가능하고 여러명이 작성 가능하다.(Ajax 활용)|
|페이징|한 페이지당 10개의 게시물을 보여준다.|

## 11. 질문게시판
|기능|내용|
|------|---|
|질문게시판|회원들이 질문 할 때 이용하는 게시판이다. 글 작성, 수정, 삭제가 가능하다. 글 작성과 수정을 할 때는 에디터 api를 사용하여 글을 꾸밀 수 있다.|
|댓글|댓글과 대댓글 작성, 수정, 삭제 가능하고 여러명이 작성 가능하다.(Ajax 활용)|
|페이징|한 페이지당 10개의 게시물을 보여준다.|

## 12. 공지사항
|기능|내용|
|------|---|
|질문게시판|회원들이 질문 할 때 이용하는 게시판이다. 글 작성, 수정, 삭제가 가능하다. 글 작성과 수정을 할 때는 에디터 api를 사용하여 사진 첨부와 글을 꾸밀 수 있다.|
|댓글|댓글과 대댓글 작성, 수정, 삭제 가능하고 여러명이 작성 가능하다.(Ajax 활용)|
|페이징|한 페이지당 20개의 게시물을 보여준다.|



# 📹 실행화면
## 1. 메인화면
![메인](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/ec3966d0-ea3f-4db1-adc1-6acfb5a43346)
![메인2](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/1d474d27-110e-4126-a8a2-ed1cb58c6ad9)
![메인3](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/85079ba0-7635-4aa1-b5ff-13003e8b8ccd)

## 2. 회원관리
![mbti](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/271208a7-c6d7-431e-a28a-f9a818d3690c)
![MBTI2 1](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/6eb9146b-f589-4465-a383-ba5cb8d5bd13)

## 3. 온라인 강의
![상품 리스트](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/901d7233-a2e1-4f3d-b081-df0767926398)
![비교함](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/ca5607a0-156f-49ff-a392-bf8186e05aff)
![상품 상세](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/9e40778d-cd75-4c74-a1d4-150cd9d78e76)
![상품 상세_후기](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/44640c4e-cd80-4578-b8fc-f65c7d35937b)
![상품 상세_문의](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/f7de7d2a-2a3a-4bda-b3f9-ff6c1c8cba5e)
![상품상세_sns](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/79fd0ff6-0751-46d5-8af1-a97418df8c11)

## 4. 강의 구매
![상품 주문](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/94f9852a-5693-4326-b36b-1028b27a5338)
![image](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/3d0cd7fa-d0ef-42c1-9cda-f6a2b8fc435a)

## 5. 직업 정보 탐색
![찜 리스트](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/48788482-37dc-4509-92c9-75a43a7d53be)
![장바구니 리스트](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/d6c52874-5647-4647-a279-4e174971548e)

## 6. 채용공고 확인
![알림](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/a9d9c175-a89f-43d1-b466-6d83ee94a707)

## 7. 알림
![1ㄷ1채팅](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/f64885be-1def-49f9-bbc1-d89370b27e85)

## 8. 자동응답 챗봇
![문의 작성](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/4c1726da-4684-47fe-a13d-5697a3be28c8)
![문의 수정](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/5501d14a-21ca-4cfa-b1b1-1d3301470aee)
![문의 게시판 리스트](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/8cec6339-6af6-45bb-a9a2-a5e4bcb67416)
![문의 상세](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/ccfb2f38-a8ce-4cd5-82de-f50d22d20e2c)

## 9. 매칭게시판
![리뷰 등록](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/5a9e2e25-f7f1-4160-93a9-e350fa2bc02d)
![리뷰 수정](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/b273265c-ea8e-4dc3-986b-22dfcafaa45c)
![리뷰 리스트](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/d0e27430-dd5e-4084-a1a9-0ae0dde0a458)
![리뷰 상세](https://github.com/2211SpringCloudWebApp/PerfumePalette/assets/119032790/1a1ecb1f-2861-47b4-9b58-8cdca5b24ac4)

## 10. 그룹

## 11. 자유게시판

## 12. 질문게시판

## 13. 공지사항














