# BEDV1_Ddaangn

당근마켓 클론코딩 프로젝트 땅근마켓

## Project Settings

```sh
git clone https://github.com/prgrms-be-devcourse/BEDV1_Ddaangn.git

docker-compose up -d # 개발 환경 도커 셋팅
```

<hr/>

## 팀 협업 규칙

#### 팀 규칙

#### 1. 역할

🧙‍♂️Scrum Master

- @Jaeuk Oh
- 프로젝트 동안 해야하는 일
  - 10일 동안 주어지는 스프린트 미션을 주도적으로 진행
    - 예) 다같이 미션 수행할 시간 정하기, 화상 미팅 링크 만들어서 공유하기, 온라인 협업툴 세팅하고 관리하기 등
  - 함께 일을 하기 위해 지켜야하는 규칙을 만들거나 결정
  - 팀 내부 혹은 외부에 갈등 상황이 발생했을 때 중재

👨‍⚖️ Product Owner

- @서동성
- 프로젝트 동안 해야하는 일
  - 프로젝트 목표 설정
  - 프로젝트 동안 해결할 문제 결정
  - 프로젝트 동안 해결할 문제가 제대로 해결된게 맞는지 여부 결정

🧑‍💻 Developers

- @Jaeuk Oh
- @서동성
- @황일용
- 프로젝트 동안 해야하는 일
  - 프로젝트 동안 해결할 문제의 해결책 결정
  - 프로젝트 동안 해결할 문제의 해결책 실현
  - 주체적으로 진행.

#### 2. 프로젝트 관리

🔎 업무의 시각화

- Notion - TimeLine
- Jira Issue Track

🗂 업무 관리 기능

- Notion Kanban Board

⏰ 리마인더 기능 (예정된 일정, 업무등)

- Notion TimeLine ?

#### 3. 커뮤니케이션

#### 슬랙 그룹 다이렉트 메시지에서 소통

- 하루에 한 번 데일리 스크럼 진행
  - `진행상황`, `이슈사항` 공유
- 데일리 스크럼
  - 개발팀을 위해 **15분**으로 시간이 고정된 미팅
  - 수행한 작업을 검토하고 다음 스프린트 작업을 예측하여 팀 협업과 성능 향상
  - 커뮤니케이션을 증진하고 개발에 방해되는 요소를 확인
  - 빠른 의사 결정을 강조하는 스크럼의 핵심인 **검토**와 **적응**에 기반한 미팅

```
데일리 스크럼 : 14시
코어 타임 : 14 ~ 19시

회고 타임 : 18 ~ 19시
```

### 팀 프로젝트의 목적이 협업인 만큼 그와 관련된 활동하기

- 의견은 자유롭게 (단, 충분한 근거와 함께)
- PR 보내고 각자 코드 리뷰.
  - PR 리뷰어 선정은 팀원 모두
  - 리뷰어는 12시간 이내에 리뷰하기
    - 리뷰이는 피드백을 빨리 받아야 하는 상황이면 슬랙으로 리뷰 요청
  - 👍🏻 따봉 많이 해주기
  - 코드리뷰 내용 반영할때마다 커밋 id남기기 (리뷰어에게 반영했다고 알려주기)
- 설계 & 기능 변경이 필요하다고 생각하면 PO에게나 팀 슬랙에 회의 신청
- 페어 프로그래밍 ??

------

## 4. 협업 도구

### JIRA

