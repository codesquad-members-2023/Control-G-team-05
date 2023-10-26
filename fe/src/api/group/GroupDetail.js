import { API_ENDPOINTS } from "../../constants/Endpoints";

export const fetchGroupDetail = async (groupId) => {
  try {
    const accessToken = localStorage.getItem("accessToken");

    const response = await fetch(API_ENDPOINTS.GROUP_DETAIL(groupId), {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
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
