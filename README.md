# BEDV1_Ddaangn

당근마켓 클론코딩 프로젝트 땅근마켓

## Project Settings

```sh
git clone https://github.com/prgrms-be-devcourse/BEDV1_Ddaangn.git

docker-compose up -d # 개발 환경 도커 셋팅
```

<hr/>

## 프로젝트 전체 구조

~~(예정)~~

<hr/>

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
