import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";
import HrLayout from "../../../components/hr/HrLayout";
import EditIcon from "../../../components/hr/trainings/EditIcon";
import AttachmentsContext from "../../../contexts/AttachmentsContext";
// import { doGetOne } from "../../../features/hr/attachments/slice";
// import { useAppDispatch, useAppSelector } from "../../../library/hooks";
import { Box } from "@mui/material";
import React, { useContext, useEffect } from "react";
import type { FC } from "react";

/**
 * `CG` aka `Caregivers` module.
 * Sub page: `Attachments`.
 * Sub sub page: `View`.
 * Form to view document information selected from attachments.
 * @returns `ReactElement`
 */
const View: FC = () => {
  const context = useContext(AttachmentsContext);
  // const dispatch = useAppDispatch();
  // const training = useAppSelector(
  //   (state) => state.hrTrainings.data[context.selected]
  // );

  // useEffect(() => {
  //   dispatch(doGetOne(training.trainingId))
  //     .unwrap()
  //     .catch((err) => {
  //       console.log(err);
  //     });
  // }, []);

  return (
    <HrLayout>
      <Box
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
        }}
      >
        <Box
          sx={{
            display: "flex",
            justifyContent: "flex-end",
          }}
        >
          {/* <EditIcon /> */}
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              // autofill={training.trainingName}
              id="trainingName"
              value="Training Name"
              disabled
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              // autofill={training.status}
              id="status"
              value="Status"
              disabled
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              // autofill={training.dateOfTraining}
              id="dateOfTraining"
              value="Date of Training"
              type="date"
              disabled
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              // autofill={training.expiryDate}
              id="expiryDate"
              value="Expiry Date"
              type="date"
              disabled
            />
          </Box>
        </Box>
        <TextArea
          // autofill={training.notes}
          formLabelFlex="1 1 0"
          outlinedInputFlex="5.3 1 0"
          id="notes"
          value="Notes"
          disabled
        />
        <Box />
      </Box>
    </HrLayout>
  );
};

export default View;
