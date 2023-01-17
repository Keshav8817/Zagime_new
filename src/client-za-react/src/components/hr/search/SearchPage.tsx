import { Box, Button, IconButton, Modal } from "@mui/material";
import React, { useEffect, useState } from "react";
import Input from "../../Input";
import Header from "../../../components/Header";

import CloseIcon from "@mui/icons-material/Close";

import type { FC } from "react";
import { Data } from "./SearchDatatypes";
import { getAllHrRecord } from "./service";
import SearchResults from "../../../pages/hr/search/SearchResults";
import Checkbox from "../../Checkbox";

import AuthLayout from "../../../components/auth/layout/AuthLayout";
import { onKeyDown } from "../../../library/app";
const SearchPage: FC = () => {
  const [isShown, setIsShown] = useState(false);
  const [state, setState] = useState<Data[]>([]);
  const [status, setStatus] = useState({});
  const hide = () => {
    setIsShown(false);
  };

  const submitHandler = (event: any) => {
    event.preventDefault();
    const form: any = event.currentTarget;

    const formData: Data = {
      staffId: 0,
      employeeId: 0,
      firstName: form.firstName.value || null,
      middleName: form.middleName.value || null,
      lastName: form.lastName.value || null,
      workLocation: form.workLocation.value || null,
      supervisor: form.supervisor.value || null,
      active: false,
    };
    getAllHrRecord(formData).then(({ data }) => {
      setState(data);
      setIsShown(true);
    });
  };
  return (
    <AuthLayout>
      <Header bannerTitle="Staff" />
      <Box
        sx={{
          display: "flex",
          flexDirection: { xs: "column", md: "row" },
          justifyContent: "center",
          p: "1rem",
          gap: "1rem",
          "& div": { width: { xs: "100%", md: 350 } },
        }}
      >
        <Box sx={{ display: "flex" }}>
          <Button
            sx={{
              background: "lightgrey",
              color: "black",
              border: "1px solid black",
              width: 300,
              textTransform: "none",
              mx: "auto",
              mb: "auto",
              pt: "1rem",
              pb: "1.2815rem",
            }}
          >
            Search for staff
          </Button>
        </Box>
        <Box
          component="form"
          sx={{
            display: "flex",
            flexDirection: "column",
            gap: "0.5rem 0",
          }}
          onSubmit={submitHandler}
          onKeyDown={onKeyDown}
        >
          <Input
            id="firstName"
            minChars={2}
            validationPattern={`^[a-zA-Z ]*$`}
            validationTitle="Digits are not allowed!"
            value="First Name"
          />
          <Input
            id="middleName"
            minChars={2}
            validationPattern={`^[a-zA-Z ]*$`}
            validationTitle="Digits are not allowed!"
            value="Middle Name"
          />
          <Input
            id="lastName"
            minChars={2}
            validationPattern={`^[a-zA-Z ]*$`}
            validationTitle="Digits are not allowed!"
            value="Last Name"
          />

          <Input
            id="workLocation"
            minChars={2}
            validationPattern={`^[a-zA-Z ]*$`}
            validationTitle="Digits are not allowed!"
            value="Work Location"
          />

          <Input
            id="supervisor"
            minChars={2}
            validationPattern={`^[a-zA-Z ]*$`}
            validationTitle="Digits are not allowed!"
            value="Supervisor"
            name="supervisor"
          />
          <Checkbox id="active" label="Active?" />
          <Box
            sx={{
              display: "flex",
              flexDirection: "row",
              flexWrap: "wrap",
            }}
          >
            <Box sx={{ flex: "1.08 1 0" }}></Box>
            <Box
              sx={{
                flex: "2 1 0",
                display: "flex",
                justifyContent: "center",
                gap: "0 1rem",
              }}
            >
              <Button variant="contained" type="submit">
                Search
              </Button>
              <Button variant="contained" type="reset" onClick={hide}>
                Reset
              </Button>
            </Box>
          </Box>
        </Box>
      </Box>
      {isShown && <SearchResults record={state} />}
    </AuthLayout>
  );
};

export default SearchPage;
