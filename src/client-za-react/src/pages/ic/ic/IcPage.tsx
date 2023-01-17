import AuthLayout from "../../../components/auth/layout/AuthLayout";
import { handleEffect } from "./IcPage_";
import { Box, Button } from "@mui/material";
import React, { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import type { FC } from "react";

import IcHeader from "../../../components/initialContact/IcHeader";
import { ModuleDispatchContext } from "../../../contexts/ModuleContext";
/**
 * `IcPage` is *IC* aka *Initial Contact* module main page.
 */
const IcPage: FC = () => {
  const navigate = useNavigate();
  const moduleDispatchContext = useContext(ModuleDispatchContext);
  const [id, setId] = useState(0);
  return (
    <AuthLayout>
      <IcHeader bannerTitle="Initial Contact and Referrals" />
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
            to={`/initial_contact/file_details/${id}`}
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
              navigate(`/initial_contact/file_details/${id}`);
            }}
          >
            Add an Initial Contact File
          </Button>
          <Button
            component={Link}
            to="/initial_contact/search"
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
              localStorage.removeItem("filedetailsId");
            }}
          >
            Search for an Initial Contact File
          </Button>
        </Box>
      </Box>
    </AuthLayout>
  );
};

export default IcPage;
