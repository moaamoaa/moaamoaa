import React,{useState,useEffect} from 'react'
import axios from 'axios'

function SignUpName(props) {
  const [name,setName] = useState("")
  const [correctValue,setCorrectValue]=useState(false)
  
  const changeNameHandler= (e)=>{
    setName(e.target.value)
  }
  const submitName = () =>{
    const checkValue = async()=> {const result = await axios.get(`http://localhost:4000/findId/${name}`)
    if(result.data.config){
      setCorrectValue(true)
      props.nameHandler(name)
      props.stageHandler(3)
    }}
    checkValue()
  }

  

  return (
    <div>
      <input type="text" onChange={changeNameHandler} />
      <button onClick={submitName}>닉네임 제출</button>
      {correctValue?"":"중복되지 않는 이름을 작성해주세요"}
    </div>
  )
}

export default SignUpName
