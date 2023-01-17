import EditIcon from "../../../components/initialContact/attachments/EditIcon";
import IcLayout from "../../../components/initialContact/ICLayout";
import Input from "../../../components/Input";
import AttachmentsContext from "../../../contexts/AttachmentsContext";
import { Box, Link } from "@mui/material";
import React, { useContext, useEffect, useState } from "react";
import type { FC } from "react";
import { useParams } from "react-router";
import { getICAttachments } from "./service";
import { Attachment } from "./AttachmentDataTypes";
import ICInput from "../../../components/initialContact/Input";
import axiosInstance from "../../../library/axiosInstance";

/**
 * `IC` aka `Initial Contact` module.
 * Sub page: `Attachments`.
 * Sub sub page: `View`.
 * Form to view document information selected from attachments.
//  * @returns `ReactElement`
 */
const View: FC = () => {
  const { id } = useParams();
  const context = useContext(AttachmentsContext);
  const [actualAttachment, setActualAttachment] = useState<any>({
    // icAttachmentType: "",
    // icAttachmentName: "",
  });
  // const [state, setState] = useState<Data>({
  //   fileDetailsId: Number(id),
  //   icAttachmentId: Number(childId),
  //   icAttachmentName: "",
  //   name: "",
  //   type: "",
  // });

  /** Download the attachment */
  useEffect(() => {
    axiosInstance
      .get(
        `initialcontactservice/attachments/read_one/${
          (context.attachment as Attachment).icAttachmentId
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
    //   getICAttachments(Number(childId)).then(({ data }) => {
    //     setState(data);
    //   });
  }, []);

  return (
    <IcLayout>
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
            <ICInput
              id="attachmentName"
              value="Name"
              autofill={(context.attachment as Attachment).name}
              disabled={true}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
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
              href={`data:${
                (actualAttachment as any).icAttachmentType
              };base64,${(actualAttachment as any).file}`}
              rel="noreferrer noopener"
            >
              {(context.attachment as Attachment).icAttachmentName}

              {/* {actualAttachment.icAttachmentName} */}
            </Link>
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
        </Box>
        <Box />
      </Box>
    </IcLayout>
  );
};

export default View;
