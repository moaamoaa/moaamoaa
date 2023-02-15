import { useNavigate } from 'react-router-dom';
import { handleOpenTeamDetail } from 'redux/team';
import { handleMemberId } from 'redux/member';
import { useDispatch, useSelector } from 'react-redux';
import { handleProfilePk } from 'redux/profile';
import MyProjectStudy from 'components/team/MyProjectStudy';
import styled from 'styled-components';
import useMobile from 'hooks/useMobile';
import {
  Card,
  CardContent,
  CardMedia,
  Typography,
  CardActions,
  Button,
  Grid,
  Skeleton,
  Avatar,
} from '@mui/material';
import { handleSuccessState } from 'redux/snack';
import CardList from 'components/common/card/CardList';
import { Link } from '@mui/material';

import scrollToTop from 'utils/scrollToTop';

import VisibilityOutlinedIcon from '@mui/icons-material/VisibilityOutlined';
import GroupsOutlinedIcon from '@mui/icons-material/GroupsOutlined';
import customAxios from 'utils/axios';

export default function CardItem(props) {
  const projectId = useSelector(state => state.team.projectId);
  const leader = useSelector(state => state.team.leader);
  const leaderId = useSelector(state => state.team.leaderId);
  const userPk = useSelector(state => state.user.userPk);
  const isMobile = useMobile();
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const goToDetail = () => {
    if (props.type === 'team') {
      dispatch(handleOpenTeamDetail({ projectId: props.card.id }));
      navigate(`/TeamDetailPage/?projectId=${props.card.id}`);
      scrollToTop();
    } else if (props.type === 'member') {
      dispatch(handleProfilePk({ id: props.card.id }));
      navigate('/ProfilePage');
      scrollToTop();
    }
  };

  const handleSaveMemberId = () => {
    if (props.type === 'member') {
      dispatch(handleMemberId({ memberId: props.card.id }));
    }
  };

  // 멤버 카드일 경우,
  // 권한위임하기 요청
  // 성공!
  const handleRight = () => {
    if (props.type === 'member') {
      customAxios
        .authAxios({
          method: 'PUT',
          url: '/projects/leader',
          data: {
            projectId: projectId, // detail페이지의 projectId 값
            userId: props.card.id, // 권한 위임 당할 사람 member 카드 id
          },
        })
        .then(response => {
          dispatch(
            handleSuccessState({
              open: true,
              message: '권한이 위임 되었습니다.',
              severity: 'success',
            }),
          );
          console.log(response.data);
          console.log('권한 위임 완료!');
        })
        .catch(error => {
          console.log(error);
        });
    }
  };

  // 멤버 카드일 경우,
  // 강퇴하기 요청
  // 성공!
  const handleDrop = () => {
    if (props.type === 'member') {
      customAxios
        .authAxios({
          method: 'DELETE',
          url: '/projects/member',
          data: {
            projectId: projectId, // detail페이지의 projectId 값
            userId: props.card.id, // 강퇴 당할 사람 member 카드 id
          },
        })
        .then(response => {
          dispatch(
            handleSuccessState({
              open: true,
              message: '팀원이 강퇴되었습니다.',
              severity: 'success',
            }),
          );
          console.log(response.data);
          console.log('팀원 강퇴 완료!');
        })
        .catch(error => {
          console.log(error);
        });
    }
  };

  // 리턴

  if (props.type === 'team') {
    return (
      <MoaCard onClick={goToDetail}>
        {props.card.img ? (
          <CardMedia
            component="img"
            src={props.card.img}
            alt="random"
            sx={{
              position: 'relative',
              backgroundSize: 'cover',
              backgroundRepeat: 'no-repeat',
              backgroundPosition: 'center',
              height: `200px`,
              margin: '0 auto',
            }}
          />
        ) : (
          <Skeleton variant="rectangular" height={200} />
        )}
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            {props.card.title}
          </Typography>
          <Grid container paddingY={1}>
            <Grid item xs={4} display={'flex'} alignItems="center">
              <Typography variant="body2" color="#888">
                팀장 {props.card.leaderName}
              </Typography>
            </Grid>
            <Grid item xs={2} display={'flex'} alignItems="center">
              <VisibilityOutlinedIcon
                fontSize="small"
                sx={{ color: '#888' }}
              ></VisibilityOutlinedIcon>
              <Typography variant="body2" color="#888">
                {props.card.hit}
              </Typography>
            </Grid>
            <Grid item xs={6} display={'flex'} alignItems="center">
              <GroupsOutlinedIcon
                fontSize="small"
                sx={{ color: '#888' }}
              ></GroupsOutlinedIcon>
              <Typography variant="body2" color="#888">
                {props.card.currentPeople} / {props.card.totalPeople}
              </Typography>
            </Grid>
          </Grid>
          <CardList type="tech" cards={props.card.techStacks}></CardList>
          <InfoTypography paddingTop={1} variant="body2" color="#000">
            {props.card.contents
              ? props.card.contents
              : `${props.card.title}팀 입니다.`}
          </InfoTypography>
        </CardContent>
      </MoaCard>
    );
  } else if (props.type === 'member') {
    // 본인 팀이면 콘솔 안 찍힘
    console.log(props.isDetail);
    console.log(leader);
    console.log(props.card.id);
    console.log(leaderId);
    return (
      <MoaCard>
        <CardActions>
          <Grid container>
            <Grid item xs>
              {/* 디테일 페이지에서만 보이고 리더이면서 해당 카드 id 가 리더가 아닌경우 => 권한위임 버튼이 보이고 아니면 안 보이고 */}
              {props.isDetail && leader && props.card.id !== leaderId ? (
                <Button
                  size="small"
                  variant="contained"
                  color="primary"
                  onClick={handleRight}
                >
                  권한위임
                </Button>
              ) : (
                <Button
                  size="small"
                  variant="contained"
                  color="primary"
                  sx={{ display: 'none' }}
                >
                  권한위임
                </Button>
              )}
            </Grid>
            <Grid item xs>
              {/* 디테일 페이지에서만 보이고 리더이면서 해당 카드 id 가 리더가 아닌경우 => 강퇴하기 버튼이 보이고 아니면 안 보이고 */}
              {props.isDetail && leader && props.card.id !== leaderId ? (
                <Button
                  size="small"
                  variant="contained"
                  color="primary"
                  onClick={handleDrop}
                >
                  강퇴하기
                </Button>
              ) : (
                <Button
                  size="small"
                  variant="contained"
                  color="primary"
                  sx={{ display: 'none' }}
                >
                  강퇴하기
                </Button>
              )}
            </Grid>
            <Grid item xs>
              {/* 나 자신에게는 제안하지 않는다 */}
              {/* 이미 내 팀인 사람에게는 제안하지 않는다 detail_profileResultDtoList*/}
              {userPk !== props.card.id ? (
                <Button
                  size="small"
                  variant="contained"
                  color="primary"
                  onClick={handleSaveMemberId}
                >
                  {/* 제안 */}
                  <MyProjectStudy isMobile={isMobile}></MyProjectStudy>
                </Button>
              ) : (
                <Button
                  size="small"
                  variant="contained"
                  color="primary"
                  onClick={handleSaveMemberId}
                  sx={{ display: 'none' }}
                >
                  {/* 제안 */}
                  <MyProjectStudy isMobile={isMobile}></MyProjectStudy>
                </Button>
              )}
            </Grid>
          </Grid>
        </CardActions>
        {props.card.img ? (
          <CardMedia
            onClick={goToDetail}
            component="img"
            src={props.card.img}
            alt="random"
            sx={{
              borderRadius: '50%',
              width: '100px',
              height: '100px',
              margin: '0 auto',
              boxShadow: 5,
              cursor: 'pointer',
            }}
          />
        ) : (
          <MoaSkeleton variant="circular" width={100} height={100} />
        )}
        <CardContent>
          <MoaTypography gutterBottom variant="h5" component="div">
            {props.card.nickname}
          </MoaTypography>
          <MoaTypography variant="body2" color="text.secondary">
            {props.card.area ? props.card.area : '온라인 / 전 지역'}
          </MoaTypography>
          <InfoTypography variant="body2" color="text.secondary">
            {props.card.contents
              ? props.card.contents
              : `안녕하세요. ${props.card.nickname}입니다.`}
          </InfoTypography>

          <CardList type={'tech'} cards={props.card.techStacks}></CardList>
        </CardContent>
      </MoaCard>
    );
  } else if (props.type === 'tech') {
    return <MoaImg src={props.card.logo} />;
  } else if (props.type === 'link') {
    return (
      <Link href={props.card.site} target="_blank">
        <MoaImg src={props.card.site} />
      </Link>
    );
  }
}

const MoaCard = styled(Card)`
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
  transition: 0.4s;
  &:hover {
    scale: 1.04;
    box-shadow: 0px 4px 16px rgba(0, 0, 0, 0.2);
    transition: 0.4s;
  }
`;

const MoaSkeleton = styled(Skeleton)`
  width: 100px;
  height: 100px;
  margin: 0 auto;
`;

const InfoTypography = styled(Typography)`
  text-align: justify;
  height: 60px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
`;

const MoaTypography = styled(Typography)`
  display: flex;
  justify-content: center;
`;

const MoaImg = styled(Avatar)`
  min-width: 40px;
  min-height: 40px;
  box-shadow: 0px 4px 5px rgba(0, 0, 0, 0.25);
`;
