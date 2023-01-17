import React, {
  ComponentPropsWithoutRef,
  ElementType,
  ReactElement,
} from "react";
import { Theme, useTheme } from "@mui/material/styles";
import OutlinedInput from "@mui/material/OutlinedInput";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import { Box, Chip, FormLabel, IconButton } from "@mui/material";
import CancelIcon from "@mui/icons-material/Cancel";
import _without from "lodash/without";
export interface ICDropdownPropsType
  extends ComponentPropsWithoutRef<ElementType> {
  optionsList: any[];
}

function getStyles(name: string, personName1: string[], theme: Theme) {
  return {
    display: "block",
    width: "500px",
    fontWeight:
      personName1.indexOf(name) === -1
        ? theme.palette.background.paper
        : theme.typography.fontWeightMedium,
  };
}

const ICMultiSelectDropdown = (props: ICDropdownPropsType): ReactElement => {
  const theme = useTheme();
  const handleDelete = (e: any, value: any) => {
    e.preventDefault();
    props.list((current: any) => _without(current, value));
  };
  console.log(props.width);
  return (
    <div>
      <Box
        sx={{
          p: 1,
          display: "flex",
          flexWrap: "wrap",
          gap: "0 1rem",
          background: "#aw21ef",
        }}
      >
        <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
          <FormLabel htmlFor={props.id} sx={{ color: "black" }}>
            {props.label}
          </FormLabel>
        </Box>
        <Box
          sx={{
            flexBasis: 0,
            borderRadius: 0,
            flexGrow: 5,
          }}
        >
          <FormControl
            sx={{
              width: props.width ? props.width : 500,
              paddingLeft: 2.5,
            }}
          >
            <Select
              readOnly={props.readOnly}
              id={props.id}
              name={props.id}
              multiple
              displayEmpty
              value={props.value}
              onChange={props.onChange}
              input={<OutlinedInput />}
              renderValue={(selected) => (
                <Box sx={{ display: "flex", flexWrap: "wrap", gap: 0.5 }}>
                  {selected.map((value: any) => (
                    <Chip
                      key={value}
                      deleteIcon={
                        <IconButton disabled={props.readOnly}>
                          <CancelIcon
                            onMouseDown={(event: any) =>
                              event.stopPropagation()
                            }
                          />
                        </IconButton>
                      }
                      label={value}
                      onDelete={(e: any) => {
                        handleDelete(e, value);
                      }}
                    />
                  ))}
                </Box>
              )}
              sx={{
                backgroundColor: "#ffffff",
                borderRadius: 2,
              }}
              MenuProps={{
                PaperProps: {
                  style: {
                    height: "250px",
                    position: "relative",
                    width: "2px",
                  },
                },
              }}
            >
              <MenuItem disabled></MenuItem>
              {props.optionsList.map((name) => (
                <MenuItem
                  key={name}
                  value={name}
                  style={getStyles(name, props.value, theme)}
                >
                  {name}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </Box>
      </Box>
    </div>
  );
};
export default ICMultiSelectDropdown;
