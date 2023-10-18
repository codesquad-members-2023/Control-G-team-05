import ChatDetailTopBar from "../../component/js/ChatDetailTopBar";
import ChatMessageBox from "../../component/js/ChatMessageBox";
import styles from "../css/ChatDetailPage.module.css";
import { TextField } from "@mui/material";

function ChatDetailPage() {
  return (
    <div className={styles.div}>
      <ChatDetailTopBar
        groupName="code squad"
        gender="male"
        opponentName="charlie"
      />
      <div className={styles.chatArea}>
        <ChatMessageBox
          isOwnMessage={false}
          message="hello light"
          time="2:46"
          isRead="Read"
        />
        <ChatMessageBox
          isOwnMessage={true}
          message="hello charlie"
          time="2:47"
          isRead="Read"
        />
        <ChatMessageBox
          isOwnMessage={true}
          message="bye charlie"
          time="2:48"
          isRead="Read"
        />
        <ChatMessageBox
          isOwnMessage={false}
          message="bye light"
          time="2:49"
          isRead="Read"
        />
      </div>
      <div className={styles.chatDetailBottomBar}>
        <div className={styles.messagecontainerParent}>
          <TextField
            color="primary"
            label="Message"
            sx={{
              width: 325,
            }}
            variant="outlined"
            multiline
            maxRows={5}
          />
          <img
            className={styles.sendIcon}
            alt=""
            src="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/5cda7c3a-76da-4bcb-bdfa-a7e8a0e632f8_1697594684559098000?Expires=-62135596800&Signature=BIsAb0t1Bbl2E~Ndb~dB~WTBxhn69WWHHe4pAvdpJ6JlM-g7q-cZ~heHBXvFm~7d6-KWJ-NMFg854jMLhsxcXxOkVtUWBeUX7PNyUVtzpPrTfinVVLm-3j2SulgPNDgzwKeoL4zIbC9ZBmiwPe~bv-aPbBcOklRaF-2SAJzd3vO493nG2UOB49Oo-wrWgbyKSJCdoQufa4dDvus6V-w-ra5rbFNZPKYGAObvCw5et9GagYNFPRImOxDqjgHTZbc7zt7BZiVx0LLGbIYeyUXQtHlM~TxFgfB639N3Ic41IJMuKLCsNTLFRDldLthgnFWx~79hsKQWz5EPdPT~yS7KDQ__&Key-Pair-Id=K1P54FZWCHCL6J"
          />
        </div>
      </div>
    </div>
  );
}

export default ChatDetailPage;
