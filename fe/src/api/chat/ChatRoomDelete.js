import { API_ENDPOINTS } from "../../constants/Endpoints";

export const fetchChatRoomDelete = async (chatRoomId) => {
  try {
    const accessToken = localStorage.getItem("accessToken");

    await fetch(API_ENDPOINTS.CHAT_ROOM_DELETE(chatRoomId), {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    });
  } catch (err) {
    console.error("Error:", err);
    throw err;
  }
};
