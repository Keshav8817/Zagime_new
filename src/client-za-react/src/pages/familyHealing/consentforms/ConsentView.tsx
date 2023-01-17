import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import Dropdown from "../../../components/Dropdown";
import Input from "../../../components/Input";

import { Box, Link, Button } from "@mui/material";
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

const ConsentView = () => {
  const [actualAttachment, setActualAttachment] = useState<Data>();
  const context = useContext(AttachmentsContext);
  const { id, childId } = useParams();
  const [FhConsenType, setFhConsenType] = useState<any>([]);
  /** Download the attachment */
  useEffect(() => {
    axiosInstance
      .get(`familyHealing/service/readConsentForms/${Number(childId)}`, {
        headers: {
          authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
        },
      })
      .then((response) => {
        setActualAttachment(response.data);
      });
  }, []);
  useEffect(() => {
    getFhConsenTypeAPI().then((response) => {
      setFhConsenType(response.data.valuesMap);
    });
  }, []);
  console.log(context.attachment);
  return (
    <FhLayout>
      <Box
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
        }}
      >
        <Box
          sx={{
            display: "flex",
            justifyContent: "flex-end",
          }}
        >
          <EditIcon />
        </Box>

        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              id="date"
              value="Date"
              type="date"
              required
              autofill={actualAttachment?.date}
              disabled={true}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Dropdown
              autofill={actualAttachment?.type}
              id="type"
              value="Type"
              optionsList={Object.values(FhConsenType).map(
                (status: any) => status.en
              )}
              required
              disabled={true}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            {" "}
            <ICInput
              id="pleaseSpecify"
              value="Please Specify"
              autofill={actualAttachment?.pleaseSpecify}
              disabled={true}
              // disabled={disableOtherType}
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            Download file:{" "}
            <Link
              download={true}
              href={`data:${actualAttachment?.fhAttachmentType};base64,${
                (actualAttachment as any)?.file
              }`}
              rel="noreferrer noopener"
            >
              {actualAttachment?.fhAttachmentName}
            </Link>
          </Box>
          {/* <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Checkbox
              id="removeProfilePicture"
              // disabled={state.disableData}
              icon={<DeleteIcon />}
              checkedIcon={<DeleteIcon color="error" />}
            />
          </Box> */}
        </Box>

        {/* <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSNextButton disabled={disabled} />
        </Box> */}
      </Box>
    </FhLayout>
  );
};

export default ConsentView;
