import { useParams } from "react-router-dom";
import ChatDetailTopBar from "../../component/js/ChatDetailTopBar";
import ChatMessageBox from "../../component/js/ChatMessageBox";
import styles from "../css/ChatDetailPage.module.css";
import { TextField } from "@mui/material";
import { useRef, useCallback, useEffect, useState } from "react";
import { fetchChatDetail } from "../../api/chat/ChatDetail";
import react from "react";
import { ParseJwt } from "../../utils/ParseJwt";
import { useStompClient } from "../../api/chat/Stomp";

function ChatDetailPage() {
  const { chatRoomId } = useParams();
  const [partnerInfo, setPartnerInfo] = useState([]);
  const [messages, setMessages] = useState([]);
  //채팅 상대방에게 좋아요를 눌렀는지 확인하는 state
  const [isLiked, setIsLike] = useState();
  const [inputMessage, setInputMessage] = useState("");
  // 맨마지막 메세지 참조를 위한 useRef
  const chatEndRef = useRef(null);
  // 한글입력시 엔터키가 중복으로 2번 눌리는 현상을 방지하기 위한 useState
  const [isComposing, setIsComposing] = useState(false);

  useEffect(() => {
    fetchChatDetail(chatRoomId).then((data) => {
      setPartnerInfo(data.partner);
      setIsLike(data.partner.isLiked);
      setMessages(data.messages);
    });
  }, [chatRoomId]);

  const accessToken = localStorage.getItem("accessToken");
  const decodedPayload = ParseJwt(accessToken);
  const { memberId } = decodedPayload;

  //stomp <--------------------------------------------------------------------------->
  // api 를 부르는게 아니며, 랜더링될때 실행될 이유가 없어 useCallback을 사용한다.
  const onMessageReceived = useCallback((data) => {
    setMessages((prevMessages) => [...prevMessages, data.messages]);
    setInputMessage("");
  }, []);

  // STOMP 클라이언트 사용
  const sendMessage = useStompClient(
    accessToken,
    chatRoomId,
    onMessageReceived
  );

  //채팅 입력시 스크롤 맨아래로 내리기
  useEffect(() => {
    // 현재 ref가 참조하는 요소(스크롤을 맨 아래로 이동)
    chatEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  return (
    <div className={styles.div}>
      <ChatDetailTopBar
        groupName={partnerInfo.groupName}
        gender={partnerInfo.gender}
        opponentName={partnerInfo.nickname}
        partnerId={partnerInfo.id}
        isLiked={isLiked}
        setIsLiked={setIsLike}
        chatRoomId={chatRoomId}
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
        <div ref={chatEndRef} />
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
          value={inputMessage}
          onChange={(e) => setInputMessage(e.target.value)}
          onCompositionStart={() => setIsComposing(true)}
          onCompositionEnd={() => setIsComposing(false)}
          onKeyDown={(e) => {
            if (e.key === "Enter" && !e.shiftKey && !isComposing) {
              e.preventDefault();
              sendMessage(inputMessage, memberId);
            }
          }}
        />
        <button
          type="button" // 버튼이 폼을 제출하지 않도록 설정
          className={styles.sendButton}
          onClick={() => sendMessage(inputMessage, memberId)}
        >
          <img
            alt="Send"
            src="https://d1xzdqg8s8ggsr.cloudfront.net/652ca67bbbe533c504a77c20/5cda7c3a-76da-4bcb-bdfa-a7e8a0e632f8_1697594684559098000?Expires=-62135596800&Signature=BIsAb0t1Bbl2E~Ndb~dB~WTBxhn69WWHHe4pAvdpJ6JlM-g7q-cZ~heHBXvFm~7d6-KWJ-NMFg854jMLhsxcXxOkVtUWBeUX7PNyUVtzpPrTfinVVLm-3j2SulgPNDgzwKeoL4zIbC9ZBmiwPe~bv-aPbBcOklRaF-2SAJzd3vO493nG2UOB49Oo-wrWgbyKSJCdoQufa4dDvus6V-w-ra5rbFNZPKYGAObvCw5et9GagYNFPRImOxDqjgHTZbc7zt7BZiVx0LLGbIYeyUXQtHlM~TxFgfB639N3Ic41IJMuKLCsNTLFRDldLthgnFWx~79hsKQWz5EPdPT~yS7KDQ__&Key-Pair-Id=K1P54FZWCHCL6J" // 이미지 URL을 src로 설정
            className={styles.sendIcon}
          />
        </button>
      </div>
    </div>
  );
}

export default ChatDetailPage;
