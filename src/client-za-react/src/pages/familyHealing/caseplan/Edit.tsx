import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import Dropdown from "../../../components/Dropdown";
import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";

import { Data } from "./CasePlanDataType";
import { onKeyDown } from "../../../library/app";

import { Box, Typography } from "@mui/material";
import React, { useState, useEffect } from "react";
import type { FC, FormEvent } from "react";
import { readCasePlan, saveCasePlan } from "./CasePlanService";
import { useNavigate, useParams } from "react-router-dom";
import FhLayout from "../../../components/fh/FhLayout";
import { startTrackValue } from "@testing-library/user-event/dist/types/document/trackValue";
import EditIcon from "../../../components/fh/fhCasePlan/EditIcon";
import ICInput from "../../../components/initialContact/Input";
import DateInput from "../../../components/initialContact/DateInput";

const Edit: FC<any> = ({ setAddNew, targetValue }) => {
  const navigate = useNavigate();
  const { id, childId } = useParams();
  const [disabled, setDisabled] = useState(true);
  const [state, setState] = useState<Data>({
    fhCasePlanId: Number(childId),
    date: "",
    regarding: "",
    emotional: "",
    spiritual: "",
    mental: "",
    physical: "",
    fhFileDetailsId: Number(id) | 0,
  });
  useEffect(() => {
    readCasePlan(Number(childId)).then(({ data }) => {
      setState(data);
    });
  }, []);
  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    console.log("click");
    const form = e.currentTarget as HTMLFormElement;
    const formData: Data = {
      fhCasePlanId: Number(childId),
      date: form.date.value,
      regarding: form.regarding.value,
      emotional: form.emotional.value,
      spiritual: form.spiritual.value,
      mental: form.mental.value,
      physical: form.physical.value,
      fhFileDetailsId: Number(id),
    };
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
              Id={state.fhCasePlanId}
              targetValue={targetValue}
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

export { Edit as CasePlanEdit };
