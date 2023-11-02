import React from "react";
import styles from "../css/ImageWithText.module.css";
import TextFadeInAnimation from "../../animation/js/TextFadeInAnimation";
import { useRef, useState } from "react";

// 로그인을 하는 페이지에서 사용하는 그룹 및 텍스트 아이콘

function ImageWithText({ imgSrc, text, onImageChange }) {
  TextFadeInAnimation();
  const [currentImage, setCurrentImage] = useState(imgSrc);
  const fileInputRef = useRef(null);

  const handleImageClick = () => {
    fileInputRef.current.click();
  };

  const handleFileChange = (event) => {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();

      reader.onloadend = () => {
        // FileReader에서 읽은 이미지 URL을 currentImage 상태에 설정
        setCurrentImage(reader.result);
        onImageChange({
          file: reader.result, // 이미지 URL
          filename: file.name, // 파일 이름
        });
      };

      reader.readAsDataURL(file);
    }
  };

  return (
    <div className={styles.inner}>
      <div className={styles.rectangleParent} onClick={handleImageClick}>
        <img className={styles.frameChild} alt="" src={currentImage} />
        <div className={styles.name} data-animate-on-scroll>
          {text}
        </div>
        <input
          type="file"
          style={{ display: "none" }}
          ref={fileInputRef}
          onChange={handleFileChange}
        />
      </div>
    </div>
  );
}

export default ImageWithText;
