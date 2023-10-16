import React from "react";
import styles from "./SignInPage.module.css"; // CSS 파일에서 스타일을 가져옵니다.

function MainPage() {
  return (
    <div className={styles.div}>
      {/* main 페이지 위쪽 페이지 */}
      <div className={styles.child} />
      <div className={styles.inner}>
        <div className={styles.rectangleParent}>
          <img
            className={styles.frameChild}
            alt=""
            src="https://www.notion.so/image/https%3A%2F%2Fcdn-icons-png.flaticon.com%2F512%2F10074%2F10074958.png?table=block&id=77c107d2-ff9c-49d3-bc7d-da98a81bc80c&spaceId=9987bb01-df95-42d3-8249-04050ff2ede3&width=250&userId=59ce483d-1566-4ee4-a097-ca0972c7e50a&cache=v2"
          />
          <div className={styles.controlG}>Control G</div>
        </div>
      </div>
      <div className={styles.registerBtnWrapper}>
        <div className={styles.registerBtn}>
          <b className={styles.signInWith}>Sign In With Kakao</b>
        </div>
      </div>
    </div>
  );
}

export default MainPage;
