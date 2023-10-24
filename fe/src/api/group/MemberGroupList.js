import { API_ENDPOINTS } from "../../constants/endpoints";
import { ParseJwt } from "../../utils/ParseJwt";

export const fetchMemberGroupList = async (searchWord) => {
  try {
    const accessToken = localStorage.getItem("accessToken");

    const decodedPayload = ParseJwt(accessToken);
    const { memberId } = decodedPayload;

    const response = await fetch(
      API_ENDPOINTS.MEMBER_GROUP_LIST(searchWord, memberId),
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${accessToken}`,
        },
      }
    );

    const data = await response.json();
    return data;
  } catch (err) {
    console.error("Error:", err);
    throw err;
  }
};
