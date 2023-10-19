import React from "react";
import { Link } from "react-router-dom";
import Button from "@mui/material/Button";
import styles from "../css/NavigationButton.module.css";

function NavigationButton({ to, label }) {
  return (
    <Link to={to}>
      <Button
        className={styles.button}
        sx={{ width: 316 }}
        color="primary"
        variant="outlined"
      >
        {label}
      </Button>
    </Link>
  );
}

export default NavigationButton;
