import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import {ConfirmButtonFn} from "./chapter08/ConfirmButtonFn";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
      {/*<CommentList />*/}
      {/*<NotificationList />*/}
      {/*  <Calculator />*/}
      {/*<Accommodate />*/}
      {/*<ConfirmButton />*/}
      <ConfirmButtonFn />
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
