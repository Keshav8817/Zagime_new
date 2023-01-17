import Input from "../../../components/Input";
import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import CyfmsLayout from "../../../components/cyfms/CYFMSLayout";
import { onKeyDown } from "../../../library/app";
import axiosInstance from "../../../library/axiosInstance";
import { Box, Button } from "@mui/material";
import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { Attachment } from "./attachmentsDatatypes";
import type { ChangeEventHandler, FC, FormEventHandler } from "react";

/**
 * `CYFMS` aka `Child, Youth, and Family Management Services` module.
 * Sub page: `Attachments`.
 * Sub sub page: `Add`.
 * Form to submit/add one more document to attachments.
 */
const AddAttachmentPage: FC = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [participantId, setParticipantId] = useState<number>(0);
  const [fileName, setFileName] = useState<string>("");

  const handleChange: ChangeEventHandler<HTMLInputElement> = (e) => {
    setFileName(e.currentTarget.value.replace(/^.*[\\/]/, ""));
  };

  const submitHandler: FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault();
    const attachment = new FormData();
    attachment.append(
      "participantDto",
      JSON.stringify({
        participantId: Number(id),
        participantAttachmentId: 0,
        name: e.currentTarget.attachmentName.value,
        type: e.currentTarget.attachmentType.value,
      } as Attachment)
    );
    attachment.append("file", e.currentTarget.attachment.files[0]);
    axiosInstance
      .put("participantservice/attachments/save_one", attachment, {
        headers: {
          authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
          "Content-Type": "multipart/form-data",
        },
      })
      .then((response) => {
        navigate(`/cyfms/attachments/${id}`);
      });
  };

  return (
    <CyfmsLayout>
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
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input id="attachmentName" value="Name" />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input id="attachmentType" value="Type" />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Box
              sx={{
                display: "flex",
                gap: "0 1rem",
                alignItems: "center",
              }}
            >
              <Button
                variant="contained"
                component="label"
                sx={{ backgroundColor: "#ffa500" }}
              >
                Upload
                <input
                  hidden
                  name="attachment"
                  type="file"
                  onChange={handleChange}
                />
              </Button>
              {fileName}
            </Box>
          </Box>
        </Box>
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSNextButton />
        </Box>
      </Box>
    </CyfmsLayout>
  );
};

export default AddAttachmentPage;
