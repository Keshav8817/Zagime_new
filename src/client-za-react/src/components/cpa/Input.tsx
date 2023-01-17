// import { FormControl, FormLabel, OutlinedInput } from "@mui/material";
// import React from "react";
// import type {
//   ElementType,
//   ReactElement,
//   ComponentPropsWithoutRef,
// } from "react";

// /**
//  * The ICInput functional component.
//  * @param props - HTML attributes.
//  * @returns ICInput component skeleton.
//  */
// export interface InputProps extends ComponentPropsWithoutRef<ElementType> {
//   /* Date */
//   maxDate?: string;
//   minDate?: string;
// }
// const ICInput = (
//   props: ComponentPropsWithoutRef<ElementType>
// ): ReactElement => {
//   return (
//     <FormControl
//       disabled={props.disabled}
//       required={props.required}
//       sx={{
//         display: "flex",
//         flexDirection: "row",
//         flexWrap: "wrap",
//       }}
//     >
//       <FormLabel
//         htmlFor={props.id}
//         sx={{ p: 1, flexBasis: 0, flexGrow: 1, color: "black" }}
//       >
//         {props.value}
//       </FormLabel>
//       <OutlinedInput
//         id={props.id}
//         readOnly={props.readOnly}
//         name={props.name}
//         sx={{ borderRadius: 2, flexBasis: 0, flexGrow: 2, ml: -1 }}
//         inputProps={{ min: props.minDate, max: props.maxDate, sx: { p: 1 } }}
//         type={props.type}
//         defaultValue={props.autofill}
//         style={{ backgroundColor: "#ffffff" }}
//       />
//     </FormControl>
//   );
// };

// export default ICInput;

import { Box, FormControl, FormLabel, OutlinedInput } from "@mui/material";
import React from "react";
import type { ElementType, FC, ComponentPropsWithoutRef } from "react";

export interface TextAreaProps extends ComponentPropsWithoutRef<ElementType> {
  formLabelFlex?: string;
  outlinedInputFlex?: string;
}

/**
 * `TextArea` is the textarea field.
 * @param props - HTML attributes.
 */
const ICInput: FC<TextAreaProps> = (props) => {
  console.log(props);
  let formLabelFlex = props.formLabelFlex ? props.formLabelFlex : "1 1 0";
  let outlinedInputFlex = props.outlinedInputFlex
    ? props.outlinedInputFlex
    : "2 1 0";
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
        sx={{ p: 1, flexBasis: 0, flexGrow: 1, color: "black" }}
      >
        {props.value}
      </FormLabel>
      <Box
        sx={{
          flex: outlinedInputFlex,
          display: "flex",
          flexDirection: "column",
        }}
      >
        <OutlinedInput
          defaultValue={props.autofill}
          id={props.id}
          inputProps={{ min: props.minDate, max: props.maxDate }}
          multiline={true}
          name={props.name}
          type={"date"}
          size={"small"}
          readOnly={props.readOnly}
          sx={{ borderRadius: 2, flexBasis: 0, flexGrow: 0 }}
        />
      </Box>
    </FormControl>
  );
};

export default ICInput;
