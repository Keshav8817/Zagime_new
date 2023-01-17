import { Box, Button, Modal } from "@mui/material";
import React from "react";
import type { FC } from "react";
import { deleteICContactNotes } from "../../../pages/ic/contactNotes/service";
import { useNavigate, useParams } from "react-router";

const style = {
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
};
export const openPopup = true;

const EditIcon: FC<any> = ({ setDisabled, contactId }) => {
  const { id, childId } = useParams();
  const navigate = useNavigate();
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const [openModel, setOpenModel] = React.useState(false);

  // Close MoreHorIcon Popup
  const handleCloseDropDown = (event: React.MouseEvent<HTMLElement>) => {
    if (event.currentTarget.tabIndex === -1) {
      setOpenModel(true);
    }
    setAnchorEl(null);
  };

  const handleCloseModel = () => {
    setOpenModel(false);
  };

  const handleClose = () => {
    setAnchorEl(null);
    navigate(`../contact_notes/${id}`);
  };

  const handleDelete = () => {
    deleteICContactNotes(Number(childId)).then(() => {
      navigate(`../contact_notes/${id}`);
    });
  };

  const handleDeleteMessage = () => {
    setOpenModel(true);
  };

  return (
    <div>
      <div className="self-center  flex gap-2">
        <>
          <Button
            variant="contained"
            onClick={() => {
              setDisabled(false);
            }}
            sx={{ backgroundColor: "#f5aa8a" }}
          >
            Edit
          </Button>
          <Button
            variant="contained"
            sx={{ backgroundColor: "#f5aa8a" }}
            onClick={handleDeleteMessage}
          >
            Delete
          </Button>
          <Button
            variant="contained"
            sx={{ backgroundColor: "#f5aa8a" }}
            onClick={() => {
              handleClose();
            }}
          >
            Close
          </Button>
        </>
      </div>

      <Modal
        open={openModel}
        onClose={(event, reason) => {
          switch (reason) {
            case "backdropClick":
              return;
            case "escapeKeyDown":
              handleCloseModel();
          }
        }}
        aria-labelledby="parent-modal-title"
        aria-describedby="parent-modal-description"
      >
        <Box
          sx={{
            ...style,
            width: 400,
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
            <Button onClick={handleDelete}>Yes</Button>
            <Button onClick={handleCloseModel}>No</Button>
          </Box>
        </Box>
      </Modal>
    </div>
  );
};

export default EditIcon;
