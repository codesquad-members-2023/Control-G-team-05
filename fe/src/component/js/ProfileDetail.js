import { CONSTANT } from "../../constants/Constant";
import styles from "../css/ProfileDetail.module.css";

function ProfileDetail({
  name,
  nickname,
  age,
  gender,
  statusMessage,
  profileImage,
  likedCount,
}) {
  return (
    <div className={styles.profileTopContainer}>
      <img className={styles.profileImg} alt="" src={profileImage} />
      <div className={styles.profileDetailContainer}>
        <div className={styles.name}>{name}</div>
        <div className={styles.nickName}>{nickname}</div>
        <div className={styles.likeAndAgeContainer}>
          <div className={styles.age}>
            {gender === "M" ? "♂" : "♀"}
            {age}
          </div>
          <div className={styles.likeContainer}>
            <img className={styles.likeIcon} alt="" src={CONSTANT.HEART_CON} />
            <div className={styles.liked}>{likedCount}</div>
          </div>
        </div>
        <div className={styles.statusMessage}>{statusMessage}</div>
      </div>
    </div>
  );
}

export default ProfileDetail;
