import { API_ENDPOINTS } from "../../constants/Endpoints";

export const fetchChatDetail = async (chatRoomId) => {
  try {
    const accessToken = localStorage.getItem("accessToken");

    const response = await fetch(API_ENDPOINTS.CHAT_DETAIL(chatRoomId), {
      method: "GET",
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    });

    return await response.json();
  } catch (err) {
    console.error("Error:", err);
    throw err;
  }
};
