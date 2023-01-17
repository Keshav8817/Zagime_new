import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";

import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";

import { onKeyDown } from "../../../library/app";

import { Box, Typography, Button } from "@mui/material";
import React, { SyntheticEvent, useEffect, useState } from "react";
import type { FC, FormEvent, ChangeEventHandler } from "react";
import DeleteIcon from "@mui/icons-material/Delete";

import FileInput from "../../../components/FileInput";
import Checkbox from "../../../components/Checkbox";
import { Data } from "./ConsentDatatype";
import { useNavigate, useParams } from "react-router-dom";
import FhLayout from "../../../components/fh/FhLayout";
// import { saveConsentForms } from "./ConsentService";
import { getFhConsenTypeAPI } from "../../../services/codetableService";
import ICInput from "../../../components/initialContact/Input";
import axiosInstance from "../../../library/axiosInstance";
import Dropdown from "../../../components/Dropdown";

const ConsentAdd: FC<any> = ({
  setAddNew,
  setDisabled,
  disabled,
  targetValue,
}) => {
  const navigate = useNavigate();
  const [FhConsenType, setFhConsenType] = useState<any>([]);
  const { id } = useParams();
  const [fileName, setFileName] = useState<string>("");
  const [state, setState] = useState<Data>({
    fhConsentFormsId: 0,
    date: "",
    type: "",
    pleaseSpecify: "",
    file: "",
    fhFileDetailsId: Number(id) | 0,
    fhAttachmentType: "",
    fhAttachmentName: "",
  });
  const [disableOtherType, setDisableOtherType] = useState<boolean>(false);
  useEffect(() => {
    getFhConsenTypeAPI().then((response) => {
      setFhConsenType(response.data.valuesMap);
      setDisableOtherType(true);
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
        onChange={(e: SyntheticEvent<HTMLFormElement>) => {
          e.preventDefault();
          const form: any = e.currentTarget;
          if (form.type.value === "Other") {
            setDisableOtherType(false);
          } else {
            form.pleaseSpecify.value = "";
            setDisableOtherType(true);
          }
        }}
        onSubmit={(event: any) => submitHandler(event)}
        onKeyDown={onKeyDown}
      >
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
              disabled={disableOtherType}
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
          <CYFSWMSNextButton disabled={disabled} />
        </Box>
      </Box>
    </FhLayout>
  );
};

export default ConsentAdd;
