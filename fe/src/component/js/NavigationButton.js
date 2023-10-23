import React from "react";
import { Link } from "react-router-dom";
import Button from "@mui/material/Button";
import styles from "../css/NavigationButton.module.css";

// sign-in, sign-up group 생성 할때 사용하는 버튼

function NavigationButton({ to, label, clickEvent, disabled }) {
  return (
    <Link to={to}>
      <Button
        className={styles.button}
        onClick={clickEvent}
        sx={{ width: 316 }}
        color="primary"
        variant="outlined"
        disabled={disabled}
      >
        {label}
      </Button>
    </Link>
  );
}

export default NavigationButton;
