import styles from "../css/ToggleButton.module.css";
import { useState } from "react";

function ToggleButton({ leftLabel, rightLabel, buttonSelectedFunction }) {
  const [toggle, setToggle] = useState(false);

  // false -> liked, true -> matched
  const toggleState = () => {
    const newToggleValue = !toggle;
    setToggle(newToggleValue);
    if (newToggleValue) {
      buttonSelectedFunction("matched");
    } else {
      buttonSelectedFunction("like");
    }
  };

  return (
    <form className={styles.switchField}>
      <input
        type="radio"
        id="switch_left"
        name="switchToggle"
        value={leftLabel}
        onChange={toggleState}
        checked={!toggle}
      />
      <label htmlFor="switch_left">{leftLabel}</label>
      <input
        type="radio"
        id="switch_right"
        name="switchToggle"
        value={rightLabel}
        onChange={toggleState}
        checked={toggle}
      />
      <label htmlFor="switch_right">{rightLabel}</label>
    </form>
  );
}

export default ToggleButton;
