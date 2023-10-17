// CustomTextField.js
import { TextField } from "@mui/material";
import SlideInAnimation from "../../animation/js/SlideInAnimation";

function CustomTextField(props) {
  SlideInAnimation();
  return (
    <TextField
      className={props.className}
      color="primary"
      label={props.label}
      sx={{ width: 318 }}
      variant="outlined"
    />
  );
}

export default CustomTextField;
