import React from "react";
import styles from "../css/ProfileMemberList.module.css";

function ProfileMemberList({
  avatarSrc,
  name,
  nickname,
  introduction,
  toggleButton,
}) {
  return (
    <div className={styles.chatStack}>
      <img className={styles.avatarIcon} alt="" src={avatarSrc} />
      <div className={styles.dataContainer}>
        <div className={styles.topContainer}>
          <div className={styles.nameContainer}>
            <div className={styles.nickname}>{nickname}</div>
            {name && <div className={styles.name}>({name})</div>}
          </div>
          <img alt="" src={toggleButton} className={styles.toggleButton}></img>
        </div>
        <div className={styles.chatMessage}>
          <div className={styles.message}>{introduction}</div>
        </div>
      </div>
    </div>
  );
}

export default ProfileMemberList;
