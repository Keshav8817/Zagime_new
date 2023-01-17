import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import Dropdown from "../../../components/Dropdown";
import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";

import { onKeyDown } from "../../../library/app";

import { Box, Typography } from "@mui/material";
import React, { useEffect, useState } from "react";
import type { FC, FormEvent } from "react";
import { readSafetyPlan, saveSafetyPlan } from "./SafetyPlanService";
import { Data } from "./SafetyPlanDataType";
import { useNavigate, useParams } from "react-router-dom";
import FhLayout from "../../../components/fh/FhLayout";
import EditIcon from "../../../components/fh/fhSafetyPlan/EditIcon";
import { readNeedAssessment } from "../needsAssessment/NeedAssessmentService";
import ICInput from "../../../components/initialContact/Input";
import DateInput from "../../../components/initialContact/DateInput";

const SafetyPlanEdit: FC<any> = ({ setAddNew, targetValue }) => {
  const { id, childId } = useParams();
  const navigate = useNavigate();
  const [disabled, setDisabled] = useState(true);

  const [state, setState] = useState<Data>({
    fhSafetyPlanId: Number(childId),
    date: "",
    signs: "",
    strategies: "",
    crisis: "",
    printName: "",
    participant: "",
    fhFileDetailsId: Number(id) | 0,
  });
  useEffect(() => {
    readSafetyPlan(Number(childId)).then(({ data }) => {
      setState(data);
    });
  }, []);
  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const form = e.currentTarget as HTMLFormElement;
    const formData: Data = {
      fhFileDetailsId: Number(id),
      fhSafetyPlanId: Number(childId),
      date: form.date.value,
      signs: form.signs.value,
      strategies: form.strategies.value,
      crisis: form.crisis.value,
      printName: form.printName.value,
      participant: form.participant.value,
    };
    saveSafetyPlan(formData).then(() => {
      navigate(`../SafetyPlan/${id}`);
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
              Id={state.fhSafetyPlanId}
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
              id="participant"
              value="Participant"
              required
              autofill={state.participant}
              readOnly={disabled}
            />
          </Box>
        </Box>
        <Typography
          sx={{ marginBottom: "0px", marginLeft: "12px" }}
          variant="body1"
        >
          Warning Signs
        </Typography>
        <TextArea
          formLabelFlex="0 1 0"
          outlinedInputFlex="5.3 1 0"
          id="signs"
          autofill={state.signs}
          readOnly={disabled}
        />
        <Typography
          sx={{ marginBottom: "0px", marginLeft: "12px" }}
          variant="body1"
        >
          Internal coping strategies
        </Typography>
        <TextArea
          formLabelFlex="0 1 0"
          outlinedInputFlex="5.3 1 0"
          id="strategies"
          autofill={state.strategies}
          readOnly={disabled}
        />
        <Typography
          sx={{ marginBottom: "0px", marginLeft: "12px" }}
          variant="body1"
        >
          People I can contact during a crisis
        </Typography>
        <TextArea
          formLabelFlex="0 1 0"
          outlinedInputFlex="5.3 1 0"
          id="crisis"
          autofill={state.crisis}
          readOnly={disabled}
        />
        <Box
          sx={{
            display: "flex",
            flexWrap: "wrap",
            gap: "0 1rem",
            marginLeft: "8px",
          }}
        >
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              id="printName"
              value="Print Name"
              autofill={state.printName}
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
        </Box>

        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSNextButton disabled={disabled} />
        </Box>
      </Box>
    </FhLayout>
  );
};

export default SafetyPlanEdit;
