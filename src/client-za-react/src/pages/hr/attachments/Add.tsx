import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import ICInput from "../../../components/initialContact/Input";
import Input from "../../../components/Input";
import HrLayout from "../../../components/hr/HrLayout";
import { onKeyDown } from "../../../library/app";
import { Box, Button } from "@mui/material";
import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { ChangeEventHandler, FC, FormEventHandler } from "react";
import { Attachment } from "./AttachmentsDatatypes";
import { postHrAttachments } from "./service";
import axiosInstance from "../../../library/axiosInstance";
/**
 * *CG* aka *Caregivers* module. \
 * Sub page: `Attachments`. \
 * Sub sub page: `Add`.
 * Form to submit/add one more document to attachments.
 */
const Add: FC = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [fileName, setFileName] = useState<string>("");
  // const [state, setState] = useState<Data>({
  //   staffId: Number(id),
  //   staffAttachmentId: 0,
  //   staffAttachmentName: "",
  //   name: "",
  //   type: "",
  // });

  const handleChange: ChangeEventHandler<HTMLInputElement> = (e) => {
    setFileName(e.currentTarget.value.replace(/^.*[\\/]/, ""));
  };

  const submitHandler: FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault();
    const attachment = new FormData();
    attachment.append(
      "staffDto",
      JSON.stringify({
        staffAttachmentId: 0,
        staffId: Number(id),
        name: e.currentTarget.attachmentName.value,
        type: e.currentTarget.attachmentType.value,
      } as Attachment)
    );
    attachment.append("file", e.currentTarget.attachment.files[0]);
    // postHrAttachments(attachment)
    axiosInstance
      .put("staffservice/attachments/save_one", attachment, {
        headers: {
          authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
          "Content-Type": "multipart/form-data",
        },
      })
      .then(() => {
        navigate(`../attachments/${id}`);
      });
  };

  return (
    <HrLayout>
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
            <ICInput id="attachmentName" value="Name" />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput id="attachmentType" value="Type" />
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
    </HrLayout>
  );
};

export default Add;
