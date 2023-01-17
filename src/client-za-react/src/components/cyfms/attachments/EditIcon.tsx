import AttachmentsContext from "../../../contexts/AttachmentsContext";
import axiosInstance from "../../../library/axiosInstance";
import { Box, Button, Modal } from "@mui/material";
import React, { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import type { Attachment } from "../../../pages/cyfms/attachments/attachmentsDatatypes";
import type { FC } from "react";

/**
 * `EditIcon` is used to show edit options on `/edit` \
 * page of an `/attachment` of `CYFMS` aka \
 * `Child, Youth, Family, and Management Services` module.
 */
const EditIcon: FC = () => {
  const navigate = useNavigate();
  const context = useContext(AttachmentsContext);
  const [openModel, setOpenModel] = useState(false);

  const handleDelete = () => {
    axiosInstance
      .delete(
        `participantservice/attachments/remove_one/${
          (context.attachment as Attachment).participantAttachmentId
        }`,
        {
          headers: {
            authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
          },
        }
      )
      .then((response) => {
        setOpenModel(false);
        navigate(
          `/cyfms/attachments/${
            (context.attachment as Attachment).participantId
          }`
        );
      });
  };

  return (
    <>
      <div>
        <div className="self-center  flex gap-2">
          <Button
            variant="contained"
            sx={{ backgroundColor: "#f5aa8a" }}
            onClick={() =>
              navigate(
                `/cyfms/attachments/edit/${
                  (context.attachment as Attachment).participantId
                }/${(context.attachment as Attachment).participantAttachmentId}`
              )
            }
          >
            Edit
          </Button>
          <Button
            variant="contained"
            sx={{ backgroundColor: "#f5aa8a" }}
            onClick={() => setOpenModel(true)}
          >
            Delete
          </Button>
          <Button
            variant="contained"
            sx={{ backgroundColor: "#f5aa8a" }}
            onClick={() =>
              navigate(
                `/cyfms/attachments/${
                  (context.attachment as Attachment).participantId
                }`
              )
            }
          >
            Close
          </Button>
        </div>
      </div>
      <Modal
        open={openModel}
        onClose={(event, reason) => setOpenModel(false)}
        aria-labelledby="parent-modal-title"
        aria-describedby="parent-modal-description"
      >
        <Box
          sx={{
            position: "absolute" as "absolute",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            width: 400,
            bgcolor: "background.paper",
            boxShadow: 24,
            pt: 2,
            px: 4,
            pb: 3,
            paddingLeft: "2%",
            display: "flex",
            justifyContent: "center",
            flexDirection: "column",
            textAlign: "center",
          }}
        >
          <p id="parent-modal-description">
            Are you sure you want to delete this record?
          </p>
          <Box sx={{ display: "flex", justifyContent: "center" }}>
            <Button onClick={() => handleDelete()}>Yes</Button>
            <Button onClick={() => setOpenModel(false)}>No</Button>
          </Box>
        </Box>
      </Modal>
    </>
  );
};

export default EditIcon;
