import styles from "../css/ChatLoadingPage.module.css";
import TopBarWithCenterText from "../../component/js/TopBarWithCenterText";
import NavigationBar from "../../component/js/NavigationBar";
import PuffLoader from "react-spinners/PuffLoader";

function ChatLoadingPage() {
  return (
    <div className={styles.div}>
      <TopBarWithCenterText topBarName="Group Profile" returnLink="/main" />
      <div>
        <PuffLoader color="#36d7b7" loading size={200} />
      </div>
      <div className={styles.text}>looking for an opponent</div>
      <NavigationBar />
    </div>
  );
}

export default ChatLoadingPage;
