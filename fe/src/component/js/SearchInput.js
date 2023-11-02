import React from "react";
import TextField from "@mui/material/TextField";
import styles from "../css/SearchInput.module.css";

function SearchInput({ setSearchWord }) {
  const handleInputChange = (event) => {
    const value = event.target.value;
    setSearchWord(value);
  };

  return (
    <TextField
      className={styles.input}
      color="primary"
      label="Search"
      sx={{ width: 327 }}
      variant="standard"
      onChange={handleInputChange}
    />
  );
}

export default SearchInput;
