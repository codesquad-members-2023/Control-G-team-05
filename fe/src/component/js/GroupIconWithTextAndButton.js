import React from "react";
import styles from "../css/GroupIconWithTextAndButton.module.css";
import { Link } from "react-router-dom";

function GroupItem({ groupImgSrc, groupName, vectorIconSrc }) {
  return (
    <div className={styles.groupContainer}>
      <div className={styles.avatar}>
        <img className={styles.groupImg} alt="" src={groupImgSrc} />
      </div>
      <div className={styles.groupDetailContainer}>
        <div className={styles.groupName}>{groupName}</div>
        {vectorIconSrc && (
          <Link to="/chats/find">
            <img className={styles.vectorIcon} alt="" src={vectorIconSrc} />
          </Link>
        )}
      </div>
    </div>
  );
}

export default GroupItem;
