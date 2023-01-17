import Dropdown from "../../../components/Dropdown";
import Header from "../../../components/Header";
import Input from "../../../components/Input";
import AuthLayout from "../../../components/auth/layout/AuthLayout";
import SearchResults from "../../../components/initialContact/search/SearchResults";
import { onKeyDown } from "../../../library/app";
import { Box, Button } from "@mui/material";
import React, { useEffect, useState } from "react";
import type { FC } from "react";
import { Data } from "./SearchDataType";
import { getAllIcRecord } from "./service";
import { getIcrStatusCodetable } from "../../../services/codetableService";

/**
 * *IC* aka *Initial Contact* module. \
 * `SearchPage` is *IC* modules' search page.
 */
const SearchPage: FC = () => {
  const [isShown, setIsShown] = useState(false);
  const [state, setState] = useState<Data[]>([]);
  const [status, setStatus] = useState({});
  const hide = () => {
    setIsShown(false);
  };

  useEffect(() => {
    getIcrStatusCodetable().then((data) => {
      setStatus(data.data.valuesMap);
    });
  }, []);

  const submitHandler = (event: any) => {
    event.preventDefault();
    const form: any = event.currentTarget;
    const formData: Data = {
      fileDetailsId: null,
      clientName: form.clientName.value || null,
      fileNumber: form.fileNumber.value || null,
      caseworker: form.caseWorker.value || null,
      startingDate: form.startingDate.value || null,
      status: form.status.value || null,
    };
    getAllIcRecord(formData).then(({ data }) => {
      setState(data);
      setIsShown(true);
    });
  };

  return (
    <AuthLayout>
      <Header bannerTitle="Initial Contact" />
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
            Search for Initial Contact(s)
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
          <Input id="clientName" value="Client Name" />
          <Input
            id="fileNumber"
            minChars={1}
            validationPattern={`^[^a-zA-Z]*$`}
            validationTitle="Alphabets are not allowed!"
            value="File No."
          />
          <Input
            id="caseWorker"
            minChars={2}
            validationPattern={`^[a-zA-Z ]*$`}
            validationTitle="Digits are not allowed!"
            value="Caseworker"
          />
          <Input
            id="startingDate"
            maxDate={new Date().toISOString().substring(0, 10)}
            minDate="1900-01-01"
            type="date"
            value="Start Date"
          />
          <Dropdown
            id="status"
            autofill={""}
            optionsList={Object.values(status).map((status: any) => status.en)}
            value="Status"
          />
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
