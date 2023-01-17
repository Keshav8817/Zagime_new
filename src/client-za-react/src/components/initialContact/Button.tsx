import { Box, Button, Modal } from "@mui/material";
import React, { FC } from "react";
import CloseIcon from "@mui/icons-material/Close";
import { useNavigate, useParams } from "react-router";
import { remove } from "../../pages/ic/FileDetails/service";
import { removeReferralInfo } from "../../pages/ic/referralInformation/service";
import { removeIncidentReport } from "../../pages/ic/IncidentReport/service";
import { removePresentConcerns } from "../../pages/ic/presentConcern/service";
import { removePatientCareInfo } from "../../pages/ic/PatientCareInformation/service";

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
function EditModeButton(props: any) {
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const { id } = useParams();
  const [openModel, setOpenModel] = React.useState(false);
  const navigate = useNavigate();
  const openDropDown = Boolean(anchorEl);
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

  const handleDelete = () => {
    if (props.module === "file_details") {
      remove(props.id).then(() => {
        navigate("/initial_contact");
      });
    } else if (props.module === "referral_information") {
      removeReferralInfo(props.id).then(() => {
        navigate(`../incident_report/${id}`);
      });
    } else if (props.module === "incident_report") {
      removeIncidentReport(props.id).then(() => {
        navigate(`../present_concerns/${id}`);
      });
    } else if (props.module === "present_concerns") {
      removePresentConcerns(props.id).then(() => {
        navigate(`../patient_care_information/${id}`);
      });
    } else if (props.module === "patient_care_information") {
      removePatientCareInfo(props.id).then(() => {
        navigate(`../file_details/${id}`);
      });
    }
  };

  const handleDeleteMessage = () => {
    setOpenModel(true);
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
              sx={{ backgroundColor: "#f5aa8a" }}
              variant="contained"
              onClick={handleDeleteMessage}
            >
              Delete
            </Button>
            <Button
              variant="contained"
              sx={{ backgroundColor: "#f5aa8a" }}
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
                  <Button onClick={handleDelete}>Yes</Button>
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
