import Box from "@mui/material/Box";
import Footer from "./Footer";
import Header from "./Header";
import React from "react";
import type { FC, PropsWithChildren } from "react";

/**
 * `Layout` is layout of non-authorized pages.
 * @param props
 * @example
 * ```jsx
 * <Layout>...</Layout>
 * // OR
 * <Layout children={} />
 * ```
 */
const Layout: FC<PropsWithChildren> = (props) => {
  return (
    <Box>
      <Header />
      {props.children}
      <Footer />
    </Box>
  );
};

export default Layout;
