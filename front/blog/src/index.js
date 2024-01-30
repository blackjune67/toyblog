import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import {ProfileCard} from "./chapter13/ProfileCard";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
      {/*<DevSupport
          ComponentPreviews={<Calculator />}
          // useInitialHook={useInitial}
      >*/}
      {/*<CommentList />*/}
      {/*<NotificationList />*/}
      {/*  <Calculator />*/}
      {/*<Accommodate />*/}
      {/*<ConfirmButton />*/}
      {/*<ConfirmButtonFn />*/}
      {/*<AttendanceBook />
      <LandingPage />*/}
      {/*<NameForm />*/}
      {/*<Reservation />*/}
      {/*<Signup />*/}
      {/*<Calculator />*/}
      {/*</DevSupport>*/}
        <ProfileCard/>
    </React.StrictMode>
);
/*setInterval(() => {
  root.render(
      <React.StrictMode>
        {/!*<App />*!/}
        {/!*<Library/>
        <Clock/>*!/}
        {/!*<Comment />*!/}
        <CommentList />
      </React.StrictMode>
  );
}, 1000);*/

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
