import React, { useState } from 'react';
import axios from 'axios';

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
  // 가입단계 상태
  const [activeStep, setActiveStep] = useState(0);
  //이메일
  const [email, setEmail] = useState('');
  //인증 코드
  const [code, setCode] = useState('');
  //닉네임
  const [name, setName] = useState('');
  //비밀번호
  const [password, setPassword] = useState('');

  //스프링부트 url
  const baseUrl = 'http://localhost:8080';

  //자식 컴포넌트에서 setState 값을 받기 위해 함수를 props로 전달
  //이메일
  const emailHandler = emailChange => {
    setEmail(emailChange);
  };
  //인증코드
  const codeHandler = codeChange => {
    setCode(codeChange);
  };
  //닉네임
  const nameHandler = nameChange => {
    setName(nameChange);
  };
  //비밀번호
  const passwordHandler = passwordChange => {
    setPassword(passwordChange);
  };

  const handleClose = () => {
    props.setSignUpDialog(false);
  };

  /** 맨 마지막 회원가입 버튼을 눌렀을 때 가입정보를 백앤드로 보냄*/
  const userSignUp = () => {
    if (password === '') {
      console.log('비밀번호를 입력해주세요');
    } else {
      console.log('회원가입전송');
      axios
        .post(`${baseUrl}/users/signup`, {
          email: email,
          nickname: name,
          password: password,
        })
        .then(e => {
          console.log(e);
          setActiveStep(4);
        })
        .catch(e => {
          console.log(e);
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
            codeHandler={codeHandler}
            emailHandler={emailHandler}
            email={email}
          />
        );
      case 1:
        return (
          <SignUpCertificationForm setActiveStep={setActiveStep} code={code} />
        );
      case 2:
        return (
          <SignUpNicknameForm
            setActiveStep={setActiveStep}
            nameHandler={nameHandler}
            name={name}
          />
        );
      case 3:
        return (
          <SignUpPasswordForm
            setActiveStep={setActiveStep}
            passwordHandler={passwordHandler}
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
