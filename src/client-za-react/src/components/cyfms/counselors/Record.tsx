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
import type { Counselor } from "../../../pages/cyfms/counselors/counselorsDatatypes";
import type { FC } from "react";
import DateInput from "../../initialContact/DateInput";

/**
 * Record for counselor.
 * @example
 * ```jsx
 * <Record record={} number={} />
 * ```
 */
const Record: FC<{
  record: Counselor;
  number: number;
  rolesCodetable?: any;
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
          Counselor / CFS Worker: {props.number}
        </Typography>
        <IconButton
          aria-label="delete record"
          size="medium"
          color="primary"
          sx={{ p: 0 }}
          disabled={/*editMode*/ false}
          onClick={(event) =>
            handleRemoveRecord(
              event,
              props.record.counselorCFSWorkerId,
              moduleContext,
              moduleDispatchContext
            )
          }
        >
          <CancelIcon fontSize="medium" />
        </IconButton>
      </Box>
      <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
        <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
          <CYFMSDropdown
            autofill={props.record.role}
            id={`record_${props.number}_Role`}
            value="Role"
            optionsList={Object.values(props.rolesCodetable).map(
              (role: any) => role.en
            )}
            disabled={/*editMode*/ false}
          />
        </Box>
        <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
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
        <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
      </Box>
      <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
        <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
          <DateInput
            autofill={props.record.startDate}
            id={`record_${props.number}_StartDate`}
            maxDate={new Date().toISOString().substring(0, 10)}
            minDate="1900-01-01"
            value="Start Date"
            type="date"
            readOnly={/*editMode*/ false}
          />
        </Box>
        <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
          <DateInput
            autofill={props.record.endDate}
            id={`record_${props.number}_EndDate`}
            maxDate={new Date().toISOString().substring(0, 10)}
            minDate="1900-01-01"
            value="End Date"
            type="date"
            readOnly={/*editMode*/ false}
          />
        </Box>
      </Box>
      <TextArea
        formLabelFlex="1 1 0"
        outlinedInputFlex="5.3 1 0"
        autofill={props.record.contactInformation}
        id={`record_${props.number}_ContactInformation`}
        value="Contact Information"
        readOnly={/*editMode*/ false}
      />
    </Box>
  );
};

export default Record;