[JIRA](https://velog.io/@markyang92/JIRA)

[[Jira Software\] Scrum 보드 사용법 간단 정리](https://bbangson.tistory.com/41)

[Jira와 GitHub 연동](https://prohannah.tistory.com/117)

- 스프린트

  - ```
    **Epic**
    ```

     : 

    큰 단위의 업무

    (여러 유저 스토리, 작업 등을 묶은 단위)

    - ```
      **Story**
      ```

       : 

      최종 사용자 관점

      에서 

      요구 사항

      이나 

      요청

      을 기록하는 이슈 유형

      - `**Sub-Task` : 스토리**를 **실제 구현**하기 위한 **기술적인 내용**을 하위 작업으로 분리

    - `**Task**` : 팀에서 수행해야하는 단위 작업

### Notion

- 회의 및 데일리 스크럼 기록
- 프로젝트와 관련된 문서 기록

### Github

- Repository
  - [prgrms-be-devcourse](https://github.com/prgrms-be-devcourse)/[BEDV1_Ddaangn](https://github.com/prgrms-be-devcourse/BEDV1_Ddaangn)
- PR

------

### 프로젝트 환경

- OS : Window 10

- 개발환경 : IntelliJ Ultimate Version
- Back END
  - 프레임워크 : Spring Boot
  - 언어 : JAVA 11
  - DB : MySQL 8 (Docker 이용)
  - JPA
  - Chat : WebSocket 
  - Security : Oauth 2.0
  - REST DOCS

## 프로젝트 전체 구조

![image-20211106210006939](https://raw.githubusercontent.com/prgrms-be-devcourse/BEDV1_Ddaangn/main/img/image-20211106210006939.png)

<hr/>

## 프로젝트 전체 패키지

```
.
└── Ddaangn
    └── src
        ├── docs
        │   └── asciidoc
        └── main
            ├── java
            │   └── com
            │       └── dev
            │           └── ddaangn
            │               ├── badge
            │               │   ├── controller
            │               │   ├── converter
            │               │   ├── dto
            │               │   ├── repository
            │               │   └── service
            │               ├── chat
            │               │   ├── config
            │               │   ├── controller
            │               │   ├── dto
            │               │   └── repository
            │               ├── common
            │               │   ├── api
            │               │   ├── dto
            │               │   ├── error
            │               │   │   └── exception
            │               │   ├── handler
            │               │   └── query
            │               ├── evaluation
            │               │   ├── controller
            │               │   ├── converter
            │               │   ├── domain
            │               │   ├── dto
            │               │   │   ├── request
            │               │   │   └── response
            │               │   ├── repository
            │               │   ├── role
            │               │   └── service
            │               ├── image
            │               │   ├── controller
            │               │   ├── converter
            │               │   ├── dto
            │               │   ├── repository
            │               │   └── service
            │               ├── like
            │               │   ├── domain
            │               │   ├── dto
            │               │   │   └── response
            │               │   ├── repository
            │               │   └── service
            │               ├── post
            │               │   ├── controller
            │               │   ├── converter
            │               │   ├── domain
            │               │   ├── dto
            │               │   │   ├── request
            │               │   │   └── response
            │               │   ├── repository
            │               │   └── service
            │               └── user
            │                   ├── config
            │                   │   └── auth
            │                   │       └── dto
            │                   ├── controller
            │                   ├── dto
            │                   │   ├── request
            │                   │   └── response
            │                   ├── repository
            │                   ├── role
            │                   ├── service
            │                   └── vo
            └── resources
                └── templates
```

### DB Table

![image-20211106204858647](https://raw.githubusercontent.com/prgrms-be-devcourse/BEDV1_Ddaangn/main/img/image-20211106204858647.png)



## 프로젝트 세부 태스크

### 📌 Code convention

1. Java Naming Convention
   - [참고 링크](https://devlsh.tistory.com/entry/Java-Naming-Convention)
2. Google Code Style을 준수
   - [참고 링크](https://google.github.io/styleguide/javaguide.html)

### 📌 git branch 관리 전략

![우아한형제들 기술블로그 - 우린 Git-flow를 사용하고 있어요](https://techblog.woowahan.com/wp-content/uploads/img/2017-10-30/git-flow_overall_graph.png)

- main : 배포 stage
   - `prgrms-be-devcourse/BEDV1_Ddaangn`의 `main` 브랜치, `develop` 브랜치에서 배포를 위해 소스코드를 저장하는 브랜치

- develop : 테스트/개발 stage
   - `prgrms-be-devcourse/BEDV1_Ddaangn`의 `develop` 브랜치, `feature/도메인`의 소스코드를 Pull Request을 통해 리뷰어 2명 이상의 허락을 받아야 하는 저장되는 브랜치

- hotfix : 배포 진행 후 발생한 버그를 수정해야 할 때 사용
   - `prgrms-be-devcourse/BEDV1_Ddaangn`의 `hotfix` 브랜치, main에 올라온 소스코드를 배포한 이후에 생기는 문제를 해결하기 위한 브랜치
  
- feature : 기능 추가 브랜치
   - `prgrms-be-devcourse/BEDV1_Ddaangn`의 `feature/user`, `feature/post`처럼 자신이 담당하는 도메인으로 브랜치를 생성하고, 개인 로컬 환경에서 개발한 소스코드를 저장하기 위한 브랜치

> 참고 링크 - [우아한 형제들 기술 블로그](http://woowabros.github.io/experience/2017/10/30/baemin-mobile-git-branch-strategy.html)

### 📌 Git Commit Message Type

- FEAT : 새로운 기능의 추가
- FIX: 버그 수정
- DOCS: 문서 수정
- STYLE: 스타일 관련 기능(코드 포맷팅, 세미콜론 누락, 코드 자체의 변경이 없는 경우)
- REFACTOR: 코드 리펙토링
- TEST: 테스트 코트, 리펙토링 테스트 코드 추가
- CHORE: 빌드 업무 수정, 패키지 매니저 수정(ex .gitignore 수정 같은 경우)

> 참고 링크 - [\[Git\] 깃 커밋 메시지 작성법(git commit message)](https://richone.tistory.com/26)

<hr/>

