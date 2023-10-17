import styles from "../css/TopBar.module.css";

function TopBar({ topBarName, icon }) {
  return (
    <div className={styles.topBar}>
      <div className={styles.topBarwrapper}>
        <img
          className={styles.productLogo}
          alt=""
          src="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/e37e10d3-e510-4572-91e9-cde50f4c13db_1697438464922631682?Expires=-62135596800&Signature=u9r4Gke6EwZYOqGOriq6iPv1Oehbnxu-I3zO11evMRyXwcJe6y8PkoWVs0W6u6y2Wh7CO9poo15TfttpEa-HYfWxf3m7TPzYx-Nc0pCmP1M9BInyLShBoCsgkeGZ9CtS61su-IAaKM5bzM7znlnIaRpx~rdmO5mtCpF4EUgv5ifHFqwg23HPROF2gtaqUgmvdjpoEpKJnxulx1WietAAfdfQmMPTrmD8kHbb3ALVK9~SRs0RT-G-OYcYK7qVNGSnX8CkxmUZ-dVoH6uvlVketY8HEjXFfz~S2lreuy8foGmZ5gyti7IbnwKVKx6I~1TWtdtmSzhwUcY3yTr-gkncMA__&Key-Pair-Id=K1P54FZWCHCL6J"
        />
        <div className={styles.topBarName}>{topBarName}</div>
        {icon && (
          <img alt="" src={icon} className={styles.notificationsNoneIcon} />
        )}
      </div>
    </div>
  );
}

export default TopBar;
