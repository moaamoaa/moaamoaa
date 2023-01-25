import React,{useState,useEffect} from 'react'
import axios from 'axios'


// 이메일 정보를 받는 자식 컴포넌트
function SignUpEmail(props) {
  // input에 작성하는 이메일 정보
  const baseUrl = "http://localhost:8080";
  const [userEmail, setUserEmail] = useState("");
  const [changeEmail,setChangeEmail] = useState("");

  const changeEmailHandler= (e)=>{
    e.preventDefault()
    console.log(e.target.value)
    setChangeEmail(e.target.value)
  }

  useEffect(async()=>{
    try {const response = await axios.get(`${baseUrl}/users/email/${userEmail}`);
      console.log(response.data)
    }
    catch (error) {
      console.log(error)
    }
  }, [changeEmail])
     

  return (
    <div>
      <form onSubmit={changeEmailHandler}>
        <input type="text" required={true} placeholder="이메일입력" value={userEmail} onChange={setUserEmail(userEmail)}/>
        <button type="submit">이메일 제출</button>
      </form>      
    </div>
  )
}
export default SignUpEmail
