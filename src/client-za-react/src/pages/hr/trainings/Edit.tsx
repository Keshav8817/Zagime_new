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
import { saveTraining, readTraining } from "./service";
import EditIcon from "../../../components/hr/trainings/EditIcon";
import { getTrainingsStatusCodetable } from "../../../services/codetableService";
import ICInput from "../../../components/initialContact/Input";
/**
 * *HR* aka *Human Resources* module. \
 * `AddTrainingPage` is used add training.
 */
const Edit: FC = () => {
  const navigate = useNavigate();
  const [trainingStatus, setTrainingStatus] = useState<any>([]);
  const { childId, id } = useParams();
  const [disabled, setDisabled] = useState(true);

  const [state, setState] = useState<Data>({
    staffId: Number(id) | 0,
    trainingId: Number(childId),
    trainingName: "",
    status: "",
    dateOfTraining: "",
    expiryDate: "",
    notes: "",
  });
  useEffect(() => {
    readTraining(Number(childId)).then(({ data }) => {
      setState(data);
    });
  }, []);
  useEffect(() => {
    getTrainingsStatusCodetable().then((response) => {
      setTrainingStatus(response.data.valuesMap);
    });
  }, []);
  const submitHandler: FormEventHandler<HTMLFormElement> = (event) => {
    event.preventDefault();
    const form = event.currentTarget;
    const formData: Data = {
      staffId: Number(id),
      trainingId: Number(childId),
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
        {" "}
        {disabled && (
          <Box
            sx={{
              display: "flex",
              justifyContent: "flex-end",
            }}
          >
            <EditIcon setDisabled={setDisabled} traningId={id} />
            {/* <EditIcon
          setDisabled={setDisabled}
          setAddNew={setAddNew}
          backgroundCheckId={data.backgrounCheckId}
          targetValue={state.data.staffId}
        /> */}
          </Box>
        )}
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
              disabled={disabled}
              optionsList={Object.values(trainingStatus).map(
                (status: any) => status.en
              )}
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              id="dateOfTraining"
              value="Date of Training"
              type="date"
              autofill={state.dateOfTraining}
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
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
          <CYFSWMSNextButton disabled={disabled} />
        </Box>
      </Box>
    </HrLayout>
  );
};

export { Edit as TrainingEdit };
