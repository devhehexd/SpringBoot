import axios from "axios";
import { useEffect, useState } from "react";

export default function Info() {

  const loginId = localStorage.getItem('loginId');
  const [memDto, setMemDto] = useState({});
  const { id, password, name, email, type } = memDto;

  //컴포넌트가 렌더링 할 때마다 실행되는 함수
  //맨 처음 한번만 실행하려면, 두번째 parameter에 빈 배열을 넣어준다(window.onload와 같은 역할. 2, 3.. 번 실행되게 하려면 배열에 해당하는 숫자를 넣어준다.)
  useEffect(() => {
    axios.get('http://localhost:8081/members/' + loginId)
      .then(function (res) {
        if (res.status === 200) {
          setMemDto(res.data.memDto)
        }
        else {
          alert('비정상 응답')
        }
      })
  }, []);

  return (
    <div>
      <h2>My Info</h2>
      ID:{id}<br />
      Password:{password}<br />
      Name:{name}<br />
      Email:{email}<br />
      Type:{type}<br />
    </div>
  )
}