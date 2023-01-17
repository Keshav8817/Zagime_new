import Header from "../../../components/Header";
import AuthLayout from "../../../components/auth/layout/AuthLayout";

import { Box, Button } from "@mui/material";
import { Link } from "react-router-dom";
import { FC, useContext, useState } from "react";
import HrHeader from "../../../components/hr/HrHeader";
import { ModuleDispatchContext } from "../../../contexts/ModuleContext";

/**
 * `HrPage` is *Human Resources*  module main page.
 */
const HrPage: FC = () => {
  const moduleDispatchContext = useContext(ModuleDispatchContext);
  const [id, setId] = useState(0);
  return (
    <AuthLayout>
      <HrHeader bannerTitle="Staff" />
      <Box
        sx={{
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          py: "3rem",
        }}
      >
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            maxWidth: 300,
            rowGap: "1rem",
          }}
        >
          <Button
            component={Link}
            to={`/hr/staff/${id}`}
            sx={{
              background: "lightgrey",
              color: "black",
              border: "1px solid black",
              display: "flex",
              justifyContent: "center",
              flexDirection: "column",
              textAlign: "center",
              pt: "1rem",
              pb: "1.2815rem",
              width: 300,
            }}
            onClick={() => {
              moduleDispatchContext({
                type: "toggle_tabsDisabled",
                tabsDisabled: true,
              });
            }}
          >
            Add A Staff
          </Button>
          <Button
            component={Link}
            to="../search"
            sx={{
              background: "lightgrey",
              color: "black",
              border: "1px solid black",
              display: "flex",
              justifyContent: "center",
              flexDirection: "column",
              textAlign: "center",
              pt: "1rem",
              pb: "1.2815rem",
              width: 300,
            }}
            className="lowercase"
            onClick={() => {
              localStorage.removeItem("staffId");
              // dispatch(setOpenPopup(true));
            }}
          >
            Search for a Staff
          </Button>
        </Box>
      </Box>
    </AuthLayout>
  );
};

export default HrPage;
