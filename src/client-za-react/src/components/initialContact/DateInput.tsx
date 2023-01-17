import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import {
  FormControl,
  FormLabel,
  IconButton,
  InputAdornment,
  OutlinedInput,
} from "@mui/material";
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
  /* Validation */
  validationTitle?: string;
  validationPattern?: string;
  /* Date */
  maxDate?: string;
  minDate?: string;
  /* Length */
  minChars?: number;
}

/**
 * The Input functional component.
 * @param props HTML attributes.
 * @returns Input component skeleton.
 */
const DateInput = (props: InputProps): ReactElement => {
  const extraProps: any = {};
  if (props.onChange) {
    extraProps.onChange = props.onChange;
    extraProps.value = props.value;
  }
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
        {props.label || props.value}
      </FormLabel>
      <input
        {...extraProps}
        className=" flex-[1.9_2_0%] border border-neutral-300 focus:border-sky-500 rounded-lg  px-2 focus:outline-none focus:border-orange-500 focus:ring-1 focus:ring-orange-500 hover:border-black"
        defaultValue={props.autofill}
        id={props.id}
        name={props.name}
        disabled={props.disabled}
        readOnly={props.readOnly}
        type={props.type}
      />
    </FormControl>
  );
};

export default DateInput;
