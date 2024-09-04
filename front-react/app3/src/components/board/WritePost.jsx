import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function WritePost() {

  const navigate = useNavigate();

  const token = localStorage.getItem('token');

  const writePost = () => {

    let formData = new FormData(document.getElementById('postform'));

    axios.post('http://localhost:8081/board', formData, { headers: { "Content-type": "multipart/form-data", token: token } })
      .then(function (res) {
        if (res.status === 200) {
          if (res.data.flag) {
            alert('글 작성 완료')
          }
          else {
            alert('글 작성 실패')
          }
        }
        else {
          alert('글 작성 취소')
        }
        navigate('/board/boardlist')
      });
  }

  return (
    <div>
      <h2>글 작성</h2>
      <form id="postform">
        제목: <input type="text" name="title" /><br />
        파일: <input type="file" name="multipartFile" /><br />
      </form>
      <button type="button" onClick={writePost}>작성</button>
    </div>
  )
}

