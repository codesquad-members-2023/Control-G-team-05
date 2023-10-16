import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import SignInPage from "./routes/SignInPage";
import SignUpPage from "./routes/SignUpPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<SignInPage />}></Route>
        <Route path="/sign-up" element={<SignUpPage />}></Route>
      </Routes>
    </Router>
  );
}

export default App;
