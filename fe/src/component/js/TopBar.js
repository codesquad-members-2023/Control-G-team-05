import { CONSTANT } from "../../constants/Constant";
import styles from "../css/TopBar.module.css";
import { Link } from "react-router-dom";

function TopBar({ topBarName, icon, linkPage }) {
  return (
    <div className={styles.topBar}>
      <div className={styles.topBarwrapper}>
        <img
          className={styles.productLogo}
          alt=""
          src={CONSTANT.CONTROL_G_ICON}
        />
        <div className={styles.topBarName}>{topBarName}</div>
        {icon && (
          <Link to={linkPage}>
            <img alt="" src={icon} className={styles.notificationsNoneIcon} />
          </Link>
        )}
      </div>
    </div>
  );
}

export default TopBar;
