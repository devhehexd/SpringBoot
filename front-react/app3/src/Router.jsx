import WritePost from "./components/board/WritePost"
import BoardList from "./components/board/BoardList"
import Info from "./components/member/Info"
import Login from "./components/member/Login"
import UserHome from "./components/member/UserHome"
import { Routes, Route } from "react-router-dom"

export default function Router() {
  return (
    <Routes>
      <Route path="/userhome" element={<UserHome />} />
      <Route path="/member/login" element={<Login />} />
      <Route path="/member/info" element={<Info />} />
      <Route path="/board/boardlist" element={<BoardList />} />
      <Route path="/board/writepost" element={<WritePost />} />
    </Routes>
  )
}