import type { FC } from "react";
import TextArea from "../../TextArea";
import { Box, IconButton, Typography } from "@mui/material";
import CancelIcon from "@mui/icons-material/Cancel";
import DateInput from "../../initialContact/DateInput";
import { remove } from "../../../pages/cyfms/criminalHistory/criminalHistoryService";

/**
 * RecordList for criminal history.
 * @example
 * ```jsx
 * <RecordList />
 * ```
 */
const RecordList: any = ({ data, setData, disabled }: any) => {
  const handleCriminalRecordChange = (index: any, value: any, type: any) => {
    const updatedData: any = JSON.parse(JSON.stringify(data));
    updatedData.criminalHistoryRecordList[index][type] = value;
    setData(updatedData);
  };

  const removeFields = (index: any) => {
    const criminalHistoryId =
      data.criminalHistoryRecordList[index].criminalHistoryRecordId;
    remove(criminalHistoryId).then((response) => {
      const updatedData: any = JSON.parse(JSON.stringify(data));
      const filterRecords: any = updatedData.criminalHistoryRecordList.filter(
        (_: any, i: number) => i !== index
      );
      updatedData.criminalHistoryRecordList = filterRecords;
      setData(updatedData);
    });
  };
  return (
    <>
      {data.criminalHistoryRecordList &&
        data.criminalHistoryRecordList.map((form: any, index: any) => {
          return (
            <div key={index} className="myform">
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
                    flexDirection: "column",
                    gap: "1rem 0",
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
                      Record {index + 1}
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
                  <Box
                    sx={{
                      display: "flex",
                      flexWrap: "wrap",
                      gap: "0 1rem",
                    }}
                  >
                    <Box sx={{ flexBasis: "0", flexGrow: 1 }}>
                      <DateInput
                        autofill={
                          data.criminalHistoryRecordList[index].arrestDate
                        }
                        onChange={(e: any) => {
                          handleCriminalRecordChange(
                            index,
                            e.target.value,
                            "arrestDate"
                          );
                        }}
                        value={data.criminalHistoryRecordList[index].arrestDate}
                        maxDate={new Date().toISOString().substring(0, 10)}
                        minDate="1900-01-01"
                        label="Arrest Date"
                        type="date"
                        readOnly={disabled}
                      />
                    </Box>
                    <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
                  </Box>
                  <TextArea
                    onChange={(e: any) => {
                      handleCriminalRecordChange(
                        index,
                        e.target.value,
                        "charges"
                      );
                    }}
                    value={data.criminalHistoryRecordList[index].charges}
                    formLabelFlex="1 1 0"
                    outlinedInputFlex="5.3 1 0"
                    id={`record_${index}_Charges`}
                    label="Charges"
                    readOnly={disabled}
                  />
                  <TextArea
                    onChange={(e: any) => {
                      handleCriminalRecordChange(
                        index,
                        e.target.value,
                        "conviction"
                      );
                    }}
                    value={data.criminalHistoryRecordList[index].conviction}
                    formLabelFlex="1 1 0"
                    outlinedInputFlex="5.3 1 0"
                    id={`record_${index}_Conviction`}
                    label="Conviction"
                    readOnly={disabled}
                  />
                  <TextArea
                    onChange={(e: any) => {
                      handleCriminalRecordChange(
                        index,
                        e.target.value,
                        "sentence"
                      );
                    }}
                    value={data.criminalHistoryRecordList[index].sentence}
                    formLabelFlex="1 1 0"
                    outlinedInputFlex="5.3 1 0"
                    id={`record_${index}_Sentence`}
                    label="Sentence"
                    readOnly={disabled}
                  />
                </Box>
              </Box>
            </div>
          );
        })}
    </>
  );
};

export default RecordList;
