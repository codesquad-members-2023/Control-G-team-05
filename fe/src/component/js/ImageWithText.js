import React from "react";
import styles from "../css/ImageWithText.module.css";
import TextFadeInAnimation from "../../animation/js/TextFadeInAnimation";

// 최초 페이지에서 로그인을 하는 페이지에서 사용하는 그룹 및 텍스트 아이콘

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
