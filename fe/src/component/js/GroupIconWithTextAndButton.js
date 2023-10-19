import React from "react";
import styles from "../css/GroupIconWithTextAndButton.module.css";

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
