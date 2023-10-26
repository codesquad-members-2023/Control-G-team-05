import React from "react";
import styles from "../css/AddGroupPage.module.css";
import ImageWithText from "../../component/js/ImageWithText";
import CustomTextField from "../../component/js/CustomTextField";
import NavigationButton from "../../component/js/NavigationButton";
import TopBarWithCenterText from "../../component/js/TopBarWithCenterText";
import { useState } from "react";
import { fetchGroupCreate } from "../../api/group/CreateGroup";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { CONSTANT } from "../../constants/Constant";

function AddGroupPage() {
  const navigate = useNavigate();

  const [selectedImage, setSelectedImage] = useState({
    file: null,
    filename: "",
  });

  const handleImageChange = (imageAndFileName) => {
    setSelectedImage(imageAndFileName);
  };

  // 닉네임의 변경사항을 체크하기위한 useState 또한 회원가입 request에 nickName 을 포함해야하기때문에 useState를 사용했다.
  const [groupName, setGroupName] = useState("");

  const handleGroupNameChange = (e) => {
    setGroupName(e.target.value);
  };

  const handleCreateGroupButtonClick = async (groupName) => {
    await fetchGroupCreate(groupName, selectedImage);
    navigate("/groups");
    toast("그룹 추가 성공");
  };

  return (
    <div className={styles.div}>
      <TopBarWithCenterText topBarName="Add Group" returnLink="/groups" />
      <ImageWithText
        imgSrc={CONSTANT.IMG_UPLOAD_ICON}
        WonImageChange={handleImageChange}
      />
      <div className={styles.inner} data-animate-on-scroll>
        <div className={styles.registerBtnParent}>
          <CustomTextField
            placeHolder="Group Name"
            onChange={handleGroupNameChange}
            text={groupName}
          />
        </div>
      </div>
      <NavigationButton
        clickEvent={() => handleCreateGroupButtonClick(groupName)}
        // to="/groups"
        label="Add Group"
        disabled={!(groupName.trim() !== "" && selectedImage.file)}
      />
    </div>
  );
}

export default AddGroupPage;
