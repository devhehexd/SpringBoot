import About from "./components/About"
import Hello from "./components/Hello"
import Srccomp from "./components/Srccomp"
import DestComp from "./components/DestComp"
import NotFound from "./components/NotFound"
import CreateAccount from "./components/CreateAccount"
import Login from "./components/Login"
import Info from "./components/Info"
import { Routes, Route } from "react-router-dom"

export default function Router() {
  return (
    <Routes>
      <Route path="/userhome" element={<Hello />} />
      <Route path="/about/:username" element={<About />} />
      <Route path="/src" element={<Srccomp />} />
      <Route path="/destination" element={<DestComp />} />
      <Route path="/member/createaccount" element={<CreateAccount />} />
      <Route path="/member/login" element={<Login />} />
      <Route path="/member/info" element={<Info />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
  )
}