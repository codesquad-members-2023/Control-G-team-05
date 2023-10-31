import styles from "../css/ChatLoadingPage.module.css";
import TopBarWithCenterText from "../../component/js/TopBarWithCenterText";
import NavigationBar from "../../component/js/NavigationBar";
import PuffLoader from "react-spinners/PuffLoader";
import { useEffect, useState } from "react";
import { fetchChatMatching } from "../../api/chat/Matching";
import Button from "@mui/material/Button";
import { useNavigate } from "react-router-dom";

function ChatLoadingPage() {
  const navigate = useNavigate();
  const [showRetryButton, setShowRetryButton] = useState(false);
  const [retry, setRetry] = useState(false); // 버튼 클릭 횟수를 추적하는 상태

  useEffect(() => {
    async function fetchAndSetChatRoomId() {
      console.log("매칭 요청 시작");
      const response = await fetchChatMatching(1);
      if (typeof response === "number") {
        // chatRoom 으로 바로 이동하도록 한다.
        navigate(`/chats/${response}`);
      } else if (typeof response === "string") {
        setShowRetryButton(true);
      }
    }
    fetchAndSetChatRoomId();
  }, [retry, navigate]);

  const handleRetry = () => {
    setShowRetryButton(false);
    setRetry((prev) => !prev);
  };

  return (
    <div className={styles.div}>
      <TopBarWithCenterText topBarName="Group Profile" returnLink="/main" />
      <div>
        {!showRetryButton && <PuffLoader color="#36d7b7" loading size={200} />}
      </div>
      <div className={styles.textAndButtonContainer}>
        <div className={styles.text}>
          {!showRetryButton && (
            <div className={styles.text}>채팅 상대 찾는중</div>
          )}
        </div>
        {showRetryButton && (
          <Button
            sx={{ width: 140 }}
            color="primary"
            variant="outlined"
            onClick={handleRetry}
          >
            채팅상대 다시 찾기
          </Button>
        )}
      </div>
      <NavigationBar />
    </div>
  );
}

export default ChatLoadingPage;
