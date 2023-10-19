import React from "react";
import styles from "../css/GroupIconWithTextAndButton.module.css"; // styles를 여기서 import하세요. 경로는 실제 파일 경로에 따라 변경될 수 있습니다.

function GroupItem({ groupImgSrc, groupName, vectorIconSrc }) {
  return (
    <div className={styles.groupContainer}>
      <div className={styles.avatar}>
        <img className={styles.groupImg} alt="" src={groupImgSrc} />
      </div>
      <div className={styles.groupDetailContainer}>
        <div className={styles.groupName}>{groupName}</div>
        <img className={styles.vectorIcon} alt="" src={vectorIconSrc} />
      </div>
    </div>
  );
}

export default GroupItem;
