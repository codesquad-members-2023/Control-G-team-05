import React from "react";
import styles from "../css/SignInPage.module.css";
import ImageWithText from "../../component/js/ImageWithText";
import { CONSTANT } from "../../constants/Constant";
import { API_ENDPOINTS } from "../../constants/Endpoints";

function SignInPage() {
  return (
    <div className={styles.div}>
      <ImageWithText imgSrc={CONSTANT.CONTROL_G_ICON} text="Control G" />
      <div className={styles.signInButtonWrapper}>
        <a href={API_ENDPOINTS.OAUTH_API} className={styles.signInButton}>
          <b className={styles.signInWith}>Sign In With Naver</b>
        </a>
      </div>
    </div>
  );
}

export default SignInPage;
