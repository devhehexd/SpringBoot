import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function BoardList() {

  const token = localStorage.getItem('token');

  const [list, setList] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8081/board', { headers: { token: token } })
      .then(function (res) {
        if (res.status === 200) {
          setList(res.data.list)
        }
        else {
          alert('비정상 응답')
        }
      })
  }, []);

  return (
    <div>
      <h2>글 목록</h2>
      <Link to="/board/writepost">글 작성</Link>
      <table border='1'>
        <thead>
          <tr>
            <th>번호</th><th>제목</th><th>이미지</th><th>작성자</th>
          </tr>
        </thead>
        <tbody>
          {list.map((item, idx) => (
            <tr key={idx}>
              <td>{item.num}</td>
              <td><Link to={"/board/postdetail/" + item.num}>{item.title}</Link></td>
              <td><img className="simg" src={'http://localhost:8081/read-img/' + item.img} /></td>
              <td>{item.writer.id}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}