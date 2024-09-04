import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function CreateAccount() {

  const navigate = useNavigate();

  const [inputs, setInputs] = useState({ id: '', password: '', name: '', email: '', type: '' });

  const { id, password, name, email, type } = inputs

  const onChange = (evt) => { //evt: 방금 발생한 이벤트의 객체
    const { name, value } = evt.target;
    setInputs({
      ...inputs, //다른 요소는 값을 그대로 유지
      [name]: value //이벤트가 발생한 요소만 value 변경
    })
  }

  const createAccount = () => {
    axios.post('http://localhost:8081/members', {}, { params: { id: id, password: password, name: name, email: email, type: type } })
      .then(function (res) {
        if (res.status === 200) {
          let print = 'id: ' + res.data.memDto.id;
          print += '\nPassword: ' + res.data.memDto.password;
          print += '\nName: ' + res.data.memDto.name;
          print += '\nEmail: ' + res.data.memDto.email;
          print += '\nType: ' + res.data.memDto.type;

          alert('회원가입 완료. \n회원정보: \n' + print);
        }
        else {
          alert('가입 취소');
        }
        navigate('/userhome');
      })
  }

  return (
    <div>
      <h2>Create Account</h2>
      ID: <input type="text" name="id" value={id} onChange={onChange} /><br />
      Password: <input type="password" name="password" value={password} onChange={onChange} /><br />
      Name: <input type="text" name="name" value={name} onChange={onChange} /><br />
      Email: <input type="email" name="email" value={email} onChange={onChange} /><br />
      Type: <input type="text" name="type" value={type} onChange={onChange} /><br />
      <button onClick={createAccount}>가입</button>
    </div>
  )
}