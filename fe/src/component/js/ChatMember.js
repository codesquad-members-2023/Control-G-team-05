import React from "react";
import styles from "../css/ChatMember.module.css";

function ChatMember({
  avatarSrc,
  time,
  nickname,
  message,
  notificationCount,
  name,
}) {
  const calculateTimeDifferenceInMinutes = (givenTimeString) => {
    const currentTime = new Date(); // 현재 시간
    const givenTime = new Date(givenTimeString); // 주어진 시간

    const timeDifference = currentTime - givenTime; // 차이를 밀리초로 계산
    const timeDifferenceInMinutes = Math.floor(timeDifference / 60000); // 밀리초를 분으로 변환

    const hours = Math.floor(timeDifferenceInMinutes / 60);
    const minutes = timeDifferenceInMinutes % 60;

    // 시간과 분으로 변환된 문자열을 반환
    if (hours > 0 || minutes > 0) {
      return hours ? `${hours}h ${minutes}m` : `${minutes}m`;
    }
    return "0m";
  };

  const timeDifferenceInMinutes = calculateTimeDifferenceInMinutes(time);

  return (
    <div className={styles.chatStack}>
      {/* 나중에 아이콘에 접속중인지 아닌지 확인할수 있게 해야함 */}
      <img className={styles.avatarIcon} alt="" src={avatarSrc} />
      <div className={styles.dataContainer}>
        <div className={styles.topContainer}>
          <div className={styles.nameContainer}>
            <div className={styles.nickname}>{nickname}</div>
            {name && <div className={styles.name}>{name}</div>}
          </div>
          <div className={styles.mAgo}>{timeDifferenceInMinutes} ago</div>
        </div>
        <div className={styles.chatMessage}>
          <div className={styles.message}>{message}</div>
          {notificationCount !== 0 && (
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
