import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function Login() {

  const navigate = useNavigate();

  const [inputs, setInputs] = useState({ id: '', password: '' });

  const { id, password } = inputs

  const onChange = (evt) => { //evt: 방금 발생한 이벤트의 객체
    const { name, value } = evt.target;
    setInputs({
      ...inputs, //다른 요소는 값을 그대로 유지
      [name]: value //이벤트가 발생한 요소만 value 변경
    })
  }

  const login = () => {
    axios.post('http://localhost:8081/login', {}, { params: { id: id, password: password } })
      .then(function (res) {
        if (res.status === 200) {
          if (res.data.flag) {
            alert('로그인 성공')
            localStorage.setItem('token', res.data.token);
            navigate('/hello')
          }
          else {
            alert('로그인 실패')
          }
        }
        else {
          alert('비정상 응답')
        }
      })
  }

  return (
    <div>
      <h2>Login</h2>
      ID: <input type="text" name="id" value={id} onChange={onChange} /><br />
      Password: <input type="password" name="password" value={password} onChange={onChange} /><br />
      <button onClick={login}>로그인</button>
    </div>
  )
}