import UserHome from "./components/member/UserHome"
import NotFound from "./components/NotFound"
import CreateAccount from "./components/member/CreateAccount"
import Login from "./components/member/Login"
import Info from "./components/member/Info"
import BoardList from "./components/board/BoardList"
import WritePost from "./components/board/WritePost"
import PostDetail from "./components/board/PostDetail"
import { Routes, Route } from "react-router-dom"

export default function Router() {
  return (
    <Routes>
      <Route path="/userhome" element={<UserHome />} />
      <Route path="/member/createaccount" element={<CreateAccount />} />
      <Route path="/member/login" element={<Login />} />
      <Route path="/member/info" element={<Info />} />
      <Route path="/board/boardlist" element={<BoardList />} />
      <Route path="/board/writepost" element={<WritePost />} />
      <Route path="/board/postdetail/:postnum" element={<PostDetail />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
  )
}