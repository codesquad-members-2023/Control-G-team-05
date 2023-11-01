import { API_ENDPOINTS } from "../../constants/Endpoints";
import { ParseJwt } from "../../utils/ParseJwt";

export const fetchSSEConnection = (setRefreshChatList) => {
  const accessToken = localStorage.getItem("accessToken");
  const decodedPayload = ParseJwt(accessToken);
  const { memberId } = decodedPayload;
  // EventSource 인스턴스를 생성하여 서버의 SSE 엔드포인트에 연결
  const eventSource = new EventSource(API_ENDPOINTS.SSH_CONNECTION(memberId));

  // 서버로부터 메시지가 도착하면 실행될 이벤트 리스너를 설정
  eventSource.addEventListener("refreshChatRoomList", function (event) {
    const data = event.data;
    console.log(data);
    if (data === "refresh") {
      console.log("refresh가 곧 될거야!");
      setRefreshChatList((current) => !current);
    }
  });

  // 연결이 오픈되었을 때 실행될 이벤트 리스너를 설정
  eventSource.onopen = (event) => {
    console.log("Connection to server opened.");
  };

  // 에러가 발생했을 때 실행될 이벤트 리스너를 설정
  eventSource.onerror = (event) => {
    console.log("EventSource failed." + event.data);
  };

  // 연결을 닫는 함수를 반환
  return () => {
    eventSource.close();
    console.log("EventSource connection closed.");
  };
};
