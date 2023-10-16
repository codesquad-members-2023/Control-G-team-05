import React from "react";
import styles from "./SignUpPage.module.css"; // CSS 파일에서 스타일을 가져옵니다.

function SignUpPage() {
  return (
    <div className={styles.div}>
      <div className={styles.inputFrame}>
        <div className={styles.registerBtnParent}>
          <div className={styles.registerBtn}>
            <div className={styles.nameWrapper}>
              <div className={styles.name}>Name</div>
            </div>
          </div>
          <div className={styles.registerBtn1}>
            <div className={styles.nameWrapper}>
              <div className={styles.name}>Nickname</div>
            </div>
          </div>
          <div className={styles.registerBtn2}>
            <div className={styles.nameWrapper}>
              <div className={styles.name}>Birth</div>
            </div>
          </div>
        </div>
        <div className={styles.registerBtn3}>
          <b className={styles.signUp}>Sign Up</b>
        </div>
      </div>
      <div className={styles.child} />
      <div className={styles.item} />
      <div className={styles.inner}>
        <div className={styles.rectangleWrapper}>
          <img
            className={styles.frameChild}
            alt=""
            src="data:image/png;base64,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"
          />
        </div>
      </div>
    </div>
  );
}

export default SignUpPage;
