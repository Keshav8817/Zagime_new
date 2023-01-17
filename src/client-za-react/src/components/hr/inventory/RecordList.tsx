import Input from "../../Input";
import TextArea from "../../TextArea";
import Checkbox from "../../Checkbox";
import CancelIcon from "@mui/icons-material/Cancel";
import { Box, IconButton, Typography } from "@mui/material";
import AddMoreContext from "../../../contexts/AddMoreContext";
import type { inventory } from "../../../pages/hr/inventory/inventoryDatatypes";
import React, { useContext } from "react";
import type { FC } from "react";
import ICInput from "../../initialContact/Input";
import { removeInventory } from "../../../pages/hr/inventory/inventoryService";
import DateInput from "../../initialContact/DateInput";
/**

 * Record for inventory.

 * @example

 * ```jsx

 * <Record record={} number={} />

 * ```

 */

const RecordList1: any = ({ data, setData, status, disabled }: any) => {
  const handleCriminalRecordChange = (index: any, value: any, type: any) => {
    const updatedData: any = JSON.parse(JSON.stringify(data));
    updatedData[index][type] = value;
    setData(updatedData);
  };
  console.log(data);
  const removeFields = (index: any) => {
    const staffinventoryId = data[index].staffInventoryId;
    removeInventory(staffinventoryId).then((response) => {
      const updatedData: any = JSON.parse(JSON.stringify(data));
      const filterRecords: any = updatedData.filter(
        (_: any, i: number) => i !== index
      );
      setData(filterRecords);
    });
  };
  return (
    <>
      {data &&
        data.map((form: any, index: any) => {
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
                  Inventory No.{index + 1}
                </Typography>

                <IconButton
                  aria-label="delete record"
                  size="medium"
                  color="primary"
                  disabled={disabled}
                  sx={{ p: 0 }}
                  //
                  onClick={() => {
                    removeFields(index);
                  }}
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
                  <Box
                    sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}
                  >
                    <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                      <ICInput
                        onChange={(e: any) => {
                          handleCriminalRecordChange(
                            index,
                            e.target.value,
                            "item"
                          );
                        }}
                        value={data[index].item}
                        label="Item"
                        readOnly={disabled}
                      />
                    </Box>

                    <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                      <ICInput
                        onChange={(e: any) => {
                          handleCriminalRecordChange(
                            index,
                            e.target.value,
                            "serialNo"
                          );
                        }}
                        validationPattern={`^[^a-zA-Z]*$`}
                        validationTitle="Alphabets are not allowed!"
                        value={data[index].serialNo}
                        label="Serial No."
                        readOnly={disabled}
                      />
                    </Box>
                  </Box>

                  <Box
                    sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}
                  >
                    <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                      <DateInput
                        type="date"
                        onChange={(e: any) => {
                          handleCriminalRecordChange(
                            index,
                            e.target.value,
                            "from"
                          );
                        }}
                        value={data[index].from}
                        label="From"
                        maxDate={new Date().toISOString().substring(0, 10)}
                        minDate="1900-01-01"
                        readOnly={disabled}
                      />
                    </Box>

                    <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                      <DateInput
                        type="date"
                        onChange={(e: any) => {
                          handleCriminalRecordChange(
                            index,
                            e.target.value,
                            "to"
                          );
                        }}
                        value={data[index].to}
                        label="To"
                        maxDate={new Date().toISOString().substring(0, 10)}
                        minDate="1900-01-01"
                        readOnly={disabled}
                      />
                    </Box>
                  </Box>

                  <Box
                    sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}
                  >
                    <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                      <Checkbox
                        onChange={(e: any) => {
                          handleCriminalRecordChange(
                            index,
                            e.target.value.checked,
                            "Inuse"
                          );
                        }}
                        checked={data[index].inUse}
                        label="In Use"
                        labelPlacement="start"
                        readOnly={disabled}
                      />
                    </Box>

                    <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
                  </Box>

                  <Box sx={{ flexBasis: 0, flexGrow: 2 }}>
                    <TextArea
                      formLabelFlex="1 1 0"
                      outlinedInputFlex="5.3 1 0"
                      onChange={(e: any) => {
                        handleCriminalRecordChange(
                          index,
                          e.target.value,
                          "notes"
                        );
                      }}
                      value={data[index].notes}
                      label="Notes"
                      readOnly={disabled}
                    />
                  </Box>

                  <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
                </Box>
              </Box>
            </Box>
          );
        })}
    </>
  );
};

export default RecordList1;
