// import { cleanState as cleanContactState } from "../features/cyfms/contact/slice";
// import { cleanState as cleanCounselorsState } from "../features/cyfms/counselors/slice";
// import { cleanState as cleanCriminalHistoryState } from "../features/cyfms/criminalHistory/slice";
// import { cleanState as cleanEducationAndEmploymentState } from "../features/cyfms/educationAndEmployment/slice";
// import { cleanState as cleanFamilyPhysiciansState } from "../features/cyfms/familyPhysicians/slice";
// import { cleanState as cleanHouseholdMembersState } from "../features/cyfms/householdMembers/slice";
// import { cleanState as cleanOtherInformationState } from "../features/cyfms/otherInformation/slice";
// import { cleanState as cleanRegisterState } from "../features/cyfms/register/slice";
// import { cleanState as cleanFileDetailsState } from "../features/initialContact/fileDetails/slice";
// import { cleanState as cleanIncidentReportState } from "../features/initialContact/incidentReport/slice";
// import { cleanState as cleanPatientCareInformationState } from "../features/initialContact/patientCareInformation/slice";
// import { cleanState as cleanPresentConcernsState } from "../features/initialContact/presentConcerns/slice";
// import { cleanState as cleanReferralInformationState } from "../features/initialContact/referralInformation/slice";
// import { uninitiate } from "../features/initiatorSlice";
// import { hideTabs, unhideTabs } from "../features/navBarSlice";
// import { setEdit, setOpen, setView } from "../features/popupSlice";
// import { useAppDispatch, useAppSelector } from "../library/hooks";
import { Box, IconButton, Modal } from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import React, { useEffect } from "react";
import type { ReactElement, ReactNode } from "react";
import { useNavigate, useNavigationType } from "react-router";
// import { setCalendarView } from "../features/calendar/appointments/slice";

/**
 * The Popup functional component.
 * @example
 * ```tsx
 * <Popup
 *   open={boolean}
 *   onClose={yourCloseHandler}
 *   ...
 * />
 * ```
 * @returns Popup component skeleton.
 */
const Popup = (props: { children: ReactNode | ReactNode[] }): ReactElement => {
  // const dispatch = useAppDispatch();
  const navigate = useNavigate();
  // const navigationAction = useNavigationType();
  // const state = useAppSelector((state) => state.popup);
  // const fileDetailsId = useAppSelector(
  //   (state) => state.icFileDetails.data.fileDetailsId
  // );
  // const participantId = useAppSelector(
  //   (state) => state.cyfmsRegister.data.participantId
  // );
  // const culturalProgramId = useAppSelector(
  //   (state) => state.cpa.data.culturalProgramId
  // );
  // const cgProviderId = useAppSelector((state) => state.cgCareProvider.data.id);

  // const calendarview = useAppSelector((state) => state.calendarAppointment);

  // useEffect(() => {
  //   if (navigationAction === "POP") {
  //     console.log("popup close");
  //     dispatch(setOpen(false));
  //     dispatch(hideTabs(null));
  //     dispatch(uninitiate(null));
  //     dispatch(setView(false));
  //   }
  // }, [navigationAction]);

  // const cleanStore = () => {
  // CYFMS
  // dispatch(cleanRegisterState(null));
  // dispatch(cleanContactState(null));
  // dispatch(cleanHouseholdMembersState(null));
  // dispatch(cleanEducationAndEmploymentState(null));
  // dispatch(cleanCriminalHistoryState(null));
  // dispatch(cleanFamilyPhysiciansState(null));
  // dispatch(cleanCounselorsState(null));
  // dispatch(cleanOtherInformationState(null));
  // Initial Contact
  // dispatch(cleanFileDetailsState(null));
  // dispatch(cleanReferralInformationState(null));
  // dispatch(cleanIncidentReportState(null));
  // dispatch(cleanPresentConcernsState(null));
  // dispatch(cleanPatientCareInformationState(null));
  // };

  return (
    <Modal
      open={/*state.open*/ false}
      onClose={(_event, reason) => {
        switch (reason) {
          case "backdropClick":
            return;
          case "escapeKeyDown":
          // dispatch(setOpen(false));
          // dispatch(setEdit(false));
          // dispatch(uninitiate(null));
          // dispatch(hideTabs(null));
          // cleanStore();
        }
      }}
    >
      <Box
        sx={{
          position: "relative",
          left: "50%",
          top: "50%",
          transform: "translate(-50%, -50%)",
          maxHeight: 500,
          maxWidth: 1000,
          bgcolor: "background.paper",
          border: "5px solid black",
          boxShadow: 24,
          overflowY: "auto",
        }}
      >
        <IconButton
          color="primary"
          aria-label="Close the popup box."
          onClick={(e) => {
            // dispatch(setOpen(false));
            // dispatch(setEdit(false));
            // dispatch(uninitiate(null));
            // dispatch(hideTabs(null));
            // dispatch(setCalendarView(false));
            // if (!state.view) {
            //   cleanStore();
            // }
            // if (calendarview.calendar) {
            //   navigate("/calendar");
            // } else {
            //   if (
            //     fileDetailsId !== 0 ||
            //     participantId !== 0 ||
            //     culturalProgramId !== 0 ||
            //     cgProviderId !== 0
            //   ) {
            //     navigate("./view");
            //   }
            //   if (
            //     fileDetailsId === 0 &&
            //     participantId === 0 &&
            //     culturalProgramId === 0 &&
            //     cgProviderId === 0
            //   ) {
            //     navigate("./");
            //   }
            // }
          }}
          sx={{ position: "absolute", right: 0 }}
        >
          <CloseIcon />
        </IconButton>
        {props.children}
      </Box>
    </Modal>
  );
};

export default Popup;
