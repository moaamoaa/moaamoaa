# MoaaMoaa

## 소개

Front-End README입니다.

## 사용법

1. 리포지토리를 복제하거나 다운로드합니다.
2. `npm install` 하여 종속성을 설치합니다.
3. `npm start` 하여 개발 서버를 시작합니다.
4. 브라우저에서 http://localhost:3000 방문하십시오 .

## 특징

추후 작성 예정입니다.

## 기술스택

- React
- Redux
- React Route
- React Hook
- Axios
- Meterial UI
- CSS-in-js

## 폴더 구조

```bash
src/
├── components/   # 모든 컴포넌트
|   ├── commons/        # 공통 컴포넌트
|   |   ├── button/             # 컴포넌트
|   |   |   └── ScrollToTop.jsx         # 컴포넌트 로직
|   |   └── ...
|   ├── profile/        # 기능 컴포넌트
|   └── ...
├── pages/        # 라우트 기반 Page
|   ├── HomePage.jsx    # 홈 페이지 컴포넌트
|   ├── ...
├── hooks/        # 모든 Hooks
|   ├── useIsAtTop.js    # custom hook
|   └── ...
├── redux/        # Redux store
|   ├── store.js        # store 생성
|   └── ...
├── utils/        # 공통 기능
|   ├── api.js          # API 통신 관련 기능
|   ├── axios.js        # Custom Axios 기능
|   └── ...
├── App.jsx       # 애플리케이션을 구성하는 기본 컴포넌트
└── index.js      # 애플리케이션을 렌더링하는 엔트리 파일

```

## 개발 기간

2023.01.16~
