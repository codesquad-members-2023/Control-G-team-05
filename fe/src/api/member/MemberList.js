import { API_ENDPOINTS } from "../../constants/Endpoints";

export const fetchMemberList = async (selected) => {
  try {
    const accessToken = localStorage.getItem("accessToken");

    const response = await fetch(API_ENDPOINTS.GET_MEMBER_LIKE(selected), {
      method: "GET",
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    });

    const data = await response.json();
    return data;
  } catch (err) {
    console.error("Error:", err);
    throw err;
  }
};
