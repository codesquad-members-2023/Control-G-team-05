import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import SignInPage from "./routes/js/SignInPage";
import SignUpPage from "./routes/js/SignUpPage";
import MainPage from "./routes/js/MainPage";
import ChatListPage from "./routes/js/ChatListPage";
import ChatDetailPage from "./routes/js/ChatDetailPage";
import ProfilePage from "./routes/js/ProfilePage";
import SearchPage from "./routes/js/SearchPage";
import AddGroupPage from "./routes/js/AddGroupPage";
import GroupDetailPage from "./routes/js/GroupDetailPage";
import ChatLoadingPage from "./routes/js/ChatLoadingPage";
import OauthCallBackPage from "./routes/js/OauthCallBackPage";
import { ToastContainer, Zoom } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import styles from "./App.module.css";

function App() {
  return (
    <div id="modalRoot" className={styles.div}>
      <Router>
        <Routes>
          <Route path="/" element={<SignInPage />}></Route>
          <Route path="/sign-up" element={<SignUpPage />}></Route>
          <Route path="/main" element={<MainPage />}></Route>
          <Route path="/chats" element={<ChatListPage />}></Route>
          <Route path="/chats/detail" element={<ChatDetailPage />}></Route>
          <Route path="/chats/find" element={<ChatLoadingPage />}></Route>
          <Route path="/members" element={<ProfilePage />}></Route>
          <Route path="/groups" element={<SearchPage />}></Route>
          <Route path="/groups/create" element={<AddGroupPage />}></Route>
          <Route path="/groups/:groupId" element={<GroupDetailPage />}></Route>
          <Route path="/callback" element={<OauthCallBackPage />}></Route>
        </Routes>
      </Router>
      <ToastContainer
        autoClose={1000} // 자동 off 시간
        hideProgressBar={false} // 진행시간바 숨김
        closeOnClick // 클릭으로 알람 닫기
        rtl={false} // 알림 좌우 반전
        pauseOnFocusLoss // 화면을 벗어나면 알람 정지
        draggable // 드래그 가능
        pauseOnHover // 마우스를 올리면 알람 정지
        theme="colored"
        transition={Zoom} // 알람 전환 효과
      />
    </div>
  );
}

export default App;
