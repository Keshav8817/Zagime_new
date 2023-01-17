import {
  ModuleContext,
  ModuleDispatchContext,
} from "../../../contexts/ModuleContext";
import CYFMSDropdown from "../../Dropdown";
import Input from "../../Input";
import TextArea from "../../TextArea";
import { handleRemoveRecord } from "./record_";
import CancelIcon from "@mui/icons-material/Cancel";
import { Box, IconButton, Typography } from "@mui/material";
import React, { useContext } from "react";
import type { HouseholdMember } from "../../../pages/cyfms/householdMembers/householdMembersDatatypes";
import type { FC } from "react";

/**
 * Record for counselor.
 * @example
 * ```jsx
 * <Record record={} number={} />
 * ```
 */
const Record: FC<{
  record: HouseholdMember;
  number: number;
  gendersCodetable?: any;
}> = (props) => {
  const moduleContext = useContext(ModuleContext);
  const moduleDispatchContext = useContext(ModuleDispatchContext);

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        gap: "1rem 0",
        p: "0.5rem",
        borderRadius: "1rem",
        boxShadow: `inset 2px 2px 3px rgba(191, 191, 191, .6),
                    inset -2px -2px 3px rgba(0, 0, 0, .6)`,
      }}
    >
      <Box
        sx={{
          display: "flex",
          flexWrap: "wrap",
          gap: "0 1rem",
        }}
      >
        <Typography color="primary" sx={{ flexGrow: 1 }}>
          Household Member: {props.number}
        </Typography>
        <IconButton
          aria-label="delete record"
          size="medium"
          color="primary"
          disabled={/*editMode*/ false}
          sx={{ p: 0 }}
          onClick={(event) => {
            handleRemoveRecord(
              event,
              props.record.householdMemberId,
              moduleContext,
              moduleDispatchContext
            );
          }}
        >
          <CancelIcon fontSize="medium" />
        </IconButton>
      </Box>
      <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
        <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
          <Input
            autofill={props.record.name}
            id={`record_${props.number}_Name`}
            validationPattern={`^[a-zA-Z ]*$`}
            validationTitle="Digits are not allowed!"
            value="Name"
            readOnly={/*editMode*/ false}
          />
        </Box>
        <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
          <CYFMSDropdown
            autofill={props.record.gender}
            id={`record_${props.number}_Gender`}
            optionsList={Object.values(props.gendersCodetable).map(
              (gender: any) => gender.en
            )}
            value="Gender"
            disabled={moduleContext.editMode}
          />
        </Box>
      </Box>
      <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
        <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
          <Input
            autofill={props.record.dateOfBirth}
            id={`record_${props.number}_DateOfBirth`}
            maxDate={new Date().toISOString().substring(0, 10)}
            minDate="1900-01-01"
            value="Date of Birth"
            type="date"
            readOnly={/*editMode*/ false}
          />
        </Box>
        <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
          <Input
            autofill={props.record.relationship}
            id={`record_${props.number}_Relationship`}
            value="Relationship"
            readOnly={/*editMode*/ false}
          />
        </Box>
      </Box>
      <TextArea
        formLabelFlex="1 1 0"
        outlinedInputFlex="5.3 1 0"
        autofill={props.record.residing}
        id={`record_${props.number}_Residing`}
        value="Residing"
        readOnly={/*editMode*/ false}
      />
    </Box>
  );
};

export default Record;
