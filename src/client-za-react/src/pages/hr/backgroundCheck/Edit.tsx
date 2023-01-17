import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import Dropdown from "../../../components/Dropdown";
import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";
import { getGoalsAndObjectivesCodetable } from "../../../services/codetableService";
import { onKeyDown } from "../../../library/app";
import SearchIcon from "@mui/icons-material/Search";
import {
  Box,
  FormLabel,
  OutlinedInput,
  Typography,
  FormControl,
} from "@mui/material";
import SearchClientName from "../../../components/hr/searchClient/searchCient";

import React, { useState, useEffect } from "react";
import type { FC, FormEvent, FormEventHandler } from "react";
import CgLayout from "../../../components/hr/HrLayout";
import { Data } from "./BackgroundCheckDatatypes";
import { readBackgroundCheck, saveBackgroundCheck } from "./service";
import { useNavigate, useParams } from "react-router";
import EditIcon from "../../../components/hr/backgroundCheck/EditIcon";
import ICInput from "../../../components/initialContact/Input";
import DateInput from "../../../components/initialContact/DateInput";

const Edit: FC<any> = ({ setAddNew, targetValue }) => {
  const navigate = useNavigate();

  const [disabled, setDisabled] = useState(true);
  const [goalsAndObjectiveStatus, setGoalsAndObjectiveStatus] = useState<any>(
    []
  );
  const [click, setClick] = useState(false);
  const [clientName, setClientName] = useState("");
  const [clientID, setClientId] = useState();
  const { childId, id } = useParams();
  const [state, setState] = useState<Data>({
    backgrounCheckId: 0,
    typeOfCheck: "",
    status: "",
    dateRequested: "",
    dateCompleted: "",
    requestedBy: "",
    notes: "",
    staffId: Number(id),
  });
  useEffect(() => {
    readBackgroundCheck(Number(childId)).then(({ data }) => {
      setState(data);
      setClientName(data.requestedBy);
      setClientId(data.requestedById);
    });
  }, []);
  useEffect(() => {
    getGoalsAndObjectivesCodetable().then((response) => {
      setGoalsAndObjectiveStatus(response.data.valuesMap);
    });
  }, []);

  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    if (!click) {
      const form = e.currentTarget as HTMLFormElement;
      const formData: Data = {
        backgrounCheckId: state.backgrounCheckId,
        typeOfCheck: form.typeOfCheck.value,
        status: form.status.value,
        dateRequested: form.dateRequested.value,
        dateCompleted: form.dateCompleted.value,
        requestedBy: clientID,
        notes: form.notes.value,
        staffId: Number(id),
      };
      console.log(formData);
      saveBackgroundCheck(formData).then(() => {
        navigate(`../backgroundCheck/${Number(id)}`);
      });
    }
  };
  const handleSearch = () => {
    console.log("click search");
    if (!disabled) {
      setClick(true);
    }
  };
  return (
    <CgLayout>
      <Box
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
        }}
        onSubmit={submitHandler}
        onKeyDown={onKeyDown}
      >
        {disabled && (
          <Box
            sx={{
              display: "flex",
              justifyContent: "flex-end",
            }}
          >
            <EditIcon setDisabled={setDisabled} backgrounCheckId={id} />
            {/* <EditIcon
            setDisabled={setDisabled}
            setAddNew={setAddNew}
            backgroundCheckId={data.backgrounCheckId}
            targetValue={state.data.staffId}
          /> */}
          </Box>
        )}

        {/* {disabled && (
        <Typography sx={{ p: 1 }}>Reference Id:{data.referenceId}</Typography>
      )} */}
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              id="typeOfCheck"
              value="Type of Check"
              autofill={state.typeOfCheck}
              readOnly={disabled}
              required
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Dropdown
              id="status"
              value="Status"
              autofill={state.status}
              disabled={disabled}
              required
              optionsList={Object.values(goalsAndObjectiveStatus).map(
                (status: any) => status.en
              )}
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}></Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <DateInput
              id="dateRequested"
              value="Date Requested"
              autofill={state.dateRequested}
              type="date"
              validationPattern={`^[a-zA-Z ]*$`}
              validationTitle="Digits are not allowed!"
              readOnly={disabled}
              required
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <DateInput
              id="dateCompleted"
              value="Date completed"
              type="date"
              validationPattern={`^[a-zA-Z ]*$`}
              validationTitle="Digits are not allowed!"
              autofill={state.dateCompleted}
              readOnly={disabled}
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            {/* <ICInput
              id="supervisor"
              value="Supervisor"
              autofill={state.supervisor}
              readOnly={disabled}
            /> */}
            <FormControl
              sx={{
                display: "flex",
                flexDirection: "row",
                flexWrap: "wrap",
              }}
            >
              <FormLabel
                sx={{ p: 1, flexBasis: 0, flexGrow: 1, color: "black" }}
              >
                Requested By
              </FormLabel>
              <OutlinedInput
                sx={{
                  borderRadius: 2,
                  flexBasis: 0,
                  flexGrow: 1.9,
                }}
                size="small"
                value={clientName}
                disabled={disabled}
                style={{ backgroundColor: "#ffffff" }}
                endAdornment={<SearchIcon onClick={handleSearch} />}
              />
            </FormControl>
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
        </Box>

        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}></Box>
        <Typography variant="body1" color="primary">
          Results
        </Typography>
        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5.3 1 0"
          id="notes"
          value="Notes"
          autofill={state.notes}
          readOnly={disabled}
        />

        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSNextButton disabled={disabled} />
        </Box>
      </Box>
      {click && (
        <SearchClientName
          click={click}
          setClick={setClick}
          moduleName="staff"
          searchId="staffId"
          setClientName={setClientName}
          setClientId={setClientId}
        />
      )}
    </CgLayout>
  );
};

export { Edit as BackgroundCheckEdit };
