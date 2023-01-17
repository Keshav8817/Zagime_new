import { FormControl, FormLabel, OutlinedInput } from "@mui/material";
import React from "react";
import type {
  ElementType,
  ReactElement,
  ComponentPropsWithoutRef,
} from "react";

export interface InputProps extends ComponentPropsWithoutRef<ElementType> {
  /* Customizes width of label and input */
  formLabelFlex?: string;
  outlinedInputFlex?: string;
}

/**
 * The Input functional component.
 * @param props HTML attributes.
 * @returns Input component skeleton.
 */
const Input = (props: InputProps): ReactElement => {
  return (
    <FormControl
      disabled={props.disabled}
      required={props.required}
      sx={{
        display: "flex",
        flexDirection: "row",
        flexWrap: "wrap",
      }}
    >
      <FormLabel
        htmlFor={props.id}
        sx={{ p: 1, flex: props.formLabelFlex || "1 1 0", color: "black" }}
      >
        {props.value}
      </FormLabel>
      <OutlinedInput
        defaultValue={props.autofill}
        id={props.id}
        name={props.name}
        inputProps={{
          accept: "image/*",
          hidden: true,
          sx: { p: 1 },
        }}
        type="file"
        sx={{
          backgroundColor: "#ffffff",
          borderRadius: 2,
          flex: props.outlinedInputFlex || "2 1 0",
        }}
      />
    </FormControl>
  );
};

export default Input;
