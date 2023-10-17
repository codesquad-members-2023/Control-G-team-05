import React from "react";
import styles from "../css/ImageWithText.module.css"; // 해당 컴포넌트의 스타일을 위한 파일 경로를 확인하십시오.
import TextFadeInAnimation from "../../animation/js/TextFadeInAnimation";

function ImageWithText({ imgSrc, text }) {
  TextFadeInAnimation();
  return (
    <div className={styles.inner}>
      <div className={styles.rectangleParent}>
        <img className={styles.frameChild} alt="" src={imgSrc} />
        <div className={styles.name} data-animate-on-scroll>
          {text}
        </div>
      </div>
    </div>
  );
}

export default ImageWithText;
