import { Box, Typography } from "@mui/material";
import Layout from "../components/layout/Layout";
import React from "react";

/**
 * The NotFound404 functional component.
 * @returns NotFound404 component skeleton.
 */
export const NotFound404 = () => {
  return (
    <Layout>
      <Box
        sx={{
          alignItems: "center",
          display: "flex",
          flexDirection: "column",
          justifyContent: "center",
          m: "2rem",
        }}
      >
        <Typography variant="h1" color="primary">
          404
        </Typography>
        <Typography variant="h2">Page Not Found</Typography>
      </Box>
    </Layout>
  );
};

export default NotFound404;
