import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import Dropdown from "../../../components/Dropdown";
import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";

import { onKeyDown } from "../../../library/app";

import { Box } from "@mui/material";
import React, { useEffect, useState } from "react";
import type { FC, FormEvent } from "react";
import FhLayout from "../../../components/fh/FhLayout";

import { useNavigate, useParams } from "react-router";
import { Data } from "./ApprovalDataType";
import { readApproval, saveApproval } from "./ApprovalService";
import EditModeButton from "../../../components/fh/EditModeButton";
import ICInput from "../../../components/initialContact/Input";
import DateInput from "../../../components/initialContact/DateInput";

const Approval: FC<any> = () => {
  const navigate = useNavigate();
  const [disabled, setDisabled] = useState(false);
  const { id } = useParams();
  const [state, setState] = useState<Data>({
    fhApprovalId: 0,
    fhFileDetailsId: Number(id) | 0,
    submittedBy: "",
    submittedDate: "",
    approvedBy: "",
    approvedDate: "",
    comments: "",
  });

  useEffect(() => {
    readApproval(Number(id), state).then(({ data }) => {
      setState(data);
      if (data.fhApprovalId !== 0) {
        setDisabled(true);
      }
    });
  }, []);
  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const form = e.currentTarget as HTMLFormElement;
    const formData: Data = {
      fhApprovalId: state.fhApprovalId,
      fhFileDetailsId: state.fhFileDetailsId,
      submittedBy: form.submittedBy.value,
      submittedDate: form.submittedDate.value,
      approvedBy: form.approvedBy.value,
      approvedDate: form.approvedDate.value,
      comments: form.comments.value,
    };
    console.log(formData);
    saveApproval(formData).then((res) => {
      navigate(`../fileDetails/${res.data.fhFileDetailsId}`);
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
        <div>
          <>
            <EditModeButton
              id={state.fhApprovalId}
              disabled={disabled}
              setDisabled={setDisabled}
              fileDetailsPage={true}
              path={`/fh/fileDetails/${id}`}
              moduleName={"Approval"}
            />
          </>
        </div>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              id="submittedBy"
              value="Submitted By"
              autofill={state.submittedBy}
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <DateInput
              id="submittedDate"
              value="Submitted Date"
              type="date"
              autofill={state.submittedDate}
              readOnly={disabled}
            />
          </Box>
        </Box>

        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              id="approvedBy"
              value="Approved By"
              autofill={state.approvedBy}
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            {" "}
            <DateInput
              id="approvedDate"
              value="Approved Date"
              type="date"
              autofill={state.approvedDate}
              readOnly={disabled}
            />
          </Box>
        </Box>

        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5.3 1 0"
          id="comments"
          value="Approver's Comments"
          autofill={state.comments}
          readOnly={disabled}
        />

        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSNextButton disabled={disabled} />
        </Box>
      </Box>
    </FhLayout>
  );
};

export default Approval;
