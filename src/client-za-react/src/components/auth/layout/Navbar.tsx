import "./Navbar.css";
import { Box, Button, useMediaQuery, useTheme } from "@mui/material";
import React from "react";
import { useNavigate } from "react-router-dom";
import type { FC } from "react";
import Drawercomponent from "../Dropdowncomp";

/**
 * `Navbar` is navigation panel in authorized layout.
 */
const Navbar: FC = () => {
  const navigate = useNavigate();
  const theme = useTheme();

  const isMatch = useMediaQuery(theme.breakpoints.down("sm"));

  const handleHome = () => {
    navigate("/home");
  };

  const handleLogout = () => {
    localStorage.setItem("jwtToken", "");
    navigate("/login");
  };

  const handleCalendar = () => {
    navigate("/calendar");
  };
  return (
    <Box
      sx={{
        width: "100%",
        backgroundColor: "black",
        mt: 4,
      }}
    >
      {isMatch ? (
        <>
          <Drawercomponent />
        </>
      ) : (
        <>
          <ul>
            <li className="list">
              <Button
                sx={{
                  color: "white",
                  textTransform: "none",
                  backgroundColor: window.location.href.includes("calendar")
                    ? ""
                    : "orange",
                  "&:hover": {
                    backgroundColor: window.location.href.includes("calendar")
                      ? "#ad5202"
                      : "#f76d0a",
                  },
                }}
                onClick={() => {
                  handleHome();
                }}
              >
                Home
              </Button>
            </li>
            <li className="list ">
              <Button
                sx={{
                  color: "white",
                  marginLeft: "10px",
                  textTransform: "none",
                  backgroundColor: window.location.href.includes("calendar")
                    ? "orange"
                    : "",
                  "&:hover": {
                    backgroundColor: window.location.href.includes("calendar")
                      ? "#f76d0a"
                      : "#ad5202",
                  },
                }}
                onClick={() => {
                  handleCalendar();
                }}
              >
                Calendar
              </Button>
            </li>

            <li className="logout list">
              <Button
                sx={{
                  color: "white",
                  marginLeft: "10px",
                  textTransform: "none",
                  "&:hover": {
                    backgroundColor: window.location.href.includes("calendar")
                      ? "#f76d0a"
                      : "#ad5202",
                  },
                }}
                onClick={handleLogout}
              >
                Logout
              </Button>
            </li>
          </ul>
        </>
      )}
    </Box>
  );
};

export default Navbar;
