import AttachmentsContext from "../../../contexts/AttachmentsContext";

import { Box, Button, Modal } from "@mui/material";
import React, { useContext } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { FC, MouseEventHandler } from "react";
import { deleteICAttachments } from "../../../pages/ic/attachments/service";

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

/**
 * `EditIcon` is used to show edit options on `/edit` page \
 * of an `/attachment` of `IC` aka `Initial Contact` module.
 * @returns `ReactElement`
 */
const EditIcon: any = () => {
  const [openModel, setOpenModel] = React.useState(false);
  const navigate = useNavigate();
  const { id, childId } = useParams();
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  // const openDropDown = Boolean(anchorEl);

  // const handleClick = (event: React.MouseEvent<HTMLElement>) => {
  //   setAnchorEl(event.currentTarget);
  // };

  // Close MoreHorIcon Popup
  const handleCloseDropDown = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(null);
  };
  const handleCloseModel = () => {
    setOpenModel(false);
  };

  const handleEdit: MouseEventHandler<HTMLElement> = (event) => {
    handleCloseDropDown(event);
    navigate(`../attachments/edit/${id}/${childId}`);
  };

  const handleDelete: MouseEventHandler<HTMLElement> = (event) => {
    event.preventDefault();
    deleteICAttachments(Number(childId)).then(() => {
      navigate(`../attachments/${id}`);
    });
  };

  const handleClose: MouseEventHandler<HTMLElement> = (event) => {
    event.preventDefault();
    navigate(`../attachments/${id}`);
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
            onClick={handleEdit}
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
            onClick={handleClose}
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
