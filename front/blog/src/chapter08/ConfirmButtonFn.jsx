import {useState} from "react";

export const ConfirmButtonFn = () => {
  const [isConfirmed, setConfirmed] = useState(false);

  const handleConfirm = () => {
    setConfirmed((preState) => !preState);
  };

  return (
      <div>
        <button onClick={handleConfirm} disabled={isConfirmed}>
          {isConfirmed ? "확인" : "확인하기"}
        </button>
      </div>
  );

}