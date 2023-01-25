import React,{useState,useEffect} from 'react'
export default function SignUpPassword(props) {
  const [password,setPassword] = useState("")
  const [checkPassword,setcheckPassword]=useState("")
  const [sameValue,setSameValue]=useState(false)
  useEffect(()=>{
    if(checkPassword!=="" && checkPassword===password){
      setSameValue(true)
      props.passwordHandler(password)
    }
    console.log(sameValue)
  },[checkPassword])
  
  const changePasswordHandler= (e)=>{
    setPassword(e.target.value)
    
  }
  const changeCheckPasswordHandler= (e)=>{
    setcheckPassword(e.target.value)    
  }

  

  return (
    <div>
      <div>비밀번호 입력</div>
      <input type="text" onChange={changePasswordHandler} />
      <div>비밀번호 확인</div>
      <input type="text" onChange={changeCheckPasswordHandler} />
      {sameValue?"비밀번호가 일치합니다":"비밀번호가 다릅니다"}
    </div>
  )
}
