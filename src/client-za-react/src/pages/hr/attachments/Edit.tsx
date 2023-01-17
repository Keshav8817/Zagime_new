import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import Input from "../../../components/Input";
import HrLayout from "../../../components/hr/HrLayout";
import EditIcon from "../../../components/hr/attachments/EditIcon";
import AttachmentsContext from "../../../contexts/AttachmentsContext";
import { onKeyDown } from "../../../library/app";
import ICInput from "../../../components/initialContact/Input";

import { Box, Button } from "@mui/material";
import React, { useContext, useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { ChangeEventHandler, FC, FormEventHandler } from "react";
import { getHrAttachments, postHrAttachments } from "./service";
import { Attachment } from "./AttachmentsDatatypes";
import axiosInstance from "../../../library/axiosInstance";
/**
 * `CG` aka `Caregivers` module.
 * Sub page: `Attachments`.
 * Sub sub page: `Edit`.
 * Form to edit document information selected from attachments.
 * @returns `ReactElement`
 */
const Edit: FC = () => {
  const context = useContext(AttachmentsContext);
  const navigate = useNavigate();
  const [fileName, setFileName] = useState<string>("");
  const { id, childId } = useParams();
  // const [state, setState] = useState<Data>({
  //   staffId: Number(localStorage.getItem("staffId")),
  //   staffAttachmentId: 0,
  //   staffAttachmentName: "",
  //   name: "",
  //   type: "",
  // });

  const handleChange: ChangeEventHandler<HTMLInputElement> = (e) => {
    setFileName(e.currentTarget.value.replace(/^.*[\\/]/, ""));
  };
  // useEffect(() => {
  //   getHrAttachments(Number(childId)).then(({ data }) => {
  //     setState(data);
  //   });
  // }, []);

  const submitHandler: FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault();
    const attachment = new FormData();
    attachment.append(
      "staffDto",
      JSON.stringify({
        staffId: (context.attachment as Attachment).staffId,
        staffAttachmentId: (context.attachment as Attachment).staffAttachmentId,
        name: e.currentTarget.attachmentName.value,
        type: e.currentTarget.attachmentType.value,
      } as any)
    );
    // postHrAttachments(attachment).then(() => {
    //   navigate(`../attachments/${id}`);
    // });

    if (e.currentTarget.attachment.files[0]) {
      attachment.append("file", e.currentTarget.attachment.files[0]);
    }
    axiosInstance
      .put("staffservice/attachments/save_one", attachment, {
        headers: {
          authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
          "Content-Type": "multipart/form-data",
        },
      })
      .then((response) => {
        navigate(`../attachments/${id}`);
      });
    // dispatch(doPost(attachment))
    //   .unwrap()
    //   .then((data) => {
    //     navigate("../attachments");
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //   });
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
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
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
    </HrLayout>
  );
};

export default Edit;
