import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import SignInPage from "./routes/js/SignInPage";
import SignUpPage from "./routes/js/SignUpPage";
import MainPage from "./routes/js/MainPage";
import ChatListPage from "./routes/js/ChatListPage";
import ChatDetailPage from "./routes/js/ChatDetailPage";
import ProfilePage from "./routes/js/ProfilePage";
import SearchPage from "./routes/js/SearchPage";
import AddGroupPage from "./routes/js/AddGroupPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<SignInPage />}></Route>
        <Route path="/sign-up" element={<SignUpPage />}></Route>
        <Route path="/main" element={<MainPage />}></Route>
        <Route path="/chats" element={<ChatListPage />}></Route>
        <Route path="/chats/detail" element={<ChatDetailPage />}></Route>
        <Route path="/members" element={<ProfilePage />}></Route>
        <Route path="/groups" element={<SearchPage />}></Route>
        <Route path="/groups/create" element={<AddGroupPage />}></Route>
      </Routes>
    </Router>
  );
}

export default App;
