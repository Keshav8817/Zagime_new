import { ModuleContext } from "../../../contexts/ModuleContext";
import { useContext } from "react";
import { Box, IconButton, Typography } from "@mui/material";
import CancelIcon from "@mui/icons-material/Cancel";
import ICInput from "../../initialContact/Input";
import TextArea from "../../TextArea";
import { removeFamilyPhysician } from "../../../pages/cyfms/familyPhysicians/familyPhysiciansService";

/**
 * RecordList for family physicians.
 * @example
 * ```jsx
 * <RecordList />
 * ```
 */
const RecordList: any = ({ data, setData, disabled }: any) => {
  const handleCriminalRecordChange = (index: any, value: any, type: any) => {
    const updatedData: any = JSON.parse(JSON.stringify(data));
    updatedData[index][type] = value;
    setData(updatedData);
  };

  const removeFields = (index: any) => {
    const familyPhysicianId = data[index].familyPhysicianId;
    removeFamilyPhysician(familyPhysicianId).then((response) => {
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
                  Family Physician: {index + 1}
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
                            "name"
                          );
                        }}
                        value={data[index].name}
                        label="Name"
                        validationPattern={`^[a-zA-Z ]*$`}
                        validationTitle="Digits are not allowed!"
                        readOnly={disabled}
                      />
                    </Box>
                    <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                      <ICInput
                        onChange={(e: any) => {
                          handleCriminalRecordChange(
                            index,
                            e.target.value,
                            "phone"
                          );
                        }}
                        value={data[index].phone}
                        label="Phone"
                        validationPattern={`^[^a-zA-Z]*$`}
                        validationTitle="Alphabets are not allowed!"
                        readOnly={disabled}
                      />
                    </Box>
                  </Box>
                  <Box
                    sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}
                  >
                    <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                      <ICInput
                        onChange={(e: any) => {
                          handleCriminalRecordChange(
                            index,
                            e.target.value,
                            "cell"
                          );
                        }}
                        value={data[index].cell}
                        label="Cell"
                        validationPattern={`^[^a-zA-Z]*$`}
                        validationTitle="Alphabets are not allowed!"
                        readOnly={disabled}
                      />
                    </Box>
                    <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
                  </Box>
                  <Box sx={{ flexBasis: 0, flexGrow: 2 }}>
                    <TextArea
                      onChange={(e: any) => {
                        handleCriminalRecordChange(
                          index,
                          e.target.value,
                          "listOfMedication"
                        );
                      }}
                      value={data[index].listOfMedication}
                      label="List of Medication"
                      formLabelFlex="1 1 0"
                      outlinedInputFlex="5.3 1 0"
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

export default RecordList;
