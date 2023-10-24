import React from "react";
import styles from "../css/GroupProfileInformation.module.css";

function GroupProfileInformation({ groupImgSrc, groupName, memberCount }) {
  return (
    <div className={styles.nameAndMemeberCountContainerParent}>
      <img className={styles.groupIcon} alt="" src={groupImgSrc} />
      <div className={styles.nameAndMemeberCountContainer}>
        <div className={styles.groupName}>{groupName}</div>
        <div className={styles.memberCountContainer}>
          <span className={styles.text}>{`Public group `}</span>
          <b className={styles.memberCount}>{memberCount}K</b>
          <span className={styles.text}> members.</span>
        </div>
      </div>
      <div className={styles.joinButtonContainer}>
        <b className={styles.join}>+ Join</b>
      </div>
    </div>
  );
}

export default GroupProfileInformation;