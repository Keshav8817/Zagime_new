import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";
import HrLayout from "../../../components/hr/HrLayout";
import { onKeyDown } from "../../../library/app";
import { Box } from "@mui/material";
import React from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { FC, FormEventHandler } from "react";
import { useState, useEffect } from "react";
import Dropdown from "../../../components/Dropdown";
import { Data } from "./TrainingDatatypes";
import { saveTraining } from "./service";
import { getTrainingsStatusCodetable } from "../../../services/codetableService";
import ICInput from "../../../components/initialContact/Input";
/**
 * *HR* aka *Human Resources* module. \
 * `AddTrainingPage` is used add training.
 */
const Add: FC<any> = ({ disabled }) => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [trainingStatus, setTrainingStatus] = useState<any>([]);
  const [state, setState] = useState<Data>({
    staffId: Number(id),
    trainingId: 0,
    trainingName: "",
    status: "",
    dateOfTraining: "",
    expiryDate: "",
    notes: "",
  });

  useEffect(() => {
    getTrainingsStatusCodetable().then((response) => {
      setTrainingStatus(response.data.valuesMap);
    });
  }, []);
  const submitHandler: FormEventHandler<HTMLFormElement> = (event) => {
    event.preventDefault();
    const form = event.currentTarget;
    const formData: Data = {
      staffId: Number(id) | 0,
      trainingId: 0,
      trainingName: form.trainingName.value,
      status: form.status.value,
      dateOfTraining: form.dateOfTraining.value,
      expiryDate: form.expiryDate.value,
      notes: form.notes.value,
    };
    console.log(formData);
    saveTraining(formData).then(() => {
      navigate(`../trainings/${Number(id)}`);
    });
  };

  return (
    <HrLayout>
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
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              id="trainingName"
              value="Training Name"
              autofill={state.trainingName}
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Dropdown
              id="status"
              value="Status"
              autofill={state.status}
              optionsList={Object.values(trainingStatus).map(
                (status: any) => status.en
              )}
              readOnly={disabled}
              // optionsList={["list1, list2,list3"]}
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              id="dateOfTraining"
              value="Date of Training"
              type="date"
              autofill={state.dateOfTraining}
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              id="expiryDate"
              value="Expiry Date"
              type="date"
              autofill={state.expiryDate}
              readOnly={disabled}
            />
          </Box>
        </Box>
        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5.3 1 0"
          id="notes"
          value="Notes"
          autofill={state.notes}
          readOnly={disabled}
        />
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSNextButton />
        </Box>
      </Box>
    </HrLayout>
  );
};

export { Add as TrainingAdd };
