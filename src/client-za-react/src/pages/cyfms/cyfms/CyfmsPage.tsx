import AuthLayout from "../../../components/auth/layout/AuthLayout";
import Header from "../../../components/Header";
import { ModuleDispatchContext } from "../../../contexts/ModuleContext";
import { Box, Button } from "@mui/material";
import React, { useContext, useEffect } from "react";
import { Link } from "react-router-dom";
import type { FC } from "react";

/**
 * `CyfmsPage` is *CYFMS* aka \
 * *Child, Youth, Family and Wellness Management System* \
 * module main page.
 */
const CyfmsPage: FC = () => {
  const moduleDispatchContext = useContext(ModuleDispatchContext);

  useEffect(() => moduleDispatchContext({ type: "clean_context" }));

  return (
    <AuthLayout>
      <Header bannerTitle="Children, Youth and Families" />
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
            to={`/cyfms/register/${0}`}
            sx={{
              background: "lightgrey",
              color: "black",
              border: "1px solid black",
              display: "flex",
              justifyContent: "center",
              flexDirection: "column",
              textAlign: "center",
            }}
            onClick={() =>
              moduleDispatchContext({
                type: "toggle_crossButton",
                crossButton: false,
              })
            }
          >
            Register a child, youth or family member
          </Button>
          <Button
            component={Link}
            to="/cyfms/search"
            sx={{
              background: "lightgrey",
              color: "black",
              border: "1px solid black",
              display: "flex",
              justifyContent: "center",
              flexDirection: "column",
              textAlign: "center",
            }}
          >
            Search for a child, youth or family member
          </Button>
        </Box>
      </Box>
    </AuthLayout>
  );
};

export default CyfmsPage;
