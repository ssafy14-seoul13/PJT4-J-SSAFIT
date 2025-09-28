# PJT4-J-SSAFIT
태리, 지영 싸핏 PJT4 BACK DB

## 프로젝트 소개 및 개요
**프로젝트 기간**
- 2025.09.26 ~ 2025.09.28.

**프로젝트 목표**
- JSP, Servlet, Session 관리 같은 Java 웹 기술을 활용
- MVC 아키텍처 기반 역할 분담으로 유지보수와 확장성이 뛰어난 시스템 설계
- 로컬 스토리지 CRUD 기능 구현 (리뷰, 로그인 기능, 영상 데이터 관리)
- 프론트엔드와 백엔드 통합으로 동적 웹 애플리케이션 개발

**프로젝트 설명**
- "SSAFIT"은 **운동 영상을 공유하고 소통할 수 있는 플랫폼**입니다.
- 사용자들은 **관심 영상을 공유하고 시청**할 수 있습니다.
- 운동을 한 뒤, 운동 영상에 **댓글 업로드, 수정, 삭제**할 수 있습니다.

<br>

## 팀 소개
<table><tr><td align="center">
<img width="225" height="225" src="https://github.com/user-attachments/assets/8bada491-2cc1-42c2-9a13-35f5458fa04f"/></td><td align="center">
<img width="225" height="225" alt="image" src="https://github.com/user-attachments/assets/126a8bf0-b0b3-4a4c-9413-b7645ebc4875" /></td></tr><tr><td align="center">
TEAM짱 <a href="https://github.com/zzero23"><strong>안태리<strong></a></td><td align="center">
TEAM1 <a href="https://github.com/zzero23"><strong>변지영<strong></a></td></tr></table>

<br>

## 기술 및 개발 환경
**Backend**
<p>
  <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white" alt="Java" height="28" style="margin-right:8px;"/>
  <img src="https://img.shields.io/badge/JSP-6A6A6A?style=for-the-badge&logo=jsp&logoColor=white" alt="JSP" height="28" style="margin-right:8px;"/>
  <img src="https://img.shields.io/badge/Servlet-6A6A6A?style=for-the-badge&logo=apachetomcat&logoColor=white" alt="Servlet" height="28" style="margin-right:8px;"/>
</p>

**Frontend**
<p>
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white" alt="HTML5" height="28" style="margin-right:8px;"/>
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white" alt="CSS3" height="28" style="margin-right:8px;"/>
</p>

**Server**
<p>
  <img src="https://img.shields.io/badge/Apache_Tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black" alt="Apache Tomcat" height="28" style="margin-right:8px;"/>
</p>

**Develpment Environment**
<p>
  <img src="https://img.shields.io/badge/STS-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="STS (Spring Tool Suite)" height="28" style="margin-right:8px;"/>
  <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white" alt="Git" height="28"/>
</p>

<br>

## 디렉토리 구조
```
📁 ssafit
├─ 📁 src
│  └─ 📁 main
│  ├─ 📁 java
│  │  └─ 📁 com/ssafy/ssafit
│  │     ├─ 📁 app                  # FrontController.java
│  │     │  └─ FrontController.java
│  │     ├─ 📁 controller           # MVC Controller (Servlet)
│  │     │  ├─ ReviewController.java
│  │     │  ├─ UserController.java
│  │     │  └─ VideoController.java
│  │     ├─ 📁 model
│  │     |  ├─ Review.java
│  │     │  ├─ User.java
│  │     │  └─ Video.java
│  │     ├─ 📁 repository           # Data access (JSON loading / memory storage)
│  │     ├─ 📁 service              # Business logic
│  │     │  ├─ review/
│  │     │  ├─ user/
│  │     │  └─ video/
│  │     └─ 📁 util
│  │        └─ IdGenerator.java     # 고유 ID 생성기
│  ├─ 📁 resources
│  │  └─ 📁 data/json               # 영상 JSON 데이터 (db 대신 사용)
│  │     └─ video.json
│  └─ 📁 webapp
│     ├─ 📁 WEB-INF
│     │  ├─ 📁 error                # 403.jsp, 404.jsp, 409.jsp 등 에러 컴포넌트
│     │  ├─ 📁 user                 # login.jsp, register.jsp, mypage.jsp
│     │  ├─ 📁 video                # create.jsp, detail.jsp, list.jsp, search.jsp, update.jsp
│     │  └─ 📁 review               
│     └─ index.jsp                  # 메인 페이지
└─ README.md
```
> PJT4-J-SSAFIT 이 워크스페이스고, ssafit이 프로젝트 폴더이다.

<br>

## 주요 기능
**사용자 기능**
- 사용자 CRUD
- 로그인, 로그아웃 <br>
**영상 기능**
- 영상 CRUD
- 부위별, 트레이너 별 영상 필터링 <br>
**댓글 기능**
- 댓글 CRUD (이때, 본인이 작성한 댓글만 수정 및 삭제 가능)
 <br>

 ## 역할 분담
 <table> <tr> <th align="center"> 안태리 </th> <th align="center"> 변지영 </th> </tr> <tr> <td> <ul> <li> 전체적인 설계 지도</li> <li> 영상 CRUD 구현</li> <li>웹 미감 지도</li> </ul> </td> <td> <ul> <li> 사용자 CRUD 구현</li> <li> 댓글 CRUD 구현</li> </ul> </td> </tr> </table>

<br>

##

## 고민점
frontcontroler 하나로 모든 요청을 받아 usercontroler, videocontroler 등 해당하는 컨트롤러로 분배하려고함
이렇게 하면 잘못된 주소 입력시 전부 직접만든 에러페이지로 보낼수있어서 해보려 했으나... 시간이슈로 못함

각 객체의 고유 아이디를 따로 고유id 생성기로 만듬. 이유는 서버가꺼져도 db기반으로 중복되지않는 id를 만들고싶어서, 하지만 시간이슈로 그런기능은 상상만하고 단순 랜덤생성으로 구현되어있음. 

json을 db처럼 사용하고싶었다. repository로 json파일 읽고 쓰기로 db를 흉내내려고 했으나... 파일경로 읽는 이슈로 로드만함.
웹서버를 실행하면 톰캣이나 이클립스 입장에선 내가 만든 폴더경로에서 시작하는게 아니라 임시폴더경로나 톰캣 카타리나 기준으로 동작해서 그럼

만들면서 서비스와 컨트롤러의 역할을 침범하게 짜는 경우가 있었음 최대한 비즈니스 로직은 서비스에 옮겨 담으려고 노력함. 아무튼 시행착오 겪으며 역할 분배를 느낄수있었음.

createAt이나 조회수 증가를 처음엔 dto에서 동작하게 했는데 비즈니스로직은 전부 서비스에서 이루어지도록 하려고 노력함.

간단한 예외처리들(중요한 파라미터가 없다던가 등) 을 하면서 중복되게 만든 부분이 많아 이러한 검사는 어디서 해야하는지 중복으로하지않고 앞부분에서만 끝내면 되는지
이런 고민을 하게 되었고 아 이래서 필터를 만들었다면 편했겠다고 느낌.


## todo 
권한 검사 필터로 추가
json 저장 기능
고유id생성기 db(json) 데이터에서 중복(충돌) 검사 통과후 신규id값 반환하도록 만들기 (근데 이러면 오래걸리는거 아님?)
검색 알고리즘 개선

