import { API_ENDPOINTS } from "../../constants/Endpoints";

export const fetchChatMatching = async (groupId) => {
  try {
    const formData = new FormData();

    const accessToken = localStorage.getItem("accessToken");

    const response = await fetch(API_ENDPOINTS.MATCHING(groupId), {
      method: "POST",
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
      body: formData,
    });

    if (response.status === 201) {
      const data = await response.json();
      return data.chatRoomId; // 새 채팅방 ID 반환
    } else if (response.status === 422) {
      return "상대를 찾을수 없습니다.";
    }
  } catch (err) {
    console.error("Error:", err);
    throw err;
  }
};
