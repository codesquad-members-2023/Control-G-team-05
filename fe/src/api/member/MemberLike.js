import { API_ENDPOINTS } from "../../constants/Endpoints";

export const fetchMemberLike = async (memberId) => {
  try {
    const accessToken = localStorage.getItem("accessToken");

    await fetch(API_ENDPOINTS.MEMBER_LIKE(memberId), {
      method: "POST",
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    });
  } catch (err) {
    console.error("Error:", err);
    throw err;
  }
};
