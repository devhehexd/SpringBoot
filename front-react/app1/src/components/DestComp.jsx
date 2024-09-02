import { useLocation } from "react-router-dom"

export default function DestComp() {

  const location = useLocation();
  const id = location.state.id;
  const job = location.state.job;

  return (
    <div>
      <h3>이동한 컴포넌트</h3>
      id: {id}<br />
      job: {job}
    </div>
  )
}