import NavBar from "../NavBar";

import { Box, Button, Modal } from "@mui/material";
import React, { useState } from "react";
import type { FC, PropsWithChildren } from "react";
import AuthLayout from "../auth/layout/AuthLayout";
import { useNavigate, useParams } from "react-router-dom";
import CloseIcon from "@mui/icons-material/Close";
// import {
//   cleanState as cleanFile,
//   doGet,
//   doRemove,
//   editState,
//   setEditButton,
//   setViewButton,
// } from "../../features/familyHealing/fileDetails/slice";
// import { cleanState as cleanFhApproval } from "../../features/familyHealing/approval/slice";
// import { cleanState as cleanFhHistory } from "../../features/familyHealing/history/slice";
// import { cleanState as cleanFhNeedAddress } from "../../features/familyHealing/needAssessment/slice";
// import { cleanState as cleanFhProgress } from "../../features/familyHealing/progressReport/slice";
// import { cleanState as cleanFhSafety } from "../../features/familyHealing/saftyplans/slice";
// import { cleanState as cleanCasePlan } from "../../features/familyHealing/casePlan/slice";
// import { cleanState as cleanConcentForm } from "../../features/familyHealing/consentForm/slice";
// import { cleanState as cleanFhFileDetails } from "../../features/familyHealing/fileDetails/slice";
// import { cleanState as cleanFhReferral } from "../../features/familyHealing/referral/slice";
// import { hideTabs } from "../../features/navBarSlice";
import FhHeader from "./fhHeader";

/**
 * `CG` aka `Caregivers` module.
 * Sub page component: `CG Layout`.
 * @param props
 * @returns `ReactElement`
 */
const FhLayout: FC<PropsWithChildren> = (props) => {
  const navigate = useNavigate();

  const { id } = useParams();
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
  const handleOpenModal = () => {
    // setOpenModel(true);
  };
  const handleDelete = () => {
    // dispatch(doRemove(data.fileNo)).then(() => {
    //   cleanState();
    //   setOpenModel(false);
    //   navigate("/fh");
    // });
  };
  const handleCloseModel = () => {
    // setOpenModel(false);
  };

  // const handleCloseButton = () => {
  //   if (data.fileNo !== 0) {
  //     console.log("click close edit true");
  //     dispatch(setViewButton(true));
  //     dispatch(setEditButton(true));
  //     navigate("/fh/fileDetails");
  //   } else {
  //     navigate("/fh");
  //   }
  // };
  return (
    <AuthLayout>
      <div className="flex justify-between ...">
        <div>
          <FhHeader bannerTitle="Family Healing" />
        </div>
      </div>

      <Box
        sx={{
          display: "flex",
          flexDirection: { xs: "column", md: "row" },
          gap: { xs: "1rem 0", md: undefined },
        }}
      >
        <Box
          sx={{
            flex: "1 0 0",
            height: 600,
            overflowY: "auto",
          }}
        >
          <NavBar
            tabs={[
              { value: "File Details", route: `../fileDetails/${id}` },
              {
                value: "Referral",
                route: `../referral/${id}`,
              },
              { value: "History", route: `../history/${id}` },
              { value: "Approval", route: `../approval/${id}` },
              { value: "Needs Assessment", route: `../needsAssessment/${id}` },

              { value: "Case Plan", route: `../casePlan/${id}` },
              { value: "Safety Plan", route: `../SafetyPlan/${id}` },
              { value: "Consent Forms", route: `../consentForms/${id}` },
              { value: "Progress Reports", route: `../progressReports/${id}` },
            ]}
          />
        </Box>
        <Box sx={{ flex: "6 1 0", px: "1rem", overflowY: "auto" }}>
          {props.children}
        </Box>
      </Box>
    </AuthLayout>
  );
};

export default FhLayout;
