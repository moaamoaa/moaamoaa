import Container from '@mui/material/Container';
import TeamDetailBanner from 'components/team/TeamDetailBanner';
import TeamMemberList from 'components/team/TeamMemberList';
import MultilineText from 'components/common/option/MultilineText';
import MultipleSelect from 'components/common/option/MultipleSelect';
import SingleSelect from 'components/common/option/SingleSelect';
import SingleSelectNumber from 'components/common/option/SingleSelectNumber';
import SingleSelectOnOff from 'components/common/option/SingleSelectOnOff';
import SingleSelectRegion from 'components/common/option/SingleSelectRegion';
import Calendar from 'components/common/option/Calendar';
import Box from '@mui/material/Box';

export default function TeamDetailPage() {
  const teamDetailBanner = {
    title: '팀 이름', // project_title
    leader: '팀장 이름', // team_role
    image: 'https://source.unsplash.com/random', // project_image
  };
  return (
    <>
      <TeamDetailBanner post={teamDetailBanner} />
      <Container fixed>
        <h2>모집 정보</h2>
        <Box>
          <h4>모집 구분</h4>
          <SingleSelect></SingleSelect>
          <h4>모집 인원</h4>
          <SingleSelectNumber></SingleSelectNumber>
          <h4>모집 기간</h4>
          <Calendar></Calendar>
          <h4>진행 방식</h4>
          <SingleSelectOnOff></SingleSelectOnOff>
          <h4>지역</h4>
          <SingleSelectRegion></SingleSelectRegion>
          <h4>기술 스택</h4>
          <MultipleSelect></MultipleSelect>
        </Box>
      </Container>
      <Container fixed>
        <h2>팀 소개</h2>
        <MultilineText></MultilineText>
      </Container>
      <Container fixed>
        <h2>팀원 소개</h2>
        <TeamMemberList></TeamMemberList>
      </Container>
    </>
  );
}
