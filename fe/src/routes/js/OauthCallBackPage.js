import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { fetchOauthData } from "../../api/auth/Oauth";

function OauthCallBackPage() {
  const navigate = useNavigate();
  const urlParams = new URLSearchParams(window.location.search);
  const accessCode = urlParams.get("code");

  useEffect(() => {
    (async () => {
      const data = await fetchOauthData(accessCode);
      if (data.accessToken) {
        localStorage.setItem("accessToken", data.accessToken);
        localStorage.setItem("refreshToken", data.refreshToken);
        navigate("/main");
      } else if (data.message && data.message.signupToken) {
        localStorage.setItem("signupToken", data.message.signupToken);
        navigate("/sign-up");
      }
    })();
  });
}

export default OauthCallBackPage;
