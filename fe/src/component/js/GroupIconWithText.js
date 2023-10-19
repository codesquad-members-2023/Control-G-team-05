import React from "react";
import styles from "../css/GroupIconWithText.module.css";

//chats 에서 상단에 그룹 아이콘들을 보여주는데 거기서 사용하는 component

function GroupIconWithText({ imgSrc, groupName }) {
  return (
    <div className={styles.group}>
      <div className={styles.avatar}>
        <img className={styles.groupIcon} alt="" src={imgSrc} />
      </div>
      <div className={styles.groupName}>{groupName}</div>
    </div>
  );
}

export default GroupIconWithText;
