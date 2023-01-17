import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import Dropdown from "../../../components/Dropdown";
import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";

import { onKeyDown } from "../../../library/app";

import { Box, Typography, Button } from "@mui/material";
import React, { useContext, useEffect, useState } from "react";
import type { FC, ChangeEventHandler } from "react";
import DeleteIcon from "@mui/icons-material/Delete";

import FileInput from "../../../components/FileInput";
import Checkbox from "../../../components/Checkbox";
import { Data } from "./ConsentDatatype";
import { useNavigate, useParams } from "react-router-dom";
import FhLayout from "../../../components/fh/FhLayout";
import { readConsentForms } from "./ConsentService";
import { getFhConsenTypeAPI } from "../../../services/codetableService";
import EditIcon from "../../../components/fh/fhConsentForms/EditIcon";
import ICInput from "../../../components/initialContact/Input";
import axiosInstance from "../../../library/axiosInstance";
import AttachmentsContext from "../../../contexts/AttachmentsContext";

const ConsentEdit: FC<any> = ({ setAddNew, targetValue }) => {
  const { id, childId } = useParams();
  const navigate = useNavigate();
  const [fileName, setFileName] = useState<string>("");
  const [disabled, setDisabled] = useState(true);
  const [FhConsenType, setFhConsenType] = useState<any>([]);
  const [state, setState] = useState<Data>({
    fhConsentFormsId: Number(childId),
    date: "",
    type: "",
    pleaseSpecify: "",
    file: "",
    fhFileDetailsId: Number(id) | 0,
    fhAttachmentType: "",
    fhAttachmentName: "",
  });
  useEffect(() => {
    readConsentForms(Number(childId)).then(({ data }) => {
      setState(data);
    });
  }, []);
  useEffect(() => {
    getFhConsenTypeAPI().then((response) => {
      setFhConsenType(response.data.valuesMap);
    });
  }, []);

  const submitHandler: AppEventHandler<HTMLFormElement> = (event) => {
    event.preventDefault();
    const formData = new FormData();
    formData.append("fhConsentFormsId", String(state.fhConsentFormsId));
    formData.append("date", event.currentTarget.date.value);
    formData.append("type", event.currentTarget.type.value);
    formData.append("pleaseSpecify", event.currentTarget.pleaseSpecify.value);
    formData.append("fhFileDetailsId", String(state.fhFileDetailsId));

    formData.append("file", event.currentTarget.fileImage.files[0]);

    axiosInstance
      .put<Data>("/familyHealing/service/saveConsentForms", formData, {
        headers: {
          authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
        },
      })
      .then((response) => {
        navigate(`../consentForms/${id}`);
      });
  };
  const handleChange: ChangeEventHandler<HTMLInputElement> = (e) => {
    setFileName(e.currentTarget.value.replace(/^.*[\\/]/, ""));
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
        onSubmit={(event: any) => submitHandler(event)}
        onKeyDown={onKeyDown}
      >
        {disabled && (
          <Box
            sx={{
              display: "flex",
              justifyContent: "flex-end",
            }}
          >
            {/* <EditIcon
              setAddNew={setAddNew}
              Id={state.fhConsentFormsId}
              targetValue={targetValue}
              setDisabled={setDisabled}
            /> */}
          </Box>
        )}
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
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
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Dropdown
              autofill={state.type}
              id="type"
              value="Type"
              optionsList={Object.values(FhConsenType).map(
                (status: any) => status.en
              )}
              required
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            {" "}
            <ICInput
              id="pleaseSpecify"
              value="Please Specify"
              autofill={state.pleaseSpecify}
              // readOnly={disabled}
              // disabled={disableOtherType}
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <FileInput
              id="fileImage"
              value="Upload"
              autofill={state.file}
              onChange={handleChange}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            {/* <Checkbox
              id="removeProfilePicture"
              // disabled={state.disableData}
              icon={<DeleteIcon />}
              checkedIcon={<DeleteIcon color="error" />}
            /> */}
          </Box>
        </Box>
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSNextButton />
        </Box>
      </Box>
    </FhLayout>
  );
};

export default ConsentEdit;
