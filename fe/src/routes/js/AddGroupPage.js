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
        imgSrc="data:image/png;base64,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"
        onImageChange={handleImageChange}
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
