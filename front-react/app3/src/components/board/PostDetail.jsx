import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

export default function PostDetail() {

  const navigate = useNavigate();
  const loginId = localStorage.getItem('loginId');

  const { postnum } = useParams(); //글 번호
  const [boardDto, setBoardDto] = useState({ num: 0, writer: {}, wdata: '', title: '', content: '' });
  const { num, writer, wdate, title, content } = boardDto;


  const del = () => {
    axios.delete('http://localhost:8081/boards/' + postnum)
      .then(function (res) {
        if (res.status === 200) {
          if (res.data.flag) {
            alert('삭제 완료')
            navigate('/board/boardlist')
          }
          else {
            alert('삭제 취소')
          }
        }
        else {
          alert('비정상 응답')
        }
      })
  }

  let menu = '';

  useEffect(() => {
    axios.get('http://localhost:8081/boards/' + postnum)
      .then(function (res) {
        if (res.status === 200) {
          setBoardDto(res.data.boardDto)
        }
        else {
          alert('비정상 응답')
        }
      });
  }, [])

  if (loginId == writer.id) {
    menu = <button onClick={del}>삭제</button>
  }

  return (
    <div>
      <h2>글 상세</h2>
      번호: {num}<br />
      작성자: {writer.id}<br />
      작성일: {wdate}<br />
      제목: {title}<br />
      내용: {content}<br />
      {menu}
    </div>
  )
}