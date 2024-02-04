import {BrowserRouter, Route, Routes} from "react-router-dom";
import {MainPage} from "./component/page/MainPage";
import {PostWritePage} from "./component/page/PostWritePage";
import {PostViewPage} from "./component/page/PostViewPage";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <p>커뮤니티</p>
        <Routes>
          <Route index element={<MainPage />} />
          <Route path="post-write" element={<PostWritePage />} />
          <Route path="post/:postId" element={<PostViewPage/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
