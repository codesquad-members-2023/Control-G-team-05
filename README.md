# Control-G-team-05
> 2023 마스터즈 맥스 팀 프로젝트
- 미션 기간: `2023-10-10 ~ 2023-11-3` [4 Week]

## 구성원

<table>
  <tr>
    <th colspan="1">#FE</th>
    <th colspan="3">#BE</th>
  </tr>
  <tr>
    <td width="150">
      <img src="https://avatars.githubusercontent.com/u/98310007?v=4" />
    </td>
    <td width="150">
      <img src="https://avatars.githubusercontent.com/u/100547825?v=4" />
    <td width="150">
      <img src="https://avatars.githubusercontent.com/u/121915790?v=4" />
    </td>
    <td width="150">
      <img src="https://avatars.githubusercontent.com/u/108439935?v=4" />
    </td>
  </tr>
  <tr>
    <td align="center">
      <code><a href="https://github.com/CDBchan"><strong>charlie</strong></a></code>
    </td>
    <td align="center">
      <code><a href="https://github.com/DOEKYONG"><strong>light</strong></a></code>
    </td>
    <td align="center">
      <code><a href="https://github.com/he2joojo"><strong>joy</strong></a></code>
    </td>
    <td align="center">
      <code><a href="https://github.com/Jeongwisdom"><strong>위즈</strong></a></code>
    </td>
  </tr>
</table>

## 프로젝트 관리

### 그라운드 룰

- 온라인으로 소통 잘하기
  - 프론트, 백도 매일 매일 소통 필요
  - 슬랙에 다른 팀원이 무언가를 올렸으면 확인하고 이모지라도 체크해주기
  - 구두로 논의한 것들이라도 슬랙에도 공유하기! (한 번 더 올려서 리마인드 하기)
- API 명세가 변경되면 슬랙으로 알려주기 + 회의 안건에 추가하기
- ✨`한 번` 지각하면 2000원 내기✨** (지각 체크한다음에 제일 지각 안한사람이 다가져가기)
- 지각 기준: [네이버 시계](https://time.navyism.com/?host=naver.com) 10:05:59 까지 봐줍니다.

</br>

### 데일리 스크럼 룰

- 시간 : 10:06 ~ 10:30
- **스크럼** **내용:** 컨디션(10점 만점) / 어제 한 일 / 오늘 할 일

***Scrum Master***

- 스크럼 마스터 지각 시 다음날 마스터와 순서 바꾸기

| 1주차 | 2주차 | 3주차  | 4주차 |
| ----- | ----- | ------ | ----- |
| 위즈  | 찰리  | 라이트 | 조이  |

</br>

### 🌳 브랜치 전략

#### 1. 작업 브랜치 (Feature Branch)
- **출발 브랜치**: `dev-be`
- **브랜치명**: `be/feature/#이슈번호-이슈명`
- **PR 전략**: Squash Merge
- **병합 대상 브랜치**: `dev-be`

#### 2. 수정 브랜치 (Hotfix Branch)
- **출발 브랜치**: `dev-be`
- **브랜치명**: `be/hotfix/#이슈번호-이슈명`
- **PR 전략**: Squash Merge
- **병합 대상 브랜치**: `dev-be`

#### 3. 배포 브랜치 (Release Branch)
- **출발 브랜치**: `dev-be`
- **PR 전략**: Merge
- **병합 대상 브랜치**: `release`



### 📝 커밋 컨벤션

**형식**: `#이슈번호 [BE/FE] 타입: 내용`

**예시**: 

```plaintext
#12 [BE] feat: 로그인 구현
```

### PR 제목

- **형식**: `#이슈번호 [BE/FE] 타입: 이슈제목`
- **예시**: 

```
#12 [BE] feat: 로그인 구현
```



### 스쿼시 머지 커밋명
- **형식**: `#이슈번호 [BE/FE] 타입: 이슈제목 #PR번호`
- **예시**: 

```
#12 [BE] feat: 로그인 구현 #16
```



### 링크
[![Notion](https://img.shields.io/badge/Notion-000000.svg?style=for-the-badge&logo=Notion&logoColor=white)](https://road-marscapone-17b.notion.site/Control-G-77c107d2ff9c49d3bc7dda98a81bc80c?pvs=4)
[![Figma](https://img.shields.io/badge/Figma-F24E1E.svg?style=for-the-badge&logo=Figma&logoColor=white)](https://www.figma.com/file/Ax5lh6dI1pgCVjzRNG0dlD/%EC%BB%A8%ED%8A%B8%EB%A1%A4G?type=design&node-id=0-1&mode=design&t=dxepdvyxTwQM6KLe-0)


