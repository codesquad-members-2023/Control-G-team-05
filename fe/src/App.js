import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import SignInPage from "./routes/SignInPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<SignInPage />}></Route>
      </Routes>
    </Router>
  );
}

export default App;
