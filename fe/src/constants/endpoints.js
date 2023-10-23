export const BASE_URL = "http://localhost:8080";
export const API_ENDPOINTS = {
  LOGIN: (queryString) =>
    `${BASE_URL}/api/login/oauth/naver?code=${queryString}`,
  SIGNUP: `${BASE_URL}/api/auth/signup`,
};
