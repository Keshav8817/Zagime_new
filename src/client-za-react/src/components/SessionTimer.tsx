import React, { useEffect, useState } from "react";
import type { ReactElement } from "react";
import { useIdleTimer } from "react-idle-timer";
import { Button, Modal, Typography } from "@mui/material";
import { Box } from "@mui/material";
import { useNavigate } from "react-router";

const SessionTimer = (): ReactElement => {
  const [timeVal, setTimeVal] = useState(null); // This will hold current remaining time
  const [open, setOpen] = useState(false);
  const [idleTimer, setIdleTimer] = useState(1000 * 60 * 5);
  const [isTimeOut, setIsTimeOut] = useState(false);
  const navigate = useNavigate();

  const handleOnIdle = () => {
    localStorage.setItem("jwtToken", "");
    navigate("/login");
  };

  //logout handler
  const handleLogout = () => {
    localStorage.setItem("jwtToken", "");
    navigate("/login");
  };

  const handleActive = (event: any) => {
    setIsTimeOut(false);
  };

  const handleOnAction = (event: any) => {
    setIsTimeOut(false);
  };

  const handleContinue = () => {
    setOpen(false);
  };

  const { getRemainingTime } = useIdleTimer({
    timeout: 1000 * 60 * 15,
    onIdle: handleOnIdle,
    onAction: handleOnAction,
    onActive: handleActive,
    debounce: 1000,
  });

  useEffect(() => {
    const interval = setInterval(() => {
      setTimeVal(getRemainingTime() as any);
      setIdleTimer(getRemainingTime() as any);
    }, 1000);
  }, []);

  useEffect(() => {
    if (idleTimer < 30000) {
      setOpen(true);
    }
  }, [timeVal]);

  return (
    <>
      {open && (
        <Modal
          open={open}
          sx={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
          onClose={(event, reason) => {
            switch (reason) {
              case "backdropClick":
                return;
            }
          }}
          aria-labelledby="parent-modal-title"
          aria-describedby="parent-modal-description"
        >
          <Box
            sx={{
              height: "200px",
              width: "500px",
              paddingTop: "2%",
              backgroundColor: "white",
              color: "black",
            }}
          >
            <Typography
              sx={{
                display: "flex",
                justifyContent: "flex-end",
                paddingRight: "10px",
                color: "red",
                fontWeight: "1000px",
              }}
            >
              Remaining time {Math.floor(idleTimer / 1000) % 60} sec
            </Typography>
            <Typography
              sx={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                fontSize: "40px",
                fontWeight: "100px",
              }}
            >
              Session Time Out
            </Typography>
            <Box
              sx={{
                display: "flex",
                paddingTop: "4%",
                justifyContent: "center",
                alignItems: "center",
                gap: 5,
              }}
            >
              <Button variant="contained" onClick={handleContinue}>
                Continue
              </Button>
              <Button variant="contained" onClick={handleLogout}>
                Logout
              </Button>
            </Box>
          </Box>
        </Modal>
      )}
    </>
  );
};

export default SessionTimer;
