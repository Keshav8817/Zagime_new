import { Box, Button, Modal } from "@mui/material";
import React, { useState, useContext } from "react";
import CloseIcon from "@mui/icons-material/Close";
import { useNavigate, useParams } from "react-router";
import { doRemove as doRemoveFileDetails } from "../../pages/familyHealing/fileDetails/FileDetailsService";
import { Data } from "../../pages/familyHealing/fileDetails/fileDetailsDataType";
import { doRemove as doRemoveReferral } from "../../pages/familyHealing/referral/ReferralService";
import { doRemoveHistory } from "../../pages/familyHealing/history/HistoryService";
import { doRemoveApproval } from "../../pages/familyHealing/approval/ApprovalService";
import { ModuleDispatchContext } from "../../contexts/ModuleContext";

function EditModeButton(props: any) {
  const navigate = useNavigate();
  const { id } = useParams();
  const moduleDispatchContext = useContext(ModuleDispatchContext);

  const [openModel, setOpenModel] = React.useState(false);
  const handleDelete = () => {
    if (props.moduleName === "fileDetails") {
      doRemoveFileDetails(props.id).then(() => {
        navigate(`/fh/${id}`);
      });
    } else if (props.moduleName === "referral") {
      doRemoveReferral(props.id).then(() => {
        navigate(`/fh/history/${id}`);
      });
    } else if (props.moduleName === "History") {
      doRemoveHistory(props.id).then(() => {
        navigate(`/fh/approval/${id}`);
      });
    } else if (props.moduleName === "Approval") {
      doRemoveApproval(props.id).then(() => {
        navigate(`/fh/fileDetails/${id}`);
      });
    }
  };
  const handleCloseModel = () => {
    setOpenModel(false);
  };
  const handleDeleteMessage = () => {
    setOpenModel(true);
  };

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
  const [state, setState] = useState<Data>({
    fhFileDetailsId: Number(localStorage.getItem("fhFiledetailsId")) | 0,
    fileNo: undefined,
    clientName: "",
    startDate: "",
    department: "",
    endDate: "",
    status: "",
    community: "",
    caseworker: "",
    date: "",
    reason: "",
    notes: "",
  });
  const openPopup = true;

  return (
    <>
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
                sx={{ backgroundColor: "#f5aa8a" }}
                disabled={!props.disabled}
                onClick={() => {
                  localStorage.setItem("editMode", "true");
                  props.setDisabled(false);
                }}
              >
                Edit
              </Button>
              <Button
                variant="contained"
                onClick={handleDeleteMessage}
                sx={{ backgroundColor: "#f5aa8a" }}
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
    </>
  );
}

export default EditModeButton;
