import EditIcon from "../../../components/hr/attachments/EditIcon";
import HrLayout from "../../../components/hr/HrLayout";
import Input from "../../../components/Input";
import AttachmentsContext from "../../../contexts/AttachmentsContext";
import { Box, Link } from "@mui/material";
import React, { useContext, useEffect, useState } from "react";
import type { FC } from "react";
import { useParams } from "react-router-dom";
import ICInput from "../../../components/initialContact/Input";
import { Attachment } from "./AttachmentsDatatypes";
import { getHrAttachments } from "./service";
import axiosInstance from "../../../library/axiosInstance";
/**
 * `CG` aka `Caregivers` module.
 * Sub page: `Attachments`.
 * Sub sub page: `View`.
 * Form to view document information selected from attachments.
 * @returns `ReactElement`
 */
const View: FC = () => {
  const context = useContext(AttachmentsContext);
  const { id, childId } = useParams();
  // const dispatch = useAppDispatch();
  // const attachment = useAppSelector(
  //   (state) => state.hrAttachments.data[context.selected]
  // );
  const [actualAttachment, setActualAttachment] = useState<any>({
    // attachmentType: "",
    // participantImageName: "",
  });
  // const [state, setState] = useState<Data>({
  //   staffId: Number(id),
  //   staffAttachmentId: Number(childId),
  //   staffAttachmentName: "",
  //   name: "",
  //   type: "",
  // });

  /** Download the attachment */
  useEffect(() => {
    axiosInstance
      .get(
        `staffservice/attachments/read_one/${
          (context.attachment as Attachment).staffAttachmentId
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
    // getHrAttachments(Number(childId)).then(({ data }) => {
    //   setState(data);
    // });
  }, []);

  return (
    <HrLayout>
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
                (actualAttachment as any).staffAttachmentType
              };base64,${(actualAttachment as any).staffAttachmentFile}`}
              rel="noreferrer noopener"
            >
              {(context.attachment as Attachment).staffAttachmentName}
            </Link>
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
        </Box>
        <Box />
      </Box>
    </HrLayout>
  );
};

export default View;
