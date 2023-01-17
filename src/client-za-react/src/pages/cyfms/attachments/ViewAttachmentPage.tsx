import Input from "../../../components/Input";
import CyfmsLayout from "../../../components/cyfms/CYFMSLayout";
import EditIcon from "../../../components/cyfms/attachments/EditIcon";
import AttachmentsContext from "../../../contexts/AttachmentsContext";
import axiosInstance from "../../../library/axiosInstance";
import { Box, Link } from "@mui/material";
import React, { useContext, useEffect, useState } from "react";
import type { Attachment } from "./attachmentsDatatypes";
import type { FC } from "react";

/**
 * `CYFMS` aka `Child, Youth, and Family Management Services` module.
 * Sub page: `Attachments`.
 * Sub sub page: `View`.
 * Form to view document information selected from attachments.
 */
const ViewAttachmentPage: FC = () => {
  const context = useContext(AttachmentsContext);
  const [actualAttachment, setActualAttachment] = useState<Attachment>();

  /** Download the attachment */
  useEffect(() => {
    axiosInstance
      .get(
        `participantservice/attachments/read_one/${
          (context.attachment as Attachment).participantAttachmentId
        }`,
        {
          headers: {
            authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
          },
        }
      )
      .then((response) => {
        setActualAttachment(response.data);
      });
  }, []);

  return (
    <CyfmsLayout>
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
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              id="attachmentName"
              value="Name"
              autofill={(context.attachment as Attachment).name}
              disabled={true}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              id="attachmentType"
              value="Type"
              autofill={(context.attachment as Attachment).type}
              disabled={true}
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            Download file:{" "}
            <Link
              download={true}
              href={`data:${(actualAttachment as any)?.attachmentType};base64,${
                (actualAttachment as any)?.image
              }`}
              rel="noreferrer noopener"
            >
              {(context.attachment as Attachment).participantImageName}
            </Link>
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
        </Box>
        <Box />
      </Box>
    </CyfmsLayout>
  );
};

export default ViewAttachmentPage;
