import React, {
  ComponentPropsWithoutRef,
  ElementType,
  ReactElement,
} from "react";
import { Theme, useTheme } from "@mui/material/styles";
import OutlinedInput from "@mui/material/OutlinedInput";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select, { SelectChangeEvent } from "@mui/material/Select";
import { Box, FormLabel } from "@mui/material";

export interface FhDropdownPropsType
  extends ComponentPropsWithoutRef<ElementType> {
  optionsList: any[];
}

function getStyles(name: string, personName: string[], theme: Theme) {
  return {
    display: "block",
    width: "300px",
    fontWeight:
      personName.indexOf(name) === -1
        ? theme.typography.fontWeightRegular
        : theme.typography.fontWeightMedium,
  };
}

const FHMultiSelectDropdown = (props: FhDropdownPropsType): ReactElement => {
  const theme = useTheme();
  var y = props.autofill.split(",");
  const val = y.filter((item: any, index: any) => y.indexOf(item) === index);
  const removeEmptyString = val.filter((item: any) => item);
  const [alcoholName, setAlcoholName] =
    React.useState<string[]>(removeEmptyString);

  const handleChange = (event: SelectChangeEvent<typeof alcoholName>) => {
    const {
      target: { value },
    } = event;
    setAlcoholName(
      // On autofill we get a stringified value.
      typeof value === "string" ? value.split(",") : value
    );
  };

  return (
    <div>
      <Box
        sx={{
          display: "flex",
          flexWrap: "wrap",
          gap: "0 0.5rem",
          background: "#aw21ef",
        }}
      >
        <Box sx={{ flexBasis: 0, flexGrow: 1, paddingLeft: 1 }}>
          <FormLabel htmlFor={props.id} sx={{ color: "black" }}>
            {props.value}
          </FormLabel>
        </Box>
        <FormControl
          sx={{
            flexBasis: 0,
            borderRadius: 2,
            flexGrow: 2,
          }}
        >
          <Select
            id={props.id}
            name={props.id}
            multiple
            displayEmpty
            value={alcoholName}
            onChange={handleChange}
            input={<OutlinedInput />}
            renderValue={(selected) => {
              if (selected.length === 1) {
                return selected.join("");
              }
              return selected.join(", ");
            }}
            sx={{
              height: "40px",
              backgroundColor: "#ffffff",
              maxWidth: "300px",
              borderRadius: 2,
              display: "flex",
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
                style={getStyles(name, alcoholName, theme)}
              >
                {name}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
      </Box>
    </div>
  );
};
export default FHMultiSelectDropdown;
