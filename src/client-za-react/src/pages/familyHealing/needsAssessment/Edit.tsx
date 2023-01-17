import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import Dropdown from "../../../components/Dropdown";
import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";

import { onKeyDown } from "../../../library/app";
import { Box, Typography } from "@mui/material";
import React, { useEffect, useState } from "react";
import type { FC, FormEvent } from "react";

import { Data } from "./NeedAssessmentDataType";
import { useNavigate, useParams } from "react-router";
import FhLayout from "../../../components/fh/FhLayout";
import {
  readNeedAssessment,
  saveNeedAssessment,
} from "./NeedAssessmentService";
import EditIcon from "../../../components/fh/fhNeedsAssessment/EditIcon";
import ICInput from "../../../components/initialContact/Input";
import DateInput from "../../../components/initialContact/DateInput";

const Edit: FC<any> = ({ setAddNew, targetValue }) => {
  // const dispatch = useAppDispatch();
  const { id, childId } = useParams();
  const navigate = useNavigate();
  const [disabled, setDisabled] = useState(true);
  const [state, setState] = useState<Data>({
    fhNeedAssessmentId: Number(childId),
    assessmentDate: "",
    completedBy: "",
    family: "",
    identified: "",
    address: "",
    resources: "",
    fhFileDetailsId: Number(id) | 0,
  });
  // const state = useAppSelector((state) => state.icFileDetails);
  // const { contactMethod } = useAppSelector((state) => state.codetable);
  // const data = useAppSelector((state) => state.icContactNotes.data);
  useEffect(() => {
    readNeedAssessment(Number(childId)).then(({ data }) => {
      setState(data);
    });
  }, []);
  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const form = e.currentTarget as HTMLFormElement;
    const formData: Data = {
      fhFileDetailsId: Number(id),
      fhNeedAssessmentId: Number(childId),
      assessmentDate: form.assessmentDate.value,
      completedBy: form.completedBy.value,
      family: form.family.value,
      identified: form.identified.value,
      address: form.address.value,
      resources: form.resources.value,
    };
    saveNeedAssessment(formData).then(() => {
      navigate(`../NeedsAssessment/${id}`);
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
              setAddNew={setAddNew}
              Id={state.fhNeedAssessmentId}
              targetValue={targetValue}
              setDisabled={setDisabled}
            />
          </Box>
        )}
        <Box
          sx={{
            display: "flex",
            flexWrap: "wrap",
            gap: "0 1rem",
            marginLeft: "8px",
          }}
        >
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <DateInput
              id="assessmentDate"
              value="Assessment Date"
              required
              type="date"
              autofill={state.assessmentDate}
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
          sx={{ marginBottom: "0px", marginLeft: "15px" }}
          variant="body1"
        >
          Describe the Family
        </Typography>
        <TextArea
          formLabelFlex="0 1 0"
          outlinedInputFlex="5.3 1 0"
          id="family"
          autofill={state.family}
          readOnly={disabled}
        />
        <Typography
          sx={{ marginBottom: "0px", marginLeft: "15px" }}
          variant="body1"
        >
          What need(s) were identified?
        </Typography>
        <TextArea
          formLabelFlex="0 1 0"
          outlinedInputFlex="5.3 1 0"
          id="identified"
          autofill={state.identified}
          readOnly={disabled}
        />
        <Typography
          sx={{ marginBottom: "0px", marginLeft: "15px" }}
          variant="body1"
        >
          What is already being done to address the needs(s)?
        </Typography>
        <TextArea
          formLabelFlex="0 1 0"
          outlinedInputFlex="5.3 1 0"
          id="address"
          autofill={state.address}
          readOnly={disabled}
        />
        <Typography
          sx={{ marginBottom: "0px", marginLeft: "15px" }}
          variant="body1"
        >
          What resources are available to help meet the needs?
        </Typography>
        <TextArea
          formLabelFlex="0 1 0"
          outlinedInputFlex="5.3 1 0"
          id="resources"
          autofill={state.resources}
          readOnly={disabled}
        />
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSNextButton disabled={disabled} />
        </Box>
      </Box>
    </FhLayout>
  );
};

export { Edit as NeedsAssessmentEdit };
