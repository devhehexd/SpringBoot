import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function WritePost() {

  const navigate = useNavigate();

  const loginId = localStorage.getItem('loginId');

  const writePost = () => {

    let formData = new FormData(document.getElementById('postform'));

    axios.post('http://localhost:8081/boards', formData, { headers: { "Content-type": "application/x-www-form-urlencoded" } })
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
        내용: <input type="text" name="content" /><br />
        <input type="hidden" name="writer" value={loginId} /><br />
      </form>
      <button onClick={writePost}>작성</button>
    </div>
    // <div>
    //   <table border="1">
    //     {list.map((item) => {
    //       <tr>
    //         <th>작성자</th>
    //         <td><input type="text" name="writer" value={item.writer.id} readonly /></td>
    //       </tr>
    //     })}
    //     <tr>
    //       <th>제목</th>
    //       <td><input type="text" name="title" /></td>
    //     </tr>
    //     <tr>
    //       <th>내용</th>
    //       <td><textarea rows="5" cols="30" name="content"></textarea> </td>
    //     </tr>
    //     <tr>
    //       <th>작성</th>
    //       <td><input type="submit" value="작성" /></td>
    //     </tr>
    //   </table>
    // </div>
  )
}

