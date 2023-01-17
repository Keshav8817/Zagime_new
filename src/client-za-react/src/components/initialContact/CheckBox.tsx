import React from "react";
import PropTypes from "prop-types";
import { Checkbox, FormControlLabel } from "@mui/material";
import CircleIcon from "@mui/icons-material/Circle";
import CheckBoxOutlineBlankRoundedIcon from "@mui/icons-material/CheckBoxOutlineBlankRounded";
import CheckBoxRoundedIcon from "@mui/icons-material/CheckBoxRounded";
import "../../index.css";
const CustomCheckbox = ({
  label,
  onChange,
  labelPlacement,
  checked,
  size,
  circle,
  disabled,
  ...otherProps
}: any) => {
  return (
    <FormControlLabel
      sx={{ marginLeft: "8px" }}
      label={label}
      labelPlacement={labelPlacement}
      control={
        <Checkbox
          checked={checked}
          size={size}
          disabled={disabled}
          onChange={onChange}
          icon={!circle ? <CheckBoxOutlineBlankRoundedIcon /> : <CircleIcon />}
          checkedIcon={!circle ? <CheckBoxRoundedIcon /> : <CircleIcon />}
          {...otherProps}
        />
      }
    />
  );
};

export default CustomCheckbox;

CustomCheckbox.defaultProps = {
  label: "",
  onChange: () => {},
  labelPlacement: "end",
  checked: false,
  size: "medium",
  circle: false,
  disabled: false,
};

CustomCheckbox.propTypes = {
  label: PropTypes.string,
  onChange: PropTypes.func.isRequired,
  labelPlacement: PropTypes.oneOf(["bottom", "end", "start", "top"]),
  checked: PropTypes.bool.isRequired,
  size: PropTypes.oneOf(["small", "medium", "large"]),
  circle: PropTypes.bool,
};
