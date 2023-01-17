import AddIcon from "@mui/icons-material/Add";
import SaveIcon from "@mui/icons-material/Save";
import Button from "@mui/material/Button";
import React from "react";
import type {
  ComponentPropsWithoutRef,
  ElementType,
  ReactElement,
} from "react";

/**
 * The CYFSWMSAddButton functional component.
 * @returns CYFSWMSAddButton component skeleton.
 */
export const CYFSWMSAddButton = (
  props: ComponentPropsWithoutRef<ElementType>
): ReactElement => {
  return (
    <Button
      sx={{ backgroundColor: "#a4c86a" }}
      onClick={props.onClick}
      startIcon={<AddIcon />}
      variant="contained"
      disabled={props.disabled}
    >
      Add More
    </Button>
  );
};
export const CYFSWMSRButton = (
  props: ComponentPropsWithoutRef<ElementType>
): ReactElement => {
  return (
    <Button
      sx={{ bgcolor: "#9ccc65" }}
      onClick={props.onClick}
      variant="contained"
    >
      Remove
    </Button>
  );
};
export const CYFSWMSAButton = (
  props: ComponentPropsWithoutRef<ElementType>
): ReactElement => {
  return (
    <Button
      sx={{ bgcolor: "#9ccc65", paddingX: "2px" }}
      onClick={props.onClick}
      variant="contained"
    >
      Add
    </Button>
  );
};

/**
 * The CYFSWMSNextButton functional component.
 * @returns CYFSWMSNextButton component skeleton.
 */
export const CYFSWMSNextButton = (
  props: ComponentPropsWithoutRef<ElementType>
): ReactElement => {
  return (
    <Button
      sx={{ backgroundColor: "#ffa500" }}
      type="submit"
      variant="contained"
      disabled={props.disabled}
      {...props}
    >
      Save
    </Button>
  );
};

/**
 * The CYFSWMSSaveButton functional component.
 * @returns CYFSWMSSaveButton component skeleton.
 */
export const CYFSWMSSaveButton = (
  props: ComponentPropsWithoutRef<ElementType>
): ReactElement => {
  return (
    <Button
      sx={{ backgroundColor: "#ffa500" }}
      type="submit"
      variant="contained"
      disabled={props.disabled}
    >
      Save
    </Button>
  );
};

export const CYFSWMSViewButton = (
  props: ComponentPropsWithoutRef<ElementType>
): ReactElement => {
  return (
    <Button type="submit" variant="contained" {...props}>
      View
    </Button>
  );
};
