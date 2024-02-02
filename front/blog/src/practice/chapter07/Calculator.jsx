import {useEffect, useState} from "react";


export function Calculator() {
  const [count, setCount] = useState(0);
  console.log('==> count : ', count);
  useEffect(() => {
    document.title = `${count} 클릭수`;
  });
  return (
      <div>
        <div>
          <h1>총 클릭 수 : {count}</h1>
          <button onClick={() => setCount(count +1)}>Click</button>
        </div>
      </div>
  );
}