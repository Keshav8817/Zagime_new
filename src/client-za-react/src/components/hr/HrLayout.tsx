import NavBar from "../NavBar";
import Header from "../Header";
import { Box, Button, Modal } from "@mui/material";
import React, { useState } from "react";
import type { FC, PropsWithChildren } from "react";
import AuthLayout from "../auth/layout/AuthLayout";
import CloseIcon from "@mui/icons-material/Close";
import { useNavigate, useParams } from "react-router-dom";
import HrHeader from "./HrHeader";

/**
 * `CG` aka `Caregivers` module.
 * Sub page component: `CG Layout`.
 * @param props
 * @returns `ReactElement`
 */
const CgLayout: FC<PropsWithChildren> = (props) => {
  const [openModel, setOpenModel] = useState(false);
  const navigate = useNavigate();
  const { id } = useParams();
  const cleanState = () => {};
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
    setOpenModel(true);
  };
  const handleDelete = () => {
    // setOpenModel = () => {
    //   setOpenModel(false);
    //   navigate("/staff");
    // };
  };
  const handleCloseModel = () => {
    setOpenModel(false);
  };
  const handleCloseButton = () => {
    // if (Number(localStorage.getItem("staffId")) !== 0) {
    //   getEditButton().then((res) => {
    //     console.log(res.data.editTrue);
    //   });
    //   navigate(`/staff/${localStorage.getItem("staffId")}`);
    // } else {
    //   navigate("/staff");
    // }
  };
  return (
    <AuthLayout>
      <div className="flex justify-between ...">
        <div>
          <HrHeader bannerTitle="Staff" />
        </div>

        {window.location.href !== "http://localhost:3000/hr/trainings" &&
          window.location.href !==
            "http://localhost:3000/hr/goals&objectives" &&
          window.location.href !== "http://localhost:3000/hr/backgroundcheck" &&
          window.location.href !== "http://localhost:3000/hr/trainings/view" &&
          window.location.href !== "http://localhost:3000/hr/trainings/add" &&
          window.location.href !==
            "http://localhost:3000/hr/attachments/edit" &&
          window.location.href !== "http://localhost:3000/hr/attachments" &&
          window.location.href !==
            "http://localhost:3000/hr/attachments/view" &&
          window.location.href !==
            "http://localhost:3000/hr/attachments/add" && (
            <div className="self-center  flex gap-2">
              {" "}
              {0 ? (
                <>
                  <Button
                    variant="contained"
                    onClick={() => {
                      // dispatch(setEditButton(false));
                    }}
                  >
                    Edit
                  </Button>
                  <Button variant="contained" onClick={handleOpenModal}>
                    Delete
                  </Button>
                  <Button
                    variant="contained"
                    onClick={() => {
                      // dispatch(cleanFile(null));
                      // dispatch(setEditButton(false));
                      navigate("/hr");
                    }}
                  >
                    Close
                  </Button>
                </>
              ) : (
                <>
                  {" "}
                  <Button>
                    {/* <CloseIcon onClick={handleCloseButton} /> */}
                  </Button>
                </>
              )}
            </div>
          )}
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
        <Box
          sx={{
            flex: "1 0 0",
            height: 600,
            overflowY: "auto",
          }}
        >
          <NavBar
            tabs={[
              { value: "Staff", route: `../staff/${id}` },
              {
                value: "Contact Information",
                route: `../contact_information/${id}`,
              },
              { value: "Job & Banking Details", route: `../job&banking/${id}` },
              {
                value: "Medical & Emergency",
                route: `../medical&emergency/${id}`,
              },
              { value: "Inventory", route: `../inventory/${id}` },

              { value: "Training", route: `../trainings/${id}` },
              {
                value: "Goals & Objectives",
                route: `../goals&objectives/${id}`,
              },
              { value: "Background Check", route: `../backgroundcheck/${id}` },
              { value: "Attachments", route: `../attachments/${id}` },
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

export default CgLayout;
