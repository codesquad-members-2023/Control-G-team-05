import React from "react";
import styles from "../css/ChatMessageBox.module.css";

function ChatMessageBox({ isOwnMessage, message, time, isRead }) {
  return (
    <div
      className={
        isOwnMessage
          ? styles.ownMessageContainer
          : styles.opponentMessageContainer
      }
    >
      <div
        className={isOwnMessage ? styles.ownMessage : styles.opponentMessage}
      >
        <div className={styles.chatMessage}>{message}</div>
        <div className={styles.chatInformation}>
          <div className={styles.time}>{time}</div>
          <div className={styles.isRead}>{isRead}</div>
        </div>
      </div>
    </div>
  );
}

export default ChatMessageBox;
