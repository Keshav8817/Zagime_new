import React, { FC, useState } from "react";
import Drawer from "@mui/material/Drawer";
import { useNavigate } from "react-router-dom";

import {
  Button,
  IconButton,
  List,
  ListItemButton,
  ListItemIcon,
  ListItemText,
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";

const Drawercomponent: FC = () => {
  const navigate = useNavigate();
  const [openDrawer, setopenDrawer] = useState(false);

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
    <React.Fragment>
      <Drawer open={openDrawer} onClose={() => setopenDrawer(false)}>
        <List>
          <ListItemButton onClick={() => setopenDrawer(false)}>
            <div className="flex flex-col">
              <div>
                <ListItemIcon>
                  <Button
                    sx={{
                      color: "yellow",
                      textTransform: "none",
                      backgroundColor: window.location.href.includes("calendar")
                        ? ""
                        : "orange",
                      "&:hover": {
                        backgroundColor: window.location.href.includes(
                          "calendar"
                        )
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
                </ListItemIcon>
                <br />
                <div>
                  <ListItemIcon>
                    <Button
                      sx={{
                        color: "yellow",

                        textTransform: "none",
                        backgroundColor: window.location.href.includes(
                          "calendar"
                        )
                          ? "orange"
                          : "",
                        "&:hover": {
                          backgroundColor: window.location.href.includes(
                            "calendar"
                          )
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
                  </ListItemIcon>
                </div>
                <div>
                  <ListItemIcon>
                    <Button
                      sx={{
                        color: "yellow",

                        textTransform: "none",
                        "&:hover": {
                          backgroundColor: window.location.href.includes(
                            "calendar"
                          )
                            ? "#f76d0a"
                            : "#ad5202",
                        },
                      }}
                      onClick={handleLogout}
                    >
                      Logout
                    </Button>
                  </ListItemIcon>
                </div>
              </div>
            </div>
          </ListItemButton>
        </List>
      </Drawer>
      <IconButton onClick={() => setopenDrawer(!openDrawer)}>
        <MenuIcon sx={{ background: "white" }} />
      </IconButton>
    </React.Fragment>
  );
};

export default Drawercomponent;
