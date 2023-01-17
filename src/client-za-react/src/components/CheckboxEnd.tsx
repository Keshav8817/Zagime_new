import { Checkbox as MuiCheckbox, FormControlLabel } from "@mui/material";
import React, { useState } from "react";
import type { FC, InputHTMLAttributes } from "react";

interface Props extends InputHTMLAttributes<HTMLInputElement> {
  /** Overrides the initial checked state. */
  checked?: boolean;
  /** Associated label. */
  label?: string;
  icon?: any;
  checkedIcon?: any;
}

export type CheckboxProps = Props;

/**
 * `Checkbox` is used on forms.
 * @param props
 */
const CheckboxEnd: FC<Props> = (props) => {
  const [checked, setChecked] = useState<boolean>(props.checked ? true : false);
  return (
    <FormControlLabel
      sx={{ flex: "1 1 0", color: "black" }}
      disabled={props.disabled}
      control={
        <MuiCheckbox
          sx={{
            display: "flex",
            flexDirection: "row",
          }}
          required={props.required}
          checked={checked}
          id={props.id}
          onChange={() => setChecked(!checked)}
          value={props.value}
          icon={props?.icon}
          checkedIcon={props?.checkedIcon}
        />
      }
      label={props.label}
      labelPlacement="start"
    />
  );
};

export default CheckboxEnd;
