import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import Dropdown from "../../../components/Dropdown";
import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";

import { onKeyDown } from "../../../library/app";

import { Box, Typography } from "@mui/material";
import React, { useEffect, useState } from "react";
import type { FC, FormEvent } from "react";
import { Data } from "./ProgressReportsDataType";
import {
  readProgressReport,
  saveProgressReport,
} from "./ProgressreportsService";
import { useNavigate, useParams } from "react-router-dom";
import FhLayout from "../../../components/fh/FhLayout";
import EditIcon from "../../../components/fh/fhProgressReports/EditIcon";
import ICInput from "../../../components/initialContact/Input";
import DateInput from "../../../components/initialContact/DateInput";

const Edit: FC<any> = ({ setAddNew, targetValue }) => {
  const navigate = useNavigate();

  const [disabled, setDisabled] = useState(true);
  const { id, childId } = useParams();

  const [state, setState] = useState<Data>({
    fhProgressReportId: Number(childId),
    dateOfAssessment: "",
    completedBy: "",
    areas: "",
    sessionsAttended: "",
    sessions: "",
    progress: "",
    fhFileDetailsId: Number(id) | 0,
  });
  useEffect(() => {
    readProgressReport(Number(childId)).then(({ data }) => {
      setState(data);
    });
  }, []);
  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const form = e.currentTarget as HTMLFormElement;
    const formData: Data = {
      fhProgressReportId: Number(childId),
      dateOfAssessment: form.dateOfAssessment.value,
      completedBy: form.completedBy.value,
      areas: form.areas.value,
      sessionsAttended: form.sessionsAttended.value,
      sessions: form.sessions.value,
      progress: form.progress.value,
      fhFileDetailsId: Number(id),
    };
    saveProgressReport(formData).then(() => {
      navigate(`../progressReports/${id}`);
    });
  };
  return (
    <FhLayout>
      <Box
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
        }}
        onSubmit={submitHandler}
        onKeyDown={onKeyDown}
      >
        {disabled && (
          <Box
            sx={{
              display: "flex",
              justifyContent: "flex-end",
            }}
          >
            <EditIcon
              setDisabled={setDisabled}
              setAddNew={setAddNew}
              Id={state.fhProgressReportId}
              targetValue={targetValue}
            />
          </Box>
        )}
        <Box
          sx={{
            display: "flex",
            flexWrap: "wrap",
            gap: "0 1rem",
            marginLeft: "6px",
          }}
        >
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <DateInput
              id="dateOfAssessment"
              value="Date of Assessment"
              type="date"
              required
              autofill={state.dateOfAssessment}
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              id="completedBy"
              value="Completed By"
              required
              autofill={state.completedBy}
              readOnly={disabled}
            />
          </Box>
        </Box>
        <Typography
          sx={{ marginBottom: "0px", marginLeft: "12px" }}
          variant="body1"
        >
          Suggested areas for support
        </Typography>
        <TextArea
          formLabelFlex="0 1 0"
          outlinedInputFlex="5.3 1 0"
          id="areas"
          autofill={state.areas}
          readOnly={disabled}
        />
        <Typography
          sx={{ marginBottom: "0px", marginLeft: "12px" }}
          variant="body1"
        >
          Describe the sessions attended
        </Typography>
        <TextArea
          formLabelFlex="0 1 0"
          id="sessionsAttended"
          outlinedInputFlex="5.3 1 0"
          autofill={state.sessionsAttended}
          readOnly={disabled}
        />
        <Typography
          sx={{ marginBottom: "0px", marginLeft: "12px" }}
          variant="body1"
        >
          What was the behavior during these sessions
        </Typography>
        <TextArea
          formLabelFlex="0 1 0"
          outlinedInputFlex="5.3 1 0"
          id="sessions"
          autofill={state.sessions}
          readOnly={disabled}
        />
        <Typography
          sx={{ marginBottom: "0px", marginLeft: "12px" }}
          variant="body1"
        >
          Describe the progress made since the last report
        </Typography>
        <TextArea
          formLabelFlex="0 1 0"
          outlinedInputFlex="5.3 1 0"
          id="progress"
          autofill={state.progress}
          readOnly={disabled}
        />
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSNextButton disabled={disabled} />
        </Box>
      </Box>
    </FhLayout>
  );
};

export { Edit as ProgressReportsEdit };
