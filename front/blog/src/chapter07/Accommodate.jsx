import {useEffect, useState} from "react";
import {UseCounter as useCounter} from "./UseCounter";

const MAX_CAPACITY = 10;

export function Accommodate(props) {
  // * state value, state FN
  const [isFull, setIsFull] = useState(false);
  const [count, increaseCount, decreaseCount] = useCounter(0);

  // * 의존성 배열이 없는 훅이 호출된 이유는 컴포넌트가 업데이트됐기때문.
  useEffect(() => {
    console.log('=============');
    console.log('useEffect() is called');
    console.log(`isFull : ${isFull}`);
  });

  // * 값이 업데이트 됨.
  useEffect(() => {
    setIsFull(count >= MAX_CAPACITY);
    console.log(`Current count value : ${count}`);
  }, [count]);


  return (
      <div style={{padding: 16}}>
        <p>{`총 ${count}명 수용했습니다`}</p>
        <button onClick={increaseCount} disabled={isFull}>입장</button>
        <button onClick={decreaseCount}>퇴장</button>
        {isFull && <p style={{color: "red"}}>정원이 가득찼습니다.</p>}
      </div>
  )
}