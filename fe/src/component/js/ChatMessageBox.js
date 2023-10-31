import React from "react";
import styles from "../css/ChatMessageBox.module.css";

function ChatMessageBox({ isOwnMessage, message, time, isRead }) {
  const timestamp = time;
  const date = new Date(timestamp);
  const hours = date.getUTCHours(); // UTC 시간의 '시간' 부분
  const minutes = date.getUTCMinutes(); // UTC 시간의 '분' 부분

  // '시간'이 0으로 시작하는 경우에는 앞에 0을 제거.
  const parsedHours = hours.toString().padStart(2, "0").replace(/^0/, "");
  const parsedMinutes = minutes.toString().padStart(2, "0");

  const parsedTime = `${parsedHours}:${parsedMinutes}`;
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
        <div
          className={
            isOwnMessage
              ? styles.chatInformationForMine
              : styles.chatInformationForPartner
          }
        >
          <div className={styles.time}>{parsedTime}</div>
          {isRead && <div className={styles.isRead}>· Read</div>}
        </div>
      </div>
    </div>
  );
}

export default ChatMessageBox;
