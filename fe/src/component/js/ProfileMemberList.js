import React from "react";
import styles from "../css/ProfileMemberList.module.css";
import PopupMessage from "./Modal";
import { useState } from "react";
import { CONSTANT } from "../../constants/Constant";

function ProfileMemberList({
  memberId,
  avatarSrc,
  name,
  nickname,
  introduction,
  toggleButton,
  selected,
  modalButtonClicked,
  setModalButtonClicked,
}) {
  const [modalState, setModalState] = useState(false);

  return (
    <div className={styles.chatStack}>
      <img
        className={styles.avatarIcon}
        alt=""
        src={avatarSrc === null ? CONSTANT.BASIC_CHAT_PROFILE_ICON : avatarSrc}
      />
      <div className={styles.dataContainer}>
        <div className={styles.topContainer}>
          <div className={styles.nameContainer}>
            <div className={styles.nickname}>{nickname}</div>
            {name && <div className={styles.name}>{name}</div>}
          </div>
          <img
            alt=""
            src={toggleButton}
            className={styles.toggleButton}
            onClick={() => setModalState(!modalState)}
          ></img>
          <PopupMessage
            memberId={memberId}
            selected={selected}
            modalState={modalState}
            setModalState={setModalState}
            modalButtonClicked={modalButtonClicked}
            setModalButtonClicked={setModalButtonClicked}
          />
        </div>
        <div className={styles.chatMessage}>
          <div className={styles.message}>{introduction}</div>
        </div>
      </div>
    </div>
  );
}

export default ProfileMemberList;
