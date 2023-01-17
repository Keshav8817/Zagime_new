import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import Input from "../../../components/Input";
import EditIcon from "../../../components/cyfms/attachments/EditIcon";
import CyfmsLayout from "../../../components/cyfms/CYFMSLayout";
import AttachmentsContext from "../../../contexts/AttachmentsContext";
import { ModuleContext } from "../../../contexts/ModuleContext";
import { onKeyDown } from "../../../library/app";
import axiosInstance from "../../../library/axiosInstance";
import { Box, Button } from "@mui/material";
import React, { useContext, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { Attachment } from "./attachmentsDatatypes";
import type { ChangeEventHandler, FC, FormEventHandler } from "react";

/**
 * `CYFMS` aka `Child, Youth, and Family Management Services` module.
 * Sub page: `Attachments`.
 * Sub sub page: `Edit`.
 * Form to edit document information selected from attachments.
 */
const EditAttachmentPage: FC = () => {
  const navigate = useNavigate();
  const { id, childId } = useParams();
  const context = useContext(AttachmentsContext);
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
        participantId: (context.attachment as Attachment).participantId,
        participantAttachmentId: (context.attachment as Attachment)
          .participantAttachmentId,
        name: e.currentTarget.attachmentName.value,
        type: e.currentTarget.attachmentType.value,
      } as Attachment)
    );
    if (e.currentTarget.attachment.files[0]) {
      attachment.append("file", e.currentTarget.attachment.files[0]);
    }
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
        <Box
          sx={{
            display: "flex",
            justifyContent: "flex-end",
          }}
        >
          <EditIcon />
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              id="attachmentName"
              value="Name"
              autofill={(context.attachment as Attachment).name}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              id="attachmentType"
              value="Type"
              autofill={(context.attachment as Attachment).type}
            />
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
              <Button variant="contained" component="label">
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

export default EditAttachmentPage;
