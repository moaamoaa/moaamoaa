import React from 'react';
import TeamApplyOffer from 'components/team/TeamApplyOffer';
import ProfileApplyOffer from 'components/profile/ProfileApplyOffer';
import ProjectStudy from 'components/team/ProjectStudy';
import ChattingRoom from 'components/profile/ChattingRoom';

export default function ErrorPage() {
  return (
    <div>
      <h1>버튼들</h1>
      <TeamApplyOffer>팀 - 받은 지원과 보낸 제안</TeamApplyOffer>
      <ProfileApplyOffer>프로필 - 보낸 지원과 받은 제안</ProfileApplyOffer>
      <ProjectStudy>팀관리 - 프로젝트와 스터디</ProjectStudy>
      <ChattingRoom>채팅아이콘</ChattingRoom>
    </div>
  );
}
