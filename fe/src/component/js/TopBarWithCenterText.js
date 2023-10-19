import styles from "../css/TopBarWithCenterText.module.css";
import { Link } from "react-router-dom";

function TopBarWithCenterText({ topBarName, returnLink }) {
  return (
    <div className={styles.topBar}>
      <Link to={returnLink}>
        <img
          alt=""
          src="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/60605814-9ca5-44e6-b700-18549a8bf841_1697439260687989733?Expires=-62135596800&Signature=CBTQwaMvZSKES8D8Zr5MYEBGYP4pFQrBe68ZDBihMsaRWbHXmJPNiuUN-r94WT9wXhTB89NbkfVvydtHrFWy80U-tRiNrVcVman5OWJkkSIzBOQLoHEh1Lee~ETslcXyxCohirUXlv-x2GqfQ6bxR2GKIej04tYJpOOq4V8koLVwc7sJSnbhGm5~wlB6PAajIsoTWcyTdo33eNjVwRMuPGk8nIh149b2gkkIJEaKRbcSVNr1BHsX4BcYjTiecUG2SYB6wtOrkv2mhOdJIxMOSUdKIL9XvNIL~6O2ftgqIkLMGCg~7~WX1zmFVkgJOM76MXrwAEAiVnNSGMwSMhiyyw__&Key-Pair-Id=K1P54FZWCHCL6J"
          className={styles.backButton}
        />
      </Link>
      <div className={styles.topBarwrapper}>
        <div className={styles.topBarName}>{topBarName}</div>
      </div>
    </div>
  );
}

export default TopBarWithCenterText;
