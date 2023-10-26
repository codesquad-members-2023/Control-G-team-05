import { API_ENDPOINTS } from "../../constants/Endpoints";

export const fetchSignUpData = async (nickname, selectedImage) => {
  try {
    // FormData 객체 생성
    const formData = new FormData();

    const fetchResponse = await fetch(selectedImage.file);
    const blob = await fetchResponse.blob();

    formData.append("nickname", nickname); // 'nickname' 필드에 nickname 값을 추가
    console.log(selectedImage);
    formData.append("profileImage", blob, selectedImage.filename);

    const response = await fetch(API_ENDPOINTS.SIGNUP, {
      method: "POST",
      headers: {
        Authorization: "Bearer " + localStorage.getItem("signupToken"),
      },
      body: formData,
    });

    localStorage.removeItem("signupToken");
    const data = await response.json();
    console.log(data);
    return data;
  } catch (err) {
    console.error("Error:", err);
    throw err;
  }
};
