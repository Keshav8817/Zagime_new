import { Box, Button, IconButton, Modal } from "@mui/material";
import React, { useEffect, useState } from "react";
import CYFMSDropdown from "../../Dropdown";
import Input from "../../Input";
import CloseIcon from "@mui/icons-material/Close";
import Header from "../../Header";
import ClientResults from "./clientResult";
import { getAllHrRecord } from "../search/service";
import Checkbox from "../../Checkbox";
import type { ReactElement } from "react";
import { Data } from "../search/SearchDatatypes";
import axiosInstance from "../../../library/axiosInstance";

const SearchClientName = ({
  searchId,
  moduleName,

  click,
  setClick,
  setClientName,
  setClientId,
}: any): ReactElement => {
  const [show, setShown] = useState(false);
  const [data, setData] = useState<Data[]>([]);
  const handleClose = () => {
    setClick(false);
  };

  const hide = () => {
    setShown(false);
  };

  const handleSubmit: AppFormEventHandler<HTMLFormElement> = (event) => {
    event.preventDefault();
    setShown(true);
    const formData: Data = {
      staffId: 0,
      // employeeId: event.currentTarget.referenceId.value || null,
      employeeId: 0,
      firstName: event.currentTarget.firstName.value || null,
      middleName: event.currentTarget.lastName.value || null,
      lastName: event.currentTarget.middleName.value || null,
      workLocation: event.currentTarget.workLocation.value || null,
      supervisor: event.currentTarget.supervisor.value || null,
      active: event.currentTarget.active.value || null,
    };
    axiosInstance
      .get(
        `/staffservice/searchStaffs/${formData.firstName}/${formData.lastName}/${formData.middleName}/${formData.workLocation}/${formData.supervisor}/${formData.active}`,
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("jwtToken"),
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
                  maxWidth: 300,
                  textTransform: "none",
                  mx: "auto",
                  mb: "auto",
                }}
              >
                Search for a Staff
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
