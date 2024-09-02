import { useNavigate } from "react-router-dom";

export default function Srccomp() {

  const navigate = useNavigate();

  const goToComp = () => {

    navigate('/destination', { state: { id: 1, job: '개발자' } });
  }

  return (
    <div>
      <button onClick={goToComp}>컴포넌트 이동</button>
    </div>
  )
}