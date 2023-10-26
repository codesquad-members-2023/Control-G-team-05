import React from "react";
import styles from "../css/SignUpPage.module.css";
import ImageWithText from "../../component/js/ImageWithText";
import CustomTextField from "../../component/js/CustomTextField";
import NavigationButton from "../../component/js/NavigationButton";
import { fetchSignUpData } from "../../api/auth/Signup";
import { ParseJwt } from "../../utils/ParseJwt";
import { useState } from "react";
import { CONSTANT } from "../../constants/Constant";

function SignUpPage() {
  // 이미지 상태관리를 하는 useState
  const [selectedImage, setSelectedImage] = useState({
    file: null,
    filename: "",
  });

  const handleImageChange = (imageAndFileName) => {
    setSelectedImage(imageAndFileName);
  };

  // 닉네임의 변경사항을 체크하기위한 useState 또한 회원가입 request에 nickName 을 포함해야하기때문에 useState를 사용했다.
  const [nickname, setNickname] = useState("");

  const handleNicknameChange = (e) => {
    setNickname(e.target.value);
  };

  const handleSignUpButtonClick = (nickname) => {
    fetchSignUpData(nickname, selectedImage);
  };

  const signUpToken = localStorage.getItem("signupToken");
  const decodedPayload = ParseJwt(signUpToken);
  const { name, birthday } = decodedPayload;

  return (
    <div className={styles.div}>
      <ImageWithText
        imgSrc={CONSTANT.IMG_UPLOAD_ICON}
        onImageChange={handleImageChange}
      />
      <div className={styles.inner} data-animate-on-scroll>
        <div className={styles.registerBtnParent}>
          <CustomTextField placeHolder="Name" text={name} />
          <CustomTextField
            placeHolder="Nickname"
            onChange={handleNicknameChange}
            text={nickname}
          />
          <CustomTextField placeHolder="Birth" text={birthday} />
        </div>
      </div>
      <NavigationButton
        clickEvent={() => handleSignUpButtonClick(nickname)}
        to="/main"
        label="Sign Up"
        disabled={!(nickname.trim() !== "" && selectedImage.file)}
      />
    </div>
  );
}

export default SignUpPage;
