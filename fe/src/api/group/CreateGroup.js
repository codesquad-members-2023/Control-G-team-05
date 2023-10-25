import { API_ENDPOINTS } from "../../constants/endpoints";

export const fetchGroupCreate = async (name, selectedImage) => {
  try {
    const formData = new FormData();

    const fetchResponse = await fetch(selectedImage.file);
    const blob = await fetchResponse.blob();

    const accessToken = localStorage.getItem("accessToken");

    formData.append("name", name);
    console.log(name);
    console.log(selectedImage);
    formData.append("image", blob, selectedImage.filename);

    await fetch(API_ENDPOINTS.GROUP_CREATE(), {
      method: "POST",
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
      body: formData,
    });
  } catch (err) {
    console.error("Error:", err);
    throw err;
  }
};
