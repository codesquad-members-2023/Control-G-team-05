import React from "react";
import TextField from "@mui/material/TextField";
import styles from "../css/SearchInput.module.css";

function SearchInput() {
  return (
    <TextField
      className={styles.input}
      color="primary"
      label="Search"
      sx={{ width: 327 }}
      variant="standard"
    />
  );
}

export default SearchInput;
