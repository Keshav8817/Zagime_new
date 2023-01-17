import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import Dropdown from "../../../components/Dropdown";
import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";

import { Data } from "./CasePlanDataType";
import { onKeyDown } from "../../../library/app";

import { Box, Typography } from "@mui/material";
import React, { useState } from "react";
import type { FC, FormEvent } from "react";
import { saveCasePlan } from "./CasePlanService";
import { useNavigate, useParams } from "react-router-dom";

import FhLayout from "../../../components/fh/FhLayout";
import ICInput from "../../../components/initialContact/Input";

const Add: FC<any> = ({ setAddNew, setDisabled, disabled, targetValue }) => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [state, setState] = useState<Data>({
    fhCasePlanId: 0,
    date: "",
    regarding: "",
    emotional: "",
    spiritual: "",
    mental: "",
    physical: "",
    fhFileDetailsId: Number(id) | 0,
  });
  const submitHandler = (e: FormEvent) => {
    e.preventDefault();

    const form = e.currentTarget as HTMLFormElement;
    const formData: Data = {
      fhCasePlanId: state.fhCasePlanId,
      date: form.date.value,
      regarding: form.regarding.value,
      emotional: form.emotional.value,
      spiritual: form.spiritual.value,
      mental: form.mental.value,
      physical: form.physical.value,
      fhFileDetailsId: state.fhFileDetailsId,
    };
    console.log("click", formData);
    saveCasePlan(formData).then(() => {
      navigate(`../casePlan/${id}`);
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
        {/* {disabled && (
        <Box
          sx={{
            display: "flex",
            justifyContent: "flex-end",
          }}
        >
          <EditIcon
            setDisabled={setDisabled}
            setAddNew={setAddNew}
            casePlanId={data.fhCasePlanId}
            targetValue={targetValue}
          />
        </Box>
      )} */}
        <Box
          sx={{
            display: "flex",
            flexWrap: "wrap",
            gap: "0 1rem",
            marginLeft: "10px",
          }}
        >
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              id="date"
              value="Date"
              type="date"
              required
              autofill={state.date}
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              id="regarding"
              value="Regarding"
              required
              autofill={state.regarding}
              readOnly={disabled}
            />
          </Box>
        </Box>
        <Typography
          sx={{ marginBottom: "0px", marginLeft: "15px" }}
          variant="body1"
        >
          Please indicate a plan for Emotional Wellness
        </Typography>
        <TextArea
          formLabelFlex="0 1 0"
          outlinedInputFlex="5.3 1 0"
          id="emotional"
          autofill={state.emotional}
          readOnly={disabled}
        />
        <Typography
          sx={{ marginBottom: "0px", marginLeft: "15px" }}
          variant="body1"
        >
          Please indicate a plan for Spiritual Wellness
        </Typography>
        <TextArea
          formLabelFlex="0 1 0"
          outlinedInputFlex="5.3 1 0"
          id="spiritual"
          autofill={state.spiritual}
          readOnly={disabled}
        />
        <Typography
          sx={{ marginBottom: "0px", marginLeft: "15px" }}
          variant="body1"
        >
          Please indicate a plan for Mental Wellness
        </Typography>
        <TextArea
          formLabelFlex="0 1 0"
          outlinedInputFlex="5.3 1 0"
          id="mental"
          autofill={state.mental}
          readOnly={disabled}
        />
        <Typography
          sx={{ marginBottom: "0px", marginLeft: "15px" }}
          variant="body1"
        >
          Please indicate a plan for Physical Wellness
        </Typography>
        <TextArea
          formLabelFlex="0 1 0"
          outlinedInputFlex="5.3 1 0"
          id="physical"
          autofill={state.physical}
          readOnly={disabled}
        />
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSNextButton disabled={disabled} />
        </Box>
      </Box>
    </FhLayout>
  );
};

export { Add as CasePlanAdd };
