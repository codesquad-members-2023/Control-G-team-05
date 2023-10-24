export const BASE_URL = "http://localhost:8080";
export const API_ENDPOINTS = {
  LOGIN: (queryString) =>
    `${BASE_URL}/api/login/oauth/naver?code=${queryString}`,

  SIGNUP: `${BASE_URL}/api/auth/signup`,

  MEMBER_GROUP_LIST: (searchWord, memberId) => {
    let queryString = "";

    if (searchWord && memberId) {
      queryString = `?word=${searchWord}&forMember=true}`;
    } else if (searchWord) {
      queryString = `?word=${searchWord}`;
    } else if (memberId) {
      queryString = `?forMember=true`;
    }
    return `${BASE_URL}/api/groups${queryString}`;
  },
};
