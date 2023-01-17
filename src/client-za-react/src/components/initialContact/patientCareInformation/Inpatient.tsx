// import { useAppSelector } from "../../../library/hooks";
import TextArea from "../../TextArea";
import { Box, Typography } from "@mui/material";
import React from "react";
import type { FC } from "react";

/**
 * The Inpatient functional component.
 */
const Inpatient: any = ({ data, disabled }: any) => {
  return (
    <Box sx={{ display: "flex", flexDirection: "column", gap: "1rem 0" }}>
      <Typography color="primary" sx={{ flexGrow: 1 }}>
        Inpatient
      </Typography>
      <TextArea
        formLabelFlex="100% 0 0"
        outlinedInputFlex="100% 0 0"
        readOnly={disabled}
        autofill={data.inpatient?.hospitalizationRecord}
        id="hospitalizationRecord"
        value="Have you been in a hospital or residential treatment center for personal problems or alcohol/drug problems? Why?"
      />
      <TextArea
        formLabelFlex="100% 0 0"
        outlinedInputFlex="100% 0 0"
        readOnly={disabled}
        autofill={data.inpatient?.hospitalizationReasons}
        id="hospitalizationReasons"
        value="Reasons"
        multi="true"
      />
    </Box>
  );
};

export default Inpatient;
