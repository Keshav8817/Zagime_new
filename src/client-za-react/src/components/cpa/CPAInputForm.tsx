import { FormControl, FormLabel, OutlinedInput } from "@mui/material";
import React from "react";
import type {
  ElementType,
  ReactElement,
  ComponentPropsWithoutRef,
} from "react";
/**
 * The ICInput functional component.
 * @param props - HTML attributes.
 * @returns ICInput component skeleton.
 */
export interface InputProps extends ComponentPropsWithoutRef<ElementType> {
  /* Date */
  maxDate?: string;
  minDate?: string;
}
const CPAInputForm = (
  props: ComponentPropsWithoutRef<ElementType>
): ReactElement => {
  return (
    <FormControl
      disabled={props.disabled}
      required={props.required}
      sx={{
        display: "flex",
        flexDirection: "row",
        flexWrap: "wrap",
        gap: "0rem",
      }}
    >
      <FormLabel
        htmlFor={props.id}
        sx={{ flexBasis: 0, flexGrow: 2, color: "black" }}
      >
        {props.value}
      </FormLabel>
      <OutlinedInput
        id={props.id}
        readOnly={props.readOnly}
        name={props.name}
        sx={{ borderRadius: 0, flexBasis: 0, flexGrow: 2, ml: -1 }}
        inputProps={{ min: props.minDate, max: props.maxDate, sx: { p: 1 } }}
        type={props.type}
        defaultValue={props.autofill}
        style={{ backgroundColor: "#dfdada" }}
      />
    </FormControl>
  );
};

export default CPAInputForm;
