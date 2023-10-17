import React from "react";
import styles from "../css/SignInPage.module.css"; // CSS 파일에서 스타일을 가져옵니다.
import { Link } from "react-router-dom";
import ImageWithText from "../../component/js/ImageWithText";

function SignInPage() {
  return (
    <div className={styles.div}>
      <ImageWithText
        imgSrc="https://www.notion.so/image/https%3A%2F%2Fcdn-icons-png.flaticon.com%2F512%2F10074%2F10074958.png?table=block&id=77c107d2-ff9c-49d3-bc7d-da98a81bc80c&spaceId=9987bb01-df95-42d3-8249-04050ff2ede3&width=250&userId=59ce483d-1566-4ee4-a097-ca0972c7e50a&cache=v2"
        text="Control G"
      />
      <div className={styles.signInButtonWrapper}>
        {/* 임시로 sign-in 누를시 sign-up 으로 이동하도록 설정 */}
        <Link className={styles.signInButton} to="/sign-up">
          <b className={styles.signInWith}>Sign In With Kakao</b>
        </Link>
      </div>
    </div>
  );
}

export default SignInPage;
