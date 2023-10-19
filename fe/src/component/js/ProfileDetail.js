import styles from "../css/ProfileDetail.module.css";

function ProfileDetail({
  name,
  nickName,
  age,
  gender,
  statusMessage,
  profileImage,
  likedCount,
}) {
  return (
    <div className={styles.profileTopContainer}>
      <img className={styles.icon} alt="" src={profileImage} />
      <div className={styles.profileDetailContainer}>
        <div className={styles.name}>{name}</div>
        <div className={styles.nickName}>{nickName}</div>
        <div className={styles.likeAndAgeContainer}>
          <div className={styles.age}>
            {gender === "male" ? "♂" : "♀"}
            {age}
          </div>
          <div className={styles.likeContainer}>
            <img
              className={styles.likeIcon}
              alt=""
              src="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/a466ca8f-750b-4ed5-ba53-9460f8eb7465_1697439263403882824?Expires=-62135596800&Signature=IquFwWmu6JvIlfjU91ht3orqsLjtM2MdUxSwyA2fzTSAmsN0gJYiSZiT29xiDmSxy6QBAA0PafMkiagZ~oGqhLYvct2wBjFKelutYVGpuPWaSuriZlSWU3dgohvHMzsQED43zY5nlSTB6DH1cocEAq71HIQT128ZxtpRPGqiqAXiqkHkNq3gXJyCXZFfXyHkJC-huq0HlyP0Ur7le9y3IeJ1vS~ofGwHQgwnRlwDf2Gb2otHptT3~Lp4xxrazY1SZjiY3R0IAwKGDLy5vb1JfAuuxakI-cKdyY88u24vLucK9fUAX-BJ6zEy4SHdJpIKGZdMtKLdCZ-sbicblB0PtQ__&Key-Pair-Id=K1P54FZWCHCL6J"
            />
            <div className={styles.liked}>{likedCount}</div>
          </div>
        </div>
        <div className={styles.statusMessage}>{statusMessage}</div>
      </div>
    </div>
  );
}

export default ProfileDetail;
