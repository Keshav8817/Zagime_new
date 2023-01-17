import Header from "../Header";
import NavBar from "../NavBar";
import AuthLayout from "../auth/layout/AuthLayout";
import { Box } from "@mui/material";
import React from "react";
import type { FC, PropsWithChildren } from "react";
import { useParams } from "react-router";

/**
 * *CYFMS* aka *Child, Youth, and Family Management Services* module. \
 * `CyfmsLayout` is layout of *CYFMS* modules' pages.
 * @example
 * ```jsx
 * <CyfmsLayout>...</CyfmsLayout>
 * // OR
 * <CyfmsLayout children={} />
 * ```
 */
const CyfmsLayout: FC<PropsWithChildren> = (props: any) => {
  const { id } = useParams();
  return (
    <AuthLayout>
      <div className="flex justify-between">
        <div>
          <Header bannerTitle="Children, Youth and Families" />
        </div>
      </div>
      <Box
        sx={{
          display: "flex",
          flexDirection: { xs: "column", md: "row" },
          gap: { xs: "1rem 0", md: undefined },
        }}
      >
        <Box sx={{ flex: "1 1 0", overflowY: "auto" }}>
          <NavBar
            tabs={[
              { value: "Register", route: `../register/${id}` },
              { value: "Contact", route: `../contact/${id}` },
              {
                value: "Household Members",
                route: `../household_members/${id}`,
              },
              {
                value: "Education and Employment",
                route: `../education_and_employment/${id}`,
              },
              { value: "Criminal History", route: `../criminal_history/${id}` },
              {
                value: "Family Physician(s)",
                route: `../family_physicians/${id}`,
              },
              {
                value: "Counselor(s) / CFS Worker(s)",
                route: `../counselors/${id}`,
              },
              {
                value: "Other Information",
                route: `../other_information/${id}`,
              },
              {
                value: "Attachments",
                route: `../attachments/${id}`,
              },
              {
                value: "Appointments",
                route: `../appointment/${id}`,
              },
              {
                value: "Reminders",
                route: `../reminder/${id}`,
              },
            ]}
          />
        </Box>
        <Box sx={{ flex: "4 1 0", px: "1rem", overflowY: "auto" }}>
          {props.children}
        </Box>
      </Box>
    </AuthLayout>
  );
};

export default CyfmsLayout;
