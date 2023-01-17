import Dropdown from "../../../components/Dropdown";
import Header from "../../../components/Header";
import Input from "../../../components/Input";
import AuthLayout from "../../../components/auth/layout/AuthLayout";
import SearchResults from "../../../components/cpa/search/SearchResults";
import { onKeyDown } from "../../../library/app";
import { handleEffect, handleSubmit } from "./searchService";
import { Box, Button } from "@mui/material";
import React, { useEffect, useState } from "react";
import type { Search } from "./searchDatatypes";
import type { FC, FormEvent } from "react";

/**
 * *CPA* aka *Cultural Program And Activity* module. \
 * `SearchPage` is *CPA* modules' search page.
 */
const SearchPage: FC = () => {
  //const [culturalStatus, setCulturalStatus] = useState<any>([]);

  const [cpaCulturalStatus, setCpaCulturalStatus] = useState({});

  const [cpaTypesCodetable, setCpaTypesCodetable] = useState({});
  const [data, setData] = useState<Search[]>([]);
  const [isShown, setIsShown] = useState<boolean>(false);

  const hide = () => {
    setIsShown(false);
  };

  useEffect(() => handleEffect(setCpaCulturalStatus, setCpaTypesCodetable), []);

  return (
    <AuthLayout>
      <Header bannerTitle="Cultural Programs and Activities" />
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
            Search for a Cultural Program or Activity
          </Button>
        </Box>
        <Box
          component="form"
          sx={{
            display: "flex",
            flexDirection: "column",
            gap: "0.5rem 0",
          }}
          onSubmit={(event: FormEvent<HTMLFormElement>) =>
            handleSubmit(event, setData, setIsShown)
          }
          onKeyDown={onKeyDown}
        >
          <Input
            id="referenceId"
            validationPattern={`^[^a-zA-Z]*$`}
            validationTitle="Alphabets are not allowed!"
            value="Reference Id"
          />
          <Input
            id="name"
            minChars={2}
            validationPattern={`^[a-zA-Z ]*$`}
            validationTitle="Digits are not allowed!"
            value="Name"
          />
          <Dropdown
            autofill={""}
            id="type"
            optionsList={Object.values(cpaTypesCodetable).map(
              (type: any) => type.en
            )}
            value="Type"
          />
          <Input
            id="caseworker"
            minChars={2}
            validationPattern={`^[a-zA-Z ]*$`}
            validationTitle="Digits are not allowed!"
            value="Caseworker"
          />
          <Input
            id="startDate"
            maxDate={new Date().toISOString().substring(0, 10)}
            minDate="1900-01-01"
            type="date"
            value="Date"
          />
          <Dropdown
            autofill={""}
            id="status"
            value="Status"
            optionsList={Object.values(cpaCulturalStatus).map(
              (type: any) => type.en
            )}
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
      {isShown && <SearchResults list={data} />}
    </AuthLayout>
  );
};

export default SearchPage;
