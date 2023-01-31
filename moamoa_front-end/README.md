# MoaMoa

## 소개

## 사용법

1. 리포지토리를 복제하거나 다운로드합니다.
2. `npm install` 하여 종속성을 설치합니다.
3. `npm start` 하여 개발 서버를 시작합니다.
4. 브라우저에서 http://localhost:3000 방문하십시오 .

## 특징

## 기술스택

- React
- Redux
- React Router
- Axios
- Semantic UI

## 폴더 구조

```bash
src/
|-- components/   # 공통 컴포넌트
|   |-- Header/   # 헤더 컴포넌트
|   |   |-- Header.jsx          # 컴포넌트 로직
|   |   |-- Header.styles.js    # 컴포넌트 스타일
|   |-- Footer/
|   |-- ...
|-- containers/   # 상태 관리 컴포넌트
|   |-- Home/           # 홈 페이지 컴포넌트
|   |   |-- Home.jsx
|   |   |-- Home.styles.js
|   |-- About/
|   |-- ...
|-- actions/      # Redux action creators
|   |-- index.js        # action creators의 집합
|   |-- types.js        # action type 정의
|-- reducers/     # Redux reducers
|   |-- index.js        # reducer의 집합
|-- store/        # Redux store
|   |-- index.js        # store 생성
|-- utils/        # 공통 기능
|   |-- api.js          # API 통신 관련 기능
|   |-- ...
|-- App.js      # 각 컴포넌트들을 결합하여 애플리케이션을 구성하는 기본 컴포넌트
|-- index.js    # 애플리케이션을 렌더링하는 엔트리 파일

```

## 개발 기간

2023.01.16~
