// import { useAppSelector } from "../../../library/hooks";
import Input from "../../Input";
import TextArea from "../../TextArea";
import { Box, Typography } from "@mui/material";
import React from "react";

/**
 * The Outpatient functional component.
 */
const Outpatient: any = ({ data, disabled }: any) => {
  return (
    <Box sx={{ display: "flex", flexDirection: "column", gap: "1rem 0" }}>
      <Typography color="primary" sx={{ flexGrow: 1 }}>
        Outpatient
      </Typography>
      <TextArea
        formLabelFlex="100% 0 0"
        readOnly={disabled}
        outlinedInputFlex="100% 0 0"
        autofill={data.outpatient?.therapyOrCounseling}
        id="therapyOrCounseling"
        value="Have you seen a therapist or counselor for personal or family problems or alcohol/drug treatment?"
      />
      <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
        <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
          <Input
            readOnly={disabled}
            autofill={data.outpatient?.therapyTimePeriod}
            id="therapyTimePeriod"
            value="When?"
            type="date"
          />
        </Box>
        <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
          <Input
            readOnly={disabled}
            autofill={data.outpatient?.therapyLocation}
            id="therapyLocation"
            value="Where?"
          />
        </Box>
      </Box>
      <TextArea
        readOnly={disabled}
        formLabelFlex="100% 0 0"
        outlinedInputFlex="100% 0 0"
        autofill={data.outpatient?.reasonForTherapy}
        id="reasonForTherapy"
        value="Reasons"
        multi="true"
      />
      <TextArea
        readOnly={disabled}
        formLabelFlex="100% 0 0"
        outlinedInputFlex="100% 0 0"
        autofill={data.outpatient?.selfHelpGroup}
        id="selfHelpGroup"
        value="Any involvement in self help groups such as NA, AA, etc?"
      />
      <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
        <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
          <Input
            readOnly={disabled}
            autofill={data.outpatient?.selfHelpGroupPeriod}
            id="selfHelpGroupPeriod"
            value="When?"
            type="date"
          />
        </Box>
        <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
          <Input
            readOnly={disabled}
            autofill={data.outpatient?.selfHelpGroupLocation}
            id="selfHelpGroupLocation"
            value="Where?"
          />
        </Box>
      </Box>
    </Box>
  );
};

export default Outpatient;
