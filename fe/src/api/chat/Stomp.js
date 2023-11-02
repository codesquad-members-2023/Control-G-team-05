import { useRef, useEffect } from "react";
import { Client } from "@stomp/stompjs";
import { API_ENDPOINTS } from "../../constants/Endpoints";

export const useStompClient = (accessToken, chatRoomId, onMessageReceived) => {
  const stompClient = useRef(null);

  useEffect(() => {
    stompClient.current = new Client({
      brokerURL: API_ENDPOINTS.WEB_SOCKET,
      connectHeaders: {
        Authorization: `Bearer ${accessToken}`,
        chatRoomId: `${chatRoomId}`,
      },
      onConnect: () => {
        console.log("Connected to the WebSocket");

        stompClient.current.subscribe(
          API_ENDPOINTS.STOMP_SUBSCRIBE(chatRoomId),
          (message) => {
            console.log("메세지 받음");
            //구독한 채널에서 받은 메세지를 어떻게 처리할 것인가
            onMessageReceived(JSON.parse(message.body));
          }
        );
      },
    });

    stompClient.current.activate();

    //clean up 함수
    return () => {
      stompClient.current.deactivate();
      console.log("Disconnected from the WebSocket");
    };
  }, [accessToken, chatRoomId, onMessageReceived]);

  const sendMessage = (messageContent, memberId) => {
    if (
      stompClient.current &&
      stompClient.current.active &&
      messageContent.trim() !== ""
    ) {
      const chatMessage = {
        chatRoomId: chatRoomId,
        message: messageContent,
        senderId: memberId,
      };
      console.log(chatMessage);

      stompClient.current.publish({
        destination: API_ENDPOINTS.STOMP_SEND,
        body: JSON.stringify(chatMessage),
      });
    }
  };

  return sendMessage;
};
