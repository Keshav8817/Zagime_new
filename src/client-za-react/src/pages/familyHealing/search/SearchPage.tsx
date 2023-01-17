import Dropdown from "../../../components/Dropdown";
import Header from "../../../components/Header";
import Input from "../../../components/Input";
import AuthLayout from "../../../components/auth/layout/AuthLayout";
import SearchResults from "../../../components/fh/search/SearchResults";
import { onKeyDown } from "../../../library/app";

import { Box, Button } from "@mui/material";
import React, { useEffect, useState } from "react";
import type { FC, FormEvent } from "react";
import { Data } from "./SearchDatatype";
import { doGetAllSearch } from "./SearchService";
import { getFhReferralStatusCodetable } from "../../../services/codetableService";

/**
 * *FH* aka *Family Healing* module. \
 * `SearchPage` is *FH* modules' search page.
 */
const SearchPage: FC = () => {
  const [isShown, setIsShown] = useState<boolean>(false);
  const [FhReferralStatusCodetable, setFhReferralStatusCodetable] =
    useState<any>([]);
  const [state, setState] = useState<Data[]>([]);
  useEffect(() => {
    getFhReferralStatusCodetable().then((response) => {
      setFhReferralStatusCodetable(response.data.valuesMap);
    });
  }, []);
  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const form = e.currentTarget as HTMLFormElement;
    const formData: Data = {
      clientName: form.clientName.value || null,
      fileNumber: form.fileNumber.value || null,
      status: form.status.value || null,
      community: form.community.value || null,
      caseworker: form.caseworker.value || null,
      startDate: form.startDate.value || null,
    };
    console.log("data", formData);
    doGetAllSearch(formData).then(({ data }) => {
      setState(data);
      setIsShown(true);
    });
  };
  const hide = () => {
    setIsShown(false);
  };

  return (
    <AuthLayout>
      <Header bannerTitle="Family Healing" />
      <Box
        sx={{
          display: "flex",
          flexDirection: { xs: "column", md: "row" },
          justifyContent: "center",
          p: "1rem",
          gap: "0 1rem",
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
            Search for a Family Healing Case
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
            id="clientName"
            minChars={2}
            validationPattern={`^[a-zA-Z ]*$`}
            validationTitle="Digits are not allowed!"
            value="Client Name"
          />
          <Input
            id="fileNumber"
            validationPattern={`^[^a-zA-Z]*$`}
            validationTitle="Alphabets are not allowed!"
            value="File No."
          />
          <Dropdown
            autofill={""}
            id="status"
            value="Status"
            optionsList={Object.values(FhReferralStatusCodetable).map(
              (status: any) => status.en
            )}
            // disabled={state.editMode}
            required
          />
          <Input
            id="community"
            minChars={2}
            validationPattern={`^[a-zA-Z ]*$`}
            validationTitle="Digits are not allowed!"
            value="Community"
          />
          <Input
            id="caseworker"
            minChars={2}
            validationPattern={`^[a-zA-Z ]*$`}
            validationTitle="Digits are not allowed!"
            value="Caseworker"
          />
          <Input id="startDate" type="date" value="Start Date" />
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
