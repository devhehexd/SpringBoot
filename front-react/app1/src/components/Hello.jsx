import { useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function Hello() {

  const navigate = useNavigate();
  let loginId = localStorage.getItem('loginId');
  let menu = '';

  const logout = () => {
    localStorage.removeItem('loginId')
    localStorage.removeItem('type')
    navigate('/userhome')
  }

  if (loginId === null) {
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
        <Link to="/member/info"> 내 정보확인</Link>
      </div>;
  }

  return (
    <div>
      <h2>User Home</h2>
      {menu}
    </div>
  )
}