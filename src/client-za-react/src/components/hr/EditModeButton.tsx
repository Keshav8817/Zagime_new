import { Box, Button, Modal } from "@mui/material";
import React, { useState } from "react";
import CloseIcon from "@mui/icons-material/Close";
import { useNavigate, useParams } from "react-router";
import { doRemove } from "../../pages/hr/staff/staffService";
import { Data } from "../../pages/hr/staff/staffDatatypes";
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
function EditModeButton(props: any) {
  const navigate = useNavigate();
  const { id } = useParams();
  const handleDeleteAPI = () => {
    doRemove(props.id).then(() => {
      navigate(props.path);
    });
  };
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const [openModel, setOpenModel] = React.useState(false);
  const [state, setState] = useState<Data>({
    staffId: Number(localStorage.getItem("staffId")) | 0,
    firstName: "",
    middleName: "",
    lastName: "",
    dateOfBirth: "",
    department: "",
    status: "",
    workLocation: "",
    supervisor: "",
    employeeId: "",
  });
  const handleDeleteMessage = () => {
    setOpenModel(true);
  };
  const handleCloseModel = () => {
    setOpenModel(false);
  };

  return (
    <div>
      <Box
        sx={{
          display: "flex",
          justifyContent: "end",
          columnGap: 1,
        }}
      >
        {props.id !== 0 ? (
          <>
            <Button
              variant="contained"
              disabled={!props.disabled}
              onClick={() => {
                localStorage.setItem("editMode", "true");
                props.setDisabled(false);
              }}
            >
              Edit
            </Button>
            <Button variant="contained" onClick={handleDeleteMessage}>
              Delete
            </Button>
            <Button
              variant="contained"
              onClick={() => {
                // dispatch(cleanFileDetails(null));
                // dispatch(setEditButton(false));
                navigate(props.path);
              }}
            >
              Close
            </Button>
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
                  <Button onClick={handleDeleteAPI}>Yes</Button>
                  <Button onClick={handleCloseModel}>No</Button>
                </Box>
              </Box>
            </Modal>
          </>
        ) : (
          <>
            {" "}
            <Button>
              <CloseIcon
                onClick={() => {
                  navigate(props.path);
                }}
              />
            </Button>
          </>
        )}
      </Box>
    </div>
  );
}

export default EditModeButton;
