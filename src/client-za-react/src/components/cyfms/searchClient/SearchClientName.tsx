import { Box, Button, IconButton, Modal } from "@mui/material";
import React, { useEffect, useState } from "react";
import CYFMSDropdown from "../../Dropdown";
import Input from "../../Input";
import CloseIcon from "@mui/icons-material/Close";
import Header from "../../Header";
import ClientResults from "./ClientResults";
import type { ReactElement } from "react";
import { Search } from "../../../pages/cyfms/search/searchDatatypes";
import axiosInstance from "../../../library/axiosInstance";

const SearchClientName = ({
  searchId,
  moduleName,
  setDisabled,
  setAddNew,
  contactId,
  targetValue,
  click,
  setClick,
  setClientName,
  setClientId,
}: any): ReactElement => {
  const [show, setShown] = useState(false);
  const [data, setData] = useState<Search[]>();
  const handleClose = () => {
    setClick(false);
  };

  const hide = () => {
    setShown(false);
  };

  const handleSubmit: AppFormEventHandler<HTMLFormElement> = (event) => {
    event.preventDefault();
    setShown(true);
    const formData: Search = {
      participantId: null,
      referenceId: event.currentTarget.referenceId.value || null,
      firstname: event.currentTarget.firstName.value || null,
      surname: event.currentTarget.lastName.value || null,
      middleName: event.currentTarget.middleName.value || null,
      dateOfBirth: event.currentTarget.dateOfBirth.value || null,
      maritalStatus: event.currentTarget.maritalStatus.value || null,
      city: event.currentTarget.city.value || null,
      workPhone: event.currentTarget.phoneNo.value || null,
    };
    axiosInstance
      .get(
        `participantservice/searchParticipants/${formData.referenceId}/${formData.firstname}/${formData.middleName}/${formData.surname}/${formData.dateOfBirth}/${formData.maritalStatus}/${formData.city}/${formData.workPhone}`,
        {
          headers: {
            authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
          },
        }
      )
      .then((response) => {
        console.log("resss--", response);
        setData(response.data);
        setShown(true);
      });
  };
  return (
    <div>
      <Modal
        open={click}
        onClose={(_event, reason) => {
          switch (reason) {
            case "backdropClick":
              return;
            case "escapeKeyDown":
              return;
          }
        }}
      >
        <Box
          sx={{
            position: "relative",
            left: "50%",
            top: "50%",
            transform: "translate(-50%, -50%)",
            maxHeight: 500,
            maxWidth: 1000,
            bgcolor: "background.paper",
            border: "5px solid black",
            boxShadow: 24,
            overflowY: "auto",
          }}
        >
          <IconButton
            color="primary"
            aria-label="Close the popup box."
            onClick={(e) => {
              handleClose();
            }}
            sx={{ position: "absolute", right: 0 }}
          >
            <CloseIcon />
          </IconButton>

          <Header bannerTitle="Child, Youth, and Family Members" />

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
                  maxWidth: 300,
                  textTransform: "none",
                  mx: "auto",
                  mb: "auto",
                }}
              >
                Search for a Child, Youth, or Family Member
              </Button>
            </Box>
            <Box
              component="form"
              sx={{
                display: "flex",
                flexDirection: "column",
                gap: "0.5rem 0",
              }}
              onSubmit={handleSubmit}
            >
              <Input
                id="referenceId"
                validationPattern={`^[^a-zA-Z]*$`}
                validationTitle="Alphabets are not allowed!"
                value="Reference Id"
              />
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
                id="dateOfBirth"
                maxDate={new Date().toISOString().substring(0, 10)}
                minDate="1900-01-01"
                type="date"
                value="Date Of Birth"
              />
              <CYFMSDropdown
                id="maritalStatus"
                autofill={""}
                // optionsList={Object.values(maritalstatus).map(
                //   (status: any) => status.en
                // )}
                optionsList={[""]}
                value="Marital Status"
              />
              <Input
                id="phoneNo"
                minChars={2}
                validationPattern={`^[^a-zA-Z]*$`}
                validationTitle="Alphabets are not allowed!"
                value="Phone No"
                name="phoneNo"
              />
              <Input
                id="city"
                minChars={2}
                validationPattern={`^[a-zA-Z ]*$`}
                validationTitle="Digits are not allowed!"
                value="City"
                name="city"
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
          {show && (
            <ClientResults
              setClick={setClick}
              moduleName={moduleName}
              searchId={searchId}
              data={data}
              setClientName={setClientName}
              setClientId={setClientId}
            />
          )}
        </Box>
      </Modal>
    </div>
  );
};

export default SearchClientName;
