import { Box, Typography } from "@mui/material";
import React from "react";
import type { ReactElement } from "react";
import Navbar from "./Navbar";

/**
 * The Header functional component.
 * @returns Header component skelseton.
 */
const HomeHeader = (): ReactElement => {
  return (
    <>
      <Box>
        <div>
          <img
            src="https://gigastore.blob.core.windows.net/images/img/Topline-Banner.png"
            alt="img"
            className="h-48 md:w-full"
          />
        </div>
      </Box>
      <Box sx={{ mt: -4 }}>
        <Navbar />
      </Box>
    </>
  );
};

export default HomeHeader;
