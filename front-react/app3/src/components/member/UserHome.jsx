import { Link, useNavigate } from "react-router-dom";

export default function Hello() {

  const navigate = useNavigate();
  let token = localStorage.getItem('token');
  let menu = '';

  const logout = () => {
    localStorage.removeItem('token')
    navigate('/userhome')
  }

  if (token === null) {
    menu =
      <div>
        <Link to="/member/createaccount">회원가입 </Link> |
        <Link to="/member/login"> 로그인</Link>
      </div>;
  }
  else {
    menu =
      <div>
        <button onClick={logout}>로그아웃 </button> |
        <Link to="/member/info"> 내 정보확인</Link> |
        <Link to="/board/boardlist"> 글 목록</Link>
      </div>;
  }

  return (
    <div>
      <h2>User Home</h2>
      {menu}
    </div>
  )
}