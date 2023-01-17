import AttachmentsContext from "../../../contexts/AttachmentsContext";
import axiosInstance from "../../../library/axiosInstance";
import { Box, Button, Modal } from "@mui/material";
import React, { useContext, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Attachment } from "../../../pages/cpa/attachments/AttachmentDataTypes";

/**
 * `EditIcon` is used to show edit options on `/edit` \
 * page of an `/attachment` of `CPA` aka \
 * `Cultural Program And Activity` module.
 */
const EditIcon: any = () => {
  const navigate = useNavigate();
  const { id, childId } = useParams();
  const context = useContext(AttachmentsContext);
  const [openModel, setOpenModel] = useState(false);

  const handleDelete = () => {
    axiosInstance
      .delete(
        `cpa/attachments/remove_one/${
          (context.attachment as Attachment).culturalProgAttachmentId
        }`,
        {
          headers: {
            authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
          },
        }
      )
      .then((response) => {
        setOpenModel(false);
        navigate(`/cpa/attachments/${id}`);
      });
  };

  return (
    <>
      <div>
        <div className="self-center  flex gap-2">
          <Button
            variant="contained"
            sx={{ backgroundColor: "#f5aa8a" }}
            onClick={() => navigate(`/cpa/attachments/edit/${id}/${childId}`)}
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
            onClick={() => navigate(`/cpa/attachments/${id}`)}
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
