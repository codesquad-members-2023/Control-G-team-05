// CustomTextField.js
import { TextField } from "@mui/material";
import SlideInAnimation from "../../animation/js/SlideInAnimation";

// 회원가입 할때 사용하는 text input field 이다.

function CustomTextField({ placeHolder }) {
  SlideInAnimation();
  return (
    <TextField
      color="primary"
      label={placeHolder}
      sx={{ width: 318 }}
      variant="outlined"
    />
  );
}

export default CustomTextField;
