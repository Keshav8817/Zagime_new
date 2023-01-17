import { Box, Button, Modal } from "@mui/material";
import React from "react";
import type { FC } from "react";
import { useNavigate, useParams } from "react-router";
import { deleteCPAParticipant } from "../../../pages/cpa/Participants/Service";
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
const ITEM_HEIGHT = 48;
export const openPopup = true;

const EditIcon: FC<any> = ({ setDisabled }) => {
  const { id, childId } = useParams();
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const [openModel, setOpenModel] = React.useState(false);
  const navigate = useNavigate();
  const handleCloseModel = () => {
    setOpenModel(false);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleDelete = () => {
    deleteCPAParticipant(Number(childId)).then(() => {
      navigate(`../participants/${id}`);
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
              navigate(`../participants/${id}`);
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
        <Box sx={{ ...style, width: 400, paddingLeft: "5%" }}>
          <p id="parent-modal-description">
            Are you sure you want to delete this record?
          </p>
          <Box paddingLeft={7}>
            <Button onClick={handleDelete}>Yes</Button>
            <Button onClick={handleCloseModel}>No</Button>
          </Box>
        </Box>
      </Modal>
    </div>
  );
};

export default EditIcon;
