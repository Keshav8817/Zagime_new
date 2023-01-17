import { Box } from "@mui/material";
// import { useAppSelector } from "../../../library/hooks";
import Header from "./AuthHeader";
import React from "react";
import { Navigate } from "react-router-dom";
import type { ReactElement, ReactNode } from "react";
import SessionTimer from "../../SessionTimer";

/**
 * The `AuthLayout` functional component makes sure
 * that the user is logged-in. If not, then redirect
 * them to the respective login page.
 * @example
 * ```tsx
 * <AuthLayout>...</AuthLayout>
 * // OR
 * <AuthLayout />
 * ```
 * @returns `AuthLayout` component skeleton.
 */
const AuthLayout = (props: {
  children: ReactNode | ReactNode[];
}): ReactElement => {
  // const state = useAppSelector((state) => state.login);

  if (localStorage.getItem("jwtToken") === "") {
    return <Navigate to="/login" />;
  }

  return (
    <Box sx={{}}>
      <Header />
      {props.children}
      <SessionTimer />
    </Box>
  );
};

export default AuthLayout;
