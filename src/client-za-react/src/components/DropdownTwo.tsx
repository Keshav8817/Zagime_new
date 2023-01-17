import { Box, FormControl, FormLabel } from "@mui/material";
import React from "react";
import type { ElementType, FC, ComponentPropsWithoutRef } from "react";

export interface DropdownProps extends ComponentPropsWithoutRef<ElementType> {
  formLabelFlex?: string;
  outlinedInputFlex?: string;
  optionsList: any[];
}

/**
 * `Dropdown is the select field.`
 * @param props - HTML attributes.
 */
const Dropdown: FC<DropdownProps> = (props) => {
  let formLabelFlex = props.formLabelFlex ? props.formLabelFlex : "1 1 0";
  let selectFlex = props.outlinedInputFlex ? props.outlinedInputFlex : "2 1 0";
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
        sx={{
          p: 1,
          flex: formLabelFlex,
          color: "black",
          "&.Mui-disabled": {
            color: "black",
          },
        }}
      >
        {props.label || props.value}
      </FormLabel>
      <Box
        sx={{
          flex: selectFlex,
          display: "flex",
          flexDirection: "column",
        }}
      >
        <select
          {...extraProps}
          disabled={props.disabled}
          name={props.id}
          id={props.id}
          style={{
            margin: "auto 0",
            fontSize: 15,
            height: "39px",
            backgroundColor: "#FFFFFF",
            borderRadius: 10,
            border: "1px solid #c4c4c4",
            paddingLeft: "12px",
          }}
        >
          {/* <option></option> */}
          {props.optionsList.map((item: any) => (
            <option
              key={item}
              value={item}
              selected={item === props.autofill ? true : false}
            >
              {item}
            </option>
          ))}
        </select>
      </Box>
    </FormControl>
  );
};

export default Dropdown;
