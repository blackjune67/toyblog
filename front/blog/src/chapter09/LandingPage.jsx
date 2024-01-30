import {Toolbar} from "./Toolbar";
import {useState} from "react";

export function LandingPage() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const onClickLogin = () => {
    setIsLoggedIn(true);
  }

  const onClickLogout = () => {
    setIsLoggedIn(false);
  }

  return (
      <div>
        <Toolbar
            isLoggedIn={isLoggedIn}
            onClickLogin={onClickLogin}
            onClickLogout={onClickLogout}
        />
        <div style={{padding: 16}}>리액트 공부하기</div>
      </div>
  );
}