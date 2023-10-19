import styles from "../css/ToggleButton.module.css";

function ToggleButton() {
  return (
    <div className={styles.buttonContainer}>
      <div className={styles.likeContainer}>
        <div className={styles.like}>like</div>
      </div>
      <div className={styles.matchedContainer}>
        <div className={styles.matched}>Matched</div>
      </div>
    </div>
  );
}

export default ToggleButton;
