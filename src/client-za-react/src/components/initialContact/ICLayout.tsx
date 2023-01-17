import NavBar from "../NavBar";
import { Box, Button, Modal } from "@mui/material";
import AuthLayout from "../auth/layout/AuthLayout";
import React, { useEffect, useState } from "react";
import type { FC, PropsWithChildren } from "react";

import { useNavigate, useParams } from "react-router-dom";
import CloseIcon from "@mui/icons-material/Close";

import IcHeader from "./IcHeader";
import { getEditButton } from "../../pages/ic/FileDetails/service";
/**
 * *IC* aka *Initial Contact* module. \
 * `IcLayout` is layout of *IC* modules' pages.
 * @example
 * ```jsx
 * <IcLayout>...</IcLayout>
 * // OR
 * <IcLayout children={} />
 * ```
 */

const IcLayout: FC<PropsWithChildren> = (props) => {
  const [openModel, setOpenModel] = useState(false);
  const navigate = useNavigate();
  const URL = `${process.env.REACT_APP_REST_API || "http://localhost:3000"}`;
  const { id } = useParams();
  const handleDelete = () => {
    setOpenModel(false);
    navigate("/initial_contact");
  };
  const handleOpenModal = () => {
    setOpenModel(true);
  };
  const handleCloseModel = () => {
    setOpenModel(false);
  };
  // useEffect(() => {
  //   if (Number(localStorage.getItem("filedetailsId")) !== 0) {
  //     getEditButton().then((res) => {
  //       console.log(res.data.editTrue);
  //       setEdit(res.data.editFalse);
  //       setView(res.data.viewTrue);
  //     });
  //   }
  // }, []);

  const handleCloseButton = () => {
    if (Number(localStorage.getItem("filedetailsId")) !== 0) {
      getEditButton().then((res) => {
        console.log(res.data.editTrue);
      });
      navigate(
        `/initial_contact/file_details/${localStorage.getItem("filedetailsId")}`
      );
    } else {
      navigate("/initial_contact");
    }
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
  return (
    <>
      <AuthLayout>
        <div className="flex justify-between ...">
          <div>
            <IcHeader bannerTitle="Initial Contact and Referrals" />
          </div>
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
        <Box
          sx={{
            display: "flex",
            flexDirection: { xs: "column", md: "row" },
            gap: { xs: "1rem 0", md: undefined },
          }}
        >
          <Box sx={{ flex: "1 1 0", overflowY: "auto" }}>
            <NavBar
              tabs={[
                {
                  value: "File Details",
                  route: `../file_details/${id}`,
                },
                {
                  value: "Referral Information",
                  route: `../referral_information/${id}`,
                },
                {
                  value: "Incident Report",
                  route: `../incident_report/${id}`,
                },
                {
                  value: "Present Concerns",
                  route: `../present_concerns/${id}`,
                },
                {
                  value: "Patient Care Information",
                  route: `../patient_care_information/${id}`,
                },
                {
                  value: "Participants",
                  route: `../participants/${id}`,
                },
                {
                  value: "Contact Notes",
                  route: `../contact_notes/${id}`,
                },
                {
                  value: "Attachments",
                  route: `../attachments/${id}`,
                },
                {
                  value: "Appointments",
                  route: `../appointment/${id}`,
                },
                {
                  value: "Reminders",
                  route: `../reminder/${id}`,
                },
              ]}
            />
          </Box>
          <Box sx={{ flex: "4 1 0", px: "1rem", overflowY: "auto" }}>
            {props.children}
          </Box>
        </Box>
      </AuthLayout>
    </>
  );
};

export default IcLayout;
