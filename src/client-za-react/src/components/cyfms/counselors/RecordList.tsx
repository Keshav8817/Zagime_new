import type { FC } from "react";
import { Box } from "@mui/system";
import { IconButton, Typography } from "@mui/material";
import CancelIcon from "@mui/icons-material/Cancel";
import TextArea from "../../TextArea";
import ICInput from "../../initialContact/Input";
import CYFMSDropdown from "../../Dropdown";
import { removeCounselorCFSWorker } from "../../../pages/cyfms/counselors/counselorsService";
import DateInput from "../../initialContact/DateInput";

/**
 * RecordList for counselors.
 * @example
 * ```jsx
 * <RecordList />
 * ```
 */
const RecordList: any = ({ data, setData, rolesCodetable, disabled }: any) => {
  const handleCriminalRecordChange = (index: any, value: any, type: any) => {
    const updatedData: any = JSON.parse(JSON.stringify(data));
    updatedData[index][type] = value;
    setData(updatedData);
  };

  const removeFields = (index: any) => {
    const counselorCFSWorkerId = data[index].counselorCFSWorkerId;
    removeCounselorCFSWorker(counselorCFSWorkerId).then((response) => {
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
              <Box
                sx={{
                  display: "flex",
                  flexWrap: "wrap",
                  gap: "0 1rem",
                }}
              >
                <Typography color="primary" sx={{ flexGrow: 1 }}>
                  Counselor / CFS Worker: {index + 1}
                </Typography>
                <IconButton
                  aria-label="delete record"
                  size="medium"
                  color="primary"
                  sx={{ p: 0 }}
                  disabled={disabled}
                  onClick={() => {
                    removeFields(index);
                  }}
                >
                  <CancelIcon fontSize="medium" />
                </IconButton>
              </Box>
              <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                  <CYFMSDropdown
                    onChange={(e: any) => {
                      handleCriminalRecordChange(index, e.target.value, "role");
                    }}
                    value={data[index].role}
                    label="Role"
                    optionsList={Object.values(rolesCodetable).map(
                      (role: any) => role.en
                    )}
                    disabled={disabled}
                  />
                </Box>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
              </Box>
              <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                  <ICInput
                    onChange={(e: any) => {
                      handleCriminalRecordChange(index, e.target.value, "name");
                    }}
                    value={data[index].name}
                    label="Name"
                    validationPattern={`^[a-zA-Z ]*$`}
                    validationTitle="Digits are not allowed!"
                    readOnly={disabled}
                  />
                </Box>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
              </Box>
              <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                  <DateInput
                    onChange={(e: any) => {
                      handleCriminalRecordChange(
                        index,
                        e.target.value,
                        "startDate"
                      );
                    }}
                    value={data[index].startDate}
                    label="Start Date"
                    maxDate={new Date().toISOString().substring(0, 10)}
                    minDate="1900-01-01"
                    type="date"
                    readOnly={disabled}
                  />
                </Box>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                  <DateInput
                    onChange={(e: any) => {
                      handleCriminalRecordChange(
                        index,
                        e.target.value,
                        "endDate"
                      );
                    }}
                    value={data[index].endDate}
                    label="End Date"
                    maxDate={new Date().toISOString().substring(0, 10)}
                    minDate="1900-01-01"
                    type="date"
                    readOnly={disabled}
                  />
                </Box>
              </Box>
              <TextArea
                formLabelFlex="1 1 0"
                outlinedInputFlex="5.3 1 0"
                onChange={(e: any) => {
                  handleCriminalRecordChange(
                    index,
                    e.target.value,
                    "contactInformation"
                  );
                }}
                value={data[index].contactInformation}
                label="Contact Information"
                readOnly={disabled}
              />
            </Box>
          );
        })}
    </>
  );
};

export default RecordList;
