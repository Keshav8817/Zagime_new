import { Box, Typography } from "@mui/material";
import React from "react";
import type { FC } from "react";
/**
 * `Props` is data type for props passed to `Header` FC.
 */
export interface Props {
  /**
   * `bannerTitle` is title displayed on header.
   */
  bannerTitle: string;
}

/**
 * `Header` FC.
 * @param props
 * @returns `ReactElement`
 */
const CpaHeader: FC<Props> = (props) => {
  return (
    <Box
      sx={{
        display: "flex",
        alignItems: "center",
        gap: "0 0.5rem",
        p: "0.5rem",
      }}
    >
      <Box component="img" src="/img/cp.png" height={75} width={75} />
      <Typography component="h2">{props.bannerTitle}</Typography>
    </Box>
  );
};

export default CpaHeader;
