import { Box, Button, IconButton, Modal, Menu, MenuItem } from "@mui/material";
import React from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import type { FC } from "react";
import { doRemove } from "../../../pages/familyHealing/consentforms/ConsentService";

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

const EditIcon: FC<any> = ({ setDisabled, setAddNew, Id, targetValue }) => {
  //const dispatch = useAppDispatch();
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const [openModel, setOpenModel] = React.useState(false);
  const openDropDown = Boolean(anchorEl);
  const { id, childId } = useParams();
  const navigate = useNavigate();
  const handleClick = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };

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
    navigate(`../consentForms/${id}`);
  };

  const handleDelete = () => {
    doRemove(Number(childId)).then(() => {
      setOpenModel(false);
      navigate(`../consentForms/${id}`);
    });

    setAddNew(false);
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
              navigate(`../consentForms/edit/${id}/${childId}`);
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
