import React from "react";
import styles from "../css/ChatMember.module.css";

function ChatMember({
  avatarSrc,
  name,
  time,
  message,
  notificationCount,
  toggleButton,
}) {
  return (
    <div className={styles.chatStack}>
      {/* 나중에 아이콘에 접속중인지 아닌지 확인할수 있게 해야함 */}
      <img className={styles.avatarIcon} alt="" src={avatarSrc} />
      <div className={styles.dataContainer}>
        <div className={styles.topContainer}>
          <div className={styles.name}>{name}</div>
          <div className={styles.mAgo}>{time}</div>
          <img alt="" src={toggleButton} className={styles.toggleButton}></img>
        </div>
        <div className={styles.chatMessage}>
          <div className={styles.message}>{message}</div>
          {notificationCount && (
            <div className={styles.notification}>
              <b className={styles.notificationCount}>{notificationCount}</b>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default ChatMember;
