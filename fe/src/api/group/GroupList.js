import { API_ENDPOINTS } from "../../constants/Endpoints";

export const fetchGroupList = async (searchWord) => {
  try {
    const accessToken = localStorage.getItem("accessToken");

    const response = await fetch(API_ENDPOINTS.MEMBER_GROUP_LIST(searchWord), {
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
