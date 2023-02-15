import React, { useState } from 'react';
import customAxios from 'utils/axios';

import { loginSuccess } from 'redux/user';
import { useDispatch } from 'react-redux';
import Cookies from 'js-cookie';

import {
  Container,
  Paper,
  Stepper,
  Step,
  StepLabel,
  Typography,
  Dialog,
} from '@mui/material/';

import DialogHeader from 'components/common/dialog/DialogHeader';
import SignUpEmailForm from 'components/signUp/SignUpEmailForm';
import SignUpCertificationForm from 'components/signUp/SignUpCertificationForm';
import SignUpNicknameForm from 'components/signUp/SignUpNicknameForm';
import SignUpPasswordForm from 'components/signUp/SignUpPasswordForm';

export default function CheckoutDialog(props) {
  const [activeStep, setActiveStep] = useState(0);
  const [email, setEmail] = useState('');
  const [code, setCode] = useState('');
  const [name, setName] = useState('');
  const [password, setPassword] = useState('');

  const dispatch = useDispatch();

  const handleEmail = emailChange => {
    setEmail(emailChange);
  };
  //인증코드
  const handleCode = codeChange => {
    setCode(codeChange);
  };
  //닉네임
  const handleName = nameChange => {
    setName(nameChange);
  };
  //비밀번호
  const handlePassword = passwordChange => {
    setPassword(passwordChange);
  };

  const handleClose = () => {
    props.setSignUpDialog(false);
    setActiveStep(0);
    setEmail('');
    setName('');
    setPassword('');
  };

  /** 맨 마지막 회원가입 버튼을 눌렀을 때 가입정보를 백앤드로 보냄*/
  const userSignUp = () => {
    if (password === '') {
    } else {
      customAxios.basicAxios
        .post('/users/signup', {
          email: email,
          nickname: name,
          password: password,
        })
        .then(e => {
          setActiveStep(4);

          // 로그인
          customAxios.basicAxios
            .post(`/users/login`, {
              email: email,
              password: password,
            })
            .then(response => {
              const token = response.data.accessToken;
              const userPk = response.data.id;

              // Set the access token
              Cookies.set('access_token', token, { expires: 1 });
              dispatch(loginSuccess({ userPk: userPk }));
            })
            .catch(error => {
              console.log(error);
            });
        })
        .catch(error => {
          console.log(error);
        });
    }
  };

  const steps = ['이메일', '인증코드', '닉네임', '비밀번호'];

  function getStepContent(step) {
    switch (step) {
      case 0:
        return (
          <SignUpEmailForm
            type="signUpEmail"
            setActiveStep={setActiveStep}
            handleCode={handleCode}
            handleEmail={handleEmail}
            email={email}
          />
        );
      case 1:
        return (
          <SignUpCertificationForm
            setActiveStep={setActiveStep}
            handleCode={handleCode}
            code={code}
            email={email}
          />
        );
      case 2:
        return (
          <SignUpNicknameForm
            setActiveStep={setActiveStep}
            handleName={handleName}
            name={name}
          />
        );
      case 3:
        return (
          <SignUpPasswordForm
            setActiveStep={setActiveStep}
            handlePassword={handlePassword}
            userSignUp={userSignUp}
            name={name}
          />
        );
      default:
        throw new Error('Unknown step');
    }
  }

  return (
    <Dialog
      onClose={handleClose}
      aria-labelledby="customized-dialog-title"
      open={props.open}
    >
      <DialogHeader onClose={handleClose}></DialogHeader>
      <Container component="main" maxWidth="sm" sx={{ mb: 4 }}>
        <Paper
          variant="outlined"
          sx={{ my: { xs: 3, md: 6 }, p: { xs: 2, md: 3 } }}
        >
          <Typography component="h1" variant="h4" align="center">
            회원가입
          </Typography>
          <Stepper activeStep={activeStep} sx={{ pt: 3, pb: 5 }}>
            {steps.map(label => (
              <Step key={label}>
                <StepLabel>{label}</StepLabel>
              </Step>
            ))}
          </Stepper>
          {activeStep === steps.length ? (
            <React.Fragment>
              <Typography variant="h5" gutterBottom>
                가입해주셔서 감사합니다.
              </Typography>
              <Typography variant="subtitle1">
                모아모아에서 팀을 만들어 어제보다 오늘 더 성장하시길 바랍니다!
              </Typography>
            </React.Fragment>
          ) : (
            <React.Fragment>{getStepContent(activeStep)}</React.Fragment>
          )}
        </Paper>
      </Container>
    </Dialog>
  );
}
