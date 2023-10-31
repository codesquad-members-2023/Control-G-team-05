import { useParams } from "react-router-dom";
import ChatDetailTopBar from "../../component/js/ChatDetailTopBar";
import ChatMessageBox from "../../component/js/ChatMessageBox";
import styles from "../css/ChatDetailPage.module.css";
import { TextField } from "@mui/material";
import { useEffect, useState } from "react";
import { fetchChatDetail } from "../../api/chat/ChatDetail";
import react from "react";
import { ParseJwt } from "../../utils/ParseJwt";

function ChatDetailPage() {
  const { chatRoomId } = useParams();
  const [partnerInfo, setPartnerInfo] = useState([]);
  const [messages, setMessages] = useState([]);
  //채팅 상대방에게 좋아요를 눌렀는지 확인하는 state
  const [isLiked, setIsLike] = useState();

  useEffect(() => {
    fetchChatDetail(chatRoomId).then((data) => {
      setPartnerInfo(data.partner);
      console.log(data);
      setIsLike(data.partner.isLiked);
      setMessages(data.messages);
    });
  }, [chatRoomId]);

  const accessToken = localStorage.getItem("accessToken");
  const decodedPayload = ParseJwt(accessToken);
  const { memberId } = decodedPayload;

  return (
    <div className={styles.div}>
      <ChatDetailTopBar
        groupName={partnerInfo.groupName}
        gender={partnerInfo.gender}
        opponentName={partnerInfo.nickname}
        partnerId={partnerInfo.id}
        isLiked={isLiked}
        setIsLiked={setIsLike}
      />
      <div className={styles.chatArea}>
        {messages.map((message) => (
          <react.Fragment key={message.id}>
            <ChatMessageBox
              isOwnMessage={message.senderId === memberId}
              message={message.message}
              time={message.sentAt}
              isRead={message.isRead}
            />
          </react.Fragment>
        ))}
      </div>
      <div className={styles.chatDetailBottomBar}>
        <TextField
          color="primary"
          label="Message"
          sx={{
            width: 320,
          }}
          size="small"
          variant="outlined"
          multiline
          maxRows={3}
        />
        <img
          className={styles.sendIcon}
          alt=""
          src="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/5cda7c3a-76da-4bcb-bdfa-a7e8a0e632f8_1697594684559098000?Expires=-62135596800&Signature=BIsAb0t1Bbl2E~Ndb~dB~WTBxhn69WWHHe4pAvdpJ6JlM-g7q-cZ~heHBXvFm~7d6-KWJ-NMFg854jMLhsxcXxOkVtUWBeUX7PNyUVtzpPrTfinVVLm-3j2SulgPNDgzwKeoL4zIbC9ZBmiwPe~bv-aPbBcOklRaF-2SAJzd3vO493nG2UOB49Oo-wrWgbyKSJCdoQufa4dDvus6V-w-ra5rbFNZPKYGAObvCw5et9GagYNFPRImOxDqjgHTZbc7zt7BZiVx0LLGbIYeyUXQtHlM~TxFgfB639N3Ic41IJMuKLCsNTLFRDldLthgnFWx~79hsKQWz5EPdPT~yS7KDQ__&Key-Pair-Id=K1P54FZWCHCL6J"
        />
      </div>
    </div>
  );
}

export default ChatDetailPage;
