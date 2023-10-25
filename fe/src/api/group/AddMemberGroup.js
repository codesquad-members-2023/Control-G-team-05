import { API_ENDPOINTS } from "../../constants/endpoints";

// 그룹 추가가 완료되면 data에 아무런 데이터가 없을거고, 추가 실패시 data의 message에 오류 메세지가 있다.

export const fetchAddGroupMember = async (groupId) => {
  try {
    const accessToken = localStorage.getItem("accessToken");

    const response = await fetch(API_ENDPOINTS.GROUP_DETAIL(groupId), {
      method: "POST",
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    });

    try {
      const data = await response.json();
      if (data) {
        return "이미 참여한 그룹입니다.";
      }
    } catch (error) {
      return "내 그룹 등록 성공";
    }
  } catch (err) {
    console.error("Error:", err);
    throw err;
  }
};
