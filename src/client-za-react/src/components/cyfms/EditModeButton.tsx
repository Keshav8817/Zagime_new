import { Box, Button, Modal } from "@mui/material";
import React, { FC, useContext } from "react";
import CloseIcon from "@mui/icons-material/Close";
import { useNavigate, useParams } from "react-router";
import { removeParticipant } from "../../pages/cyfms/register/registerService";
import { removeParticipantContact } from "../../pages/cyfms/contact/contactService";
import { removeAllHouseHoldMember } from "../../pages/cyfms/householdMembers/householdMembersService";
import { removeEducationAndEmployment } from "../../pages/cyfms/educationAndEmployment/educationAndEmploymentService";
import { removeFamilyPhysician } from "../../pages/cyfms/familyPhysicians/familyPhysiciansService";
import { removeCounselorCFSWorker } from "../../pages/cyfms/counselors/counselorsService";
import { removeCriminalHistory } from "../../pages/cyfms/criminalHistory/criminalHistoryService";
import { removeParticipantOtherInformation } from "../../pages/cyfms/otherInformation/otherInformationService";

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
  const [openModel, setOpenModel] = React.useState(false);
  const navigate = useNavigate();
  const { id } = useParams();
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
    if (props.module === "register") {
      removeParticipant(Number(id)).then(() => {
        navigate(`/cyfms/${id}`);
      });
    } else if (props.module === "contact") {
      removeParticipantContact(props.id).then(() => {
        navigate(`/cyfms/household_members/${id}`);
      });
    } else if (props.module === "household_members") {
      removeAllHouseHoldMember(props.id).then(() => {
        setOpenModel(false);
        console.log("hopuse: ", props.id);
        navigate(`/cyfms/education_and_employment/${id}`);
      });
    } else if (props.module === "education_and_employment") {
      removeEducationAndEmployment(props.id).then(() => {
        navigate(`/cyfms/criminal_history/${id}`);
      });
    } else if (props.module === "criminal_history") {
      removeCriminalHistory(Number(props.id)).then(() => {
        navigate(`/cyfms/family_physicians/${id}`);
      });
    } else if (props.module === "family_physicians") {
      removeFamilyPhysician(props.id).then(() => {
        setOpenModel(false);

        navigate(`/cyfms/counselors/${id}`);
      });
    } else if (props.module === "counselors") {
      removeCounselorCFSWorker(props.id).then(() => {
        setOpenModel(false);

        navigate(`/cyfms/other_information/${id}`);
      });
    } else if (props.module === "other_information") {
      removeParticipantOtherInformation(props.id).then(() => {
        setOpenModel(false);

        navigate(`/cyfms/register/${id}`);
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
