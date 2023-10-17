import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import SignInPage from "./routes/js/SignInPage";
import SignUpPage from "./routes/js/SignUpPage";
import MainPage from "./routes/js/MainPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<SignInPage />}></Route>
        <Route path="/sign-up" element={<SignUpPage />}></Route>
        <Route path="/main-page" element={<MainPage />}></Route>
      </Routes>
    </Router>
  );
}

export default App;
