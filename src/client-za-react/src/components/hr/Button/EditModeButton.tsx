import { Box, Button, Modal } from "@mui/material";
import React, { useState } from "react";
import CloseIcon from "@mui/icons-material/Close";
import { useNavigate, useParams } from "react-router";
import { doRemove } from "../../../pages/hr/staff/staffService";
import { doRemoveIC } from "../../../pages/hr/contactInformation/contactInformationServices";
import { doRemoveGoalsObjective } from "../../../pages/hr/jobAndBanking/jobAndBankingServices";
import { doRemoveMedicalEmergency } from "../../../pages/hr/medicalEmergency/medicalEmergencyService";
import { removeStaffInvemtory } from "../../../pages/hr/inventory/inventoryService";
import { Data } from "../../../pages/hr/staff/staffDatatypes";
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
  const { childId, id } = useParams();
  const handleDeleteAPI = () => {
    if (props.module === "staff") {
      doRemove(props.id).then(() => {
        navigate("/hr");
      });
    } else if (props.module === "jobBanking") {
      doRemoveGoalsObjective(props.id).then(() => {
        navigate(`/hr/medical&emergency/${id}`);
      });
    } else if (props.module === "contactInformation") {
      doRemoveIC(props.id).then(() => {
        setOpenModel(false);

        navigate(`/hr/job&banking/${id}`);
      });
    } else if (props.module === "medicalEmergency") {
      doRemoveMedicalEmergency(props.id).then(() => {
        navigate(`/hr/inventory/${id}`);
      });
    } else if (props.module === "inventory") {
      removeStaffInvemtory(Number(id)).then(() => {
        navigate(`/hr/trainings/${id}`);
      });
    }
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
              sx={{ backgroundColor: "#f5aa8a" }}
              onClick={() => {
                localStorage.setItem("editMode", "true");
                props.setDisabled(false);
              }}
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
                navigate(props.path);
              }}
            >
              Close
            </Button>
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
    </div>
  );
}

export default EditModeButton;
