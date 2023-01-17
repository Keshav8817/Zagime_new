import Input from "../../Input";
import TextArea from "../../TextArea";
import Checkbox from "../../Checkbox";
import { handleRemoveRecord } from "./record_";
import CancelIcon from "@mui/icons-material/Cancel";
import { Box, IconButton, Typography } from "@mui/material";
import AddMoreContext from "../../../contexts/AddMoreContext";
import type { inventory } from "../../../pages/hr/inventory/inventoryDatatypes";
import React, { useContext } from "react";
import type { FC } from "react";
import {
  ModuleContext,
  ModuleDispatchContext,
} from "../../../contexts/ModuleContext";

/**
 * Record for inventory.
 * @example
 * ```jsx
 * <Record record={} number={} />
 * ```
 */
const Record: FC<{
  record: inventory;
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
      <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
        <Typography color="primary" sx={{ flexGrow: 1 }}>
          Inventory No.{props.number}
        </Typography>
        <IconButton
          aria-label="delete record"
          size="medium"
          color="primary"
          disabled={/*editMode*/ false}
          sx={{ p: 0 }}
          onClick={(event) =>
            handleRemoveRecord(
              event,
              props.record.staffInventoryId,
              moduleContext,
              moduleDispatchContext
            )
          }
        >
          <CancelIcon fontSize="medium" />
        </IconButton>
      </Box>
      <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            gap: "1rem 0",
            flexGrow: 1,
          }}
        >
          <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
              <Input
                autofill={props.record.item}
                id={`record_${props.number}_Item`}
                value="Item"
                readOnly={/*editMode*/ false}
              />
            </Box>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
              <Input
                autofill={props.record.serialNo}
                id={`record_${props.number}_SerialNumber`}
                validationPattern={`^[^a-zA-Z]*$`}
                validationTitle="Alphabets are not allowed!"
                value="Serial No."
                readOnly={/*editMode*/ false}
              />
            </Box>
          </Box>
          <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
              <Input
                type="date"
                autofill={props.record.from}
                id={`record_${props.number}_From`}
                value="From"
                maxDate={new Date().toISOString().substring(0, 10)}
                minDate="1900-01-01"
                readOnly={/*editMode*/ false}
              />
            </Box>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
              <Input
                type="date"
                autofill={props.record.to}
                id={`record_${props.number}_To`}
                value="To"
                maxDate={new Date().toISOString().substring(0, 10)}
                minDate="1900-01-01"
                readOnly={/*editMode*/ false}
              />
            </Box>
          </Box>
          <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
              <Checkbox
                checked={props.record.inUse}
                id={`record_${props.number}_InUse`}
                label="In Use"
                labelPlacement="start"
                readOnly={/*editMode*/ false}
              />
            </Box>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 2 }}>
            <TextArea
              formLabelFlex="1 1 0"
              outlinedInputFlex="5.3 1 0"
              autofill={props.record.notes}
              id={`record_${props.number}_Notes`}
              value="Notes"
              readOnly={/*editMode*/ false}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
        </Box>
      </Box>
    </Box>
  );
};

export default Record;
