import { API_ENDPOINTS } from "../../constants/endpoints";

export const fetchOauthData = async (accessCode) => {
  try {
    const response = await fetch(API_ENDPOINTS.LOGIN(accessCode), {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
    });

    const data = await response.json();
    return data;
  } catch (err) {
    console.error("Error:", err);
    throw err;
  }
};
