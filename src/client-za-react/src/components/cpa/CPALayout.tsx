import Header from "../Header";
import NavBar from "../NavBar";
import AuthLayout from "../auth/layout/AuthLayout";
import { Box, Button, Modal } from "@mui/material";
import React, { useState } from "react";
import type { FC, PropsWithChildren } from "react";
//import { useAppDispatch, useAppSelector } from "../../library/hooks";
import { useNavigate, useParams } from "react-router-dom";

import CpaHeader from "./CpaHeader";

/**
 * *CPA* aka *Cultural Programs and Activities* module. \
 * `CpaLayout` is layout of *CPA* modules' pages.
 * @example
 * ```jsx
 * <CpaLayout>...</CpaLayout>
 * // OR
 * <CpaLayout children={} />
 * ```
 */
const CpaLayout: FC<PropsWithChildren> = (props) => {
  // const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const [openModel, setOpenModel] = useState(false);
  const { id } = useParams();
  const handleCloseModel = () => {
    setOpenModel(false);
  };
  // const data = useAppSelector((state) => state.cpa.data);
  // const edit = useAppSelector((state) => state.cpa);

  // const cleanState = () => {
  //   dispatch(cleanCPA(null));
  //   dispatch(cleanParticipant(null));
  //   dispatch(cleanAttachment(null));
  //   dispatch(hideTabs(true));
  // };
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
  // const handleDelete = () => {
  //   dispatch(doDelete(data.referenceId)).then(() => {
  //     cleanState();

  //     setOpenModel(false);
  //     navigate("/cpa");
  //   });
  // };

  const handleCloseButton = () => {
    // if (data.culturalProgramId !== 0) {
    //   // dispatch(setViewButton(true));
    //   // dispatch(setEditButton(true));
    //   navigate("/cpa/add_cpa");
    // } else {
    //   navigate("/cpa");
    // }
  };
  return (
    <AuthLayout>
      <div className="flex justify-between ...">
        <div>
          <CpaHeader bannerTitle="Cultural Programs and Activities" />
        </div>

        {window.location.href === "http://localhost:3000/cpa/add_cpa" && (
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
                    // dispatch(cleanCPA(null));
                    // dispatch(setEditButton(false));
                    // navigate("/cpa");
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
            {/* <Button onClick={handleDelete}>Yes</Button> */}
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
                value: "Cultural Program Or Activity",
                route: `../add_cpa/${id}`,
              },
              {
                value: "Participants",
                route: `../participants/${id}`,
              },
              {
                value: "Attachments",
                route: `../attachments/${id}`,
              },
            ]}
          />
        </Box>
        <Box sx={{ flex: "4 1 0", px: "1rem", overflowY: "auto" }}>
          {props.children}
        </Box>
      </Box>
    </AuthLayout>
  );
};

export default CpaLayout;
