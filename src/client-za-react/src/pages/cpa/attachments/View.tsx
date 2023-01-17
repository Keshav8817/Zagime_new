import Input from "../../../components/Input";

import AttachmentsContext from "../../../contexts/AttachmentsContext";
import axiosInstance from "../../../library/axiosInstance";
import { Box, Link } from "@mui/material";
import React, { useContext, useEffect, useState } from "react";
import type { FC } from "react";
import { Attachment } from "./AttachmentDataTypes";
import CpaLayout from "../../../components/cpa/CPALayout";
import EditIcon from "../../../components/cpa/attachments/EditIcon";

/**
 * `CPA` aka `Cultural Program And Activity` module.
 * Sub page: `Attachments`.
 * Sub sub page: `View`.
 * Form to view document information selected from attachments.
 */
const View: FC = () => {
  const context = useContext(AttachmentsContext);
  const [actualAttachment, setActualAttachment] = useState<any>({});

  /** Download the attachment */
  useEffect(() => {
    axiosInstance
      .get(
        `cpa/attachments/read_one/${
          (context.attachment as Attachment).culturalProgAttachmentId
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
    <CpaLayout>
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
              href={`data:${(actualAttachment as any).attachmentType};base64,${
                (actualAttachment as any).file
              }`}
              rel="noreferrer noopener"
            >
              {(context.attachment as Attachment).culturalAttachmentName}
            </Link>
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
        </Box>
        <Box />
      </Box>
    </CpaLayout>
  );
};

export default View;
