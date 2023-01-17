import { Box } from "@mui/material";
import React from "react";
import type { FC } from "react";

/**
 * `Header` is header of non-authorized layout.
 */
const Header: FC = () => {
  return (
    <div>
      <Box
        sx={{
          backgroundImage: "url('/img/zagime.jpg')",
          backgroundRepeat: "no-repeat",
          backgroundPosition: "top center",
          backgroundSize: "",
          height: "222px",
          width: "100%",
        }}
      />
    </div>
  );
};

export default Header;
