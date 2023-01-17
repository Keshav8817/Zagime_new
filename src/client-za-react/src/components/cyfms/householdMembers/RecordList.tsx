import type { FC } from "react";
import { Box } from "@mui/system";
import { IconButton, Typography } from "@mui/material";
import TextArea from "../../TextArea";
import CancelIcon from "@mui/icons-material/Cancel";
import CYFMSDropdown from "../../Dropdown";
import ICInput from "../../initialContact/Input";
import { removeHouseHoldMember } from "../../../pages/cyfms/householdMembers/householdMembersService";
import DateInput from "../../initialContact/DateInput";

/**
 * RecordList for household members.
 * @example
 * ```jsx
 * <RecordList />
 * ```
 */
const RecordList: any = ({ data, setData, status, disabled }: any) => {
  const handleCriminalRecordChange = (index: any, value: any, type: any) => {
    const updatedData: any = JSON.parse(JSON.stringify(data));
    updatedData[index][type] = value;
    setData(updatedData);
  };

  const removeFields = (index: any) => {
    const householdMemberId = data[index].householdMemberId;
    removeHouseHoldMember(householdMemberId).then((response) => {
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
                  Household Member: {index + 1}
                </Typography>
                <IconButton
                  aria-label="delete record"
                  size="medium"
                  color="primary"
                  disabled={disabled}
                  sx={{ p: 0 }}
                  onClick={() => {
                    removeFields(index);
                  }}
                >
                  <CancelIcon fontSize="medium" />
                </IconButton>
              </Box>
              <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                  <ICInput
                    onChange={(e: any) => {
                      handleCriminalRecordChange(index, e.target.value, "name");
                    }}
                    value={data[index].name}
                    validationPattern={`^[a-zA-Z ]*$`}
                    validationTitle="Digits are not allowed!"
                    label="Name"
                    readOnly={disabled}
                  />
                </Box>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                  <CYFMSDropdown
                    onChange={(e: any) => {
                      handleCriminalRecordChange(
                        index,
                        e.target.value,
                        "gender"
                      );
                    }}
                    value={data[index].gender}
                    optionsList={Object.values(status).map(
                      (gender: any) => gender.en
                    )}
                    label="Gender"
                    disabled={disabled}
                  />
                </Box>
              </Box>
              <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                  <DateInput
                    onChange={(e: any) => {
                      handleCriminalRecordChange(
                        index,
                        e.target.value,
                        "dateOfBirth"
                      );
                    }}
                    value={data[index].dateOfBirth}
                    maxDate={new Date().toISOString().substring(0, 10)}
                    minDate="1900-01-01"
                    label="Date of Birth"
                    type="date"
                    readOnly={disabled}
                  />
                </Box>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                  <ICInput
                    onChange={(e: any) => {
                      handleCriminalRecordChange(
                        index,
                        e.target.value,
                        "relationship"
                      );
                    }}
                    value={data[index].relationship}
                    label="Relationship"
                    readOnly={disabled}
                  />
                </Box>
              </Box>
              <TextArea
                formLabelFlex="1 1 0"
                outlinedInputFlex="5.3 1 0"
                onChange={(e: any) => {
                  handleCriminalRecordChange(index, e.target.value, "residing");
                }}
                value={data[index].residing}
                label="Residing"
                readOnly={disabled}
              />
            </Box>
          );
        })}
    </>
  );
};

export default RecordList;
