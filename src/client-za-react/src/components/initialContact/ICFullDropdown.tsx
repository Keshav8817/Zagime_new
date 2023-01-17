import { FormControl, FormLabel } from "@mui/material";
import React from "react";
import type {
  ElementType,
  ReactElement,
  ComponentPropsWithoutRef,
} from "react";
import "../../styles/App.css";
export interface ICDropdownPropsType
  extends ComponentPropsWithoutRef<ElementType> {
  optionsList: any[];
}

/**
 * The ICDropdown functional component.
 * @param props - HTML attributes.
 * @returns ICDropdown component skeleton.
 */
const ICFullDropdown = (props: ICDropdownPropsType): ReactElement => {
  return (
    <FormControl
      sx={{
        display: "flex",
        flexDirection: "row",
        flexWrap: "wrap",
      }}
      required={props.required}
    >
      <FormLabel
        htmlFor={props.id}
        sx={{ p: 1, flexBasis: 0, flexGrow: 4, color: "black" }}
      >
        {props.value}
      </FormLabel>
      <select
        className="disabled"
        disabled={props.disabled}
        name={props.id}
        id={props.id}
        defaultValue={props.autofill}
        style={{
          fontSize: 15,
          display: "block",
          flexBasis: 0,
          flexGrow: 1,
          height: "40px",
          backgroundColor: "#dfdada",
        }}
      >
        <option disabled></option>
        {props.optionsList.map((item: any) => (
          <option key={item}>{item}</option>
        ))}
      </select>
    </FormControl>
  );
};

export default ICFullDropdown;
