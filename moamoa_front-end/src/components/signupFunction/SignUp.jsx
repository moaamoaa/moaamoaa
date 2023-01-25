import React,{useState,useEffect} from 'react'
import SignUpEmail from './SignUpEmail'
import SignUpName from './SignUpName'
import SignUpPassword from './SignUpPassword'
import axios from 'axios'

// 부모 회원가입 컴포넌트
function SignUp() {
  //단계 상태
  const [changeStage,setChangeStage]= useState(0)
  //이메일
  const [email,setEmail] = useState("")
  //인증 코드
  const [code,setCode] = useState("")
  //닉네임
  const [name,setName] = useState("")
  //비밀번호
  const [password,setPassword] = useState("")


  //자식 컴포넌트에서 setState 값을 받기 위해 함수를 props로 전달
  const emailHandler = (emailChange) =>{
    setEmail(emailChange)
  }
  const codeHandler = (codeChange) =>{
    setCode(codeChange)
  }
  /** 맨 마지막 회원가입 버튼을 눌렀을 때 가입정보를 백앤드로 보냄*/
  const nameHandler = (nameChange) =>{
    setName(nameChange)
  }
  const passwordHandler = (passwordChange) =>{
    setPassword(passwordChange)
  }
  const stageHandler = (stageChange) =>{
    setChangeStage(stageChange)
  }
  /** 맨 마지막 회원가입 버튼을 눌렀을 때 가입정보를 백앤드로 보냄*/
  const userSignUp = () =>{
    axios.post("http://localhost:8080/users",{"email":email,"name":name,"password":password})
  }

  return (
    <>
      {changeStage===0?<SignUpEmail codeHandler={codeHandler} emailHandler={emailHandler} stageHandler={stageHandler}/>:""}
      {changeStage===2?<SignUpName nameHandler={nameHandler} stageHandler={stageHandler}/>:""}
      {changeStage===3?<SignUpPassword passwordHandler={passwordHandler} stageHandler={stageHandler}/>:""}
      {/* 버튼 눌렀을 때 router.push로 메인 페이지로 가기 */}
      {changeStage===3?<button onClick={userSignUp}>회원가입하기</button>:""}
    </>
  )
}

export default SignUp
