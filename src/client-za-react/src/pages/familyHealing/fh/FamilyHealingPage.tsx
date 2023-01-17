import { Box, Button } from "@mui/material";
import React, { useContext, useState } from "react";
import { Link, useParams } from "react-router-dom";
import AuthLayout from "../../../components/auth/layout/AuthLayout";
import FhHeader from "../../../components/fh/fhHeader";
import { ModuleDispatchContext } from "../../../contexts/ModuleContext";

const FamilyHealingPage = () => {
  const moduleDispatchContext = useContext(ModuleDispatchContext);
  const [id, setId] = useState(0);
  return (
    <>
      {" "}
      <AuthLayout>
        <FhHeader bannerTitle="Family Healing" />
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
              to={`/fh/fileDetails/${id}`}
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
                localStorage.removeItem("fhFiledetailsId");
              }}
            >
              Add A Family Healing Case
            </Button>
            <Button
              component={Link}
              to="/fh/search"
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
                moduleDispatchContext({
                  type: "toggle_tabsDisabled",
                  tabsDisabled: true,
                });
                localStorage.removeItem("fhFiledetailsId");
              }}
            >
              Search for a Family Healing Case
            </Button>
          </Box>
        </Box>
      </AuthLayout>
    </>
  );
};

export default FamilyHealingPage;
