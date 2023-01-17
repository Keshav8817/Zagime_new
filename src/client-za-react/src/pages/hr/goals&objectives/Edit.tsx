import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import Dropdown from "../../../components/Dropdown";
import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";
import { getGoalsAndObjectivesCodetable } from "../../../services/codetableService";
import CYFMSDropdown from "../../../components/Dropdown";
import { onKeyDown } from "../../../library/app";
// import { useAppDispatch, useAppSelector } from "../../../library/hooks";
import {
  Box,
  FormControl,
  FormLabel,
  OutlinedInput,
  Typography,
} from "@mui/material";
import React, { useState, useEffect } from "react";
import type { FC, FormEvent, FormEventHandler } from "react";
import { useNavigate, useParams } from "react-router";
import { Data } from "./GoalsObjectiveDataTypes";
import { readGoalsAndObjective, saveGoalsAndObjective } from "./service";
import HrLayout from "../../../components/hr/HrLayout";
import CgLayout from "../../../components/hr/HrLayout";
import EditIcon from "../../../components/hr/goalsObjective.tsx/EditIcon";
import ICInput from "../../../components/initialContact/Input";
import SearchClientName from "../../../components/hr/searchClient/searchCient";
import DateInput from "../../../components/initialContact/DateInput";
import SearchIcon from "@mui/icons-material/Search";
const Edit: FC<any> = ({ setAddNew, targetValue }) => {
  const navigate = useNavigate();

  const [goalsAndObjectiveStatus, setGoalsAndObjectiveStatus] = useState<any>(
    []
  );
  // const dispatch = useAppDispatch();
  // const state = useAppSelector((state) => state.hrStaff);
  // const { goalsAndObjective } = useAppSelector((state) => state.codetable);
  // const data = useAppSelector((state) => state.hrGolsAndObjective.data);
  // const { id, clientName } = useAppSelector((state) => state.icReminder);
  const { childId, id } = useParams();
  const [disabled, setDisabled] = useState(true);
  const [clientName, setClientName] = useState("");
  const [clientID, setClientId] = useState();
  const [click, setClick] = useState(false);
  const [state, setState] = useState<Data>({
    goalsAndObjectivesId: Number(childId),
    date: "",
    status: "",
    supervisor: "",
    planPeriod: "",
    goalsAndObjectives: "",
    guidesAndEnablers: "",
    reviewComments: "",
    reviewedBy: "",
    reviewDate: "",
    staffId: Number(id),
  });
  useEffect(() => {
    readGoalsAndObjective(Number(childId)).then(({ data }) => {
      setState(data);
      setClientName(data.supervisor);
      setClientId(data.supervisorId);
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
        goalsAndObjectivesId: Number(childId),
        date: form.date.value,
        status: form.status.value,
        supervisor: clientID,
        planPeriod: form.planPeriod.value,
        goalsAndObjectives: form.goalsAndObjectives.value,
        guidesAndEnablers: form.guidesAndEnablers.value,
        reviewComments: form.reviewComments.value,
        reviewedBy: form.reviewedBy.value,
        reviewDate: form.reviewDate.value,
        staffId: Number(id),
      };
      console.log(formData);
      saveGoalsAndObjective(formData).then(() => {
        navigate(`../goals&objectives/${Number(id)}`);
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
      {" "}
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
            <EditIcon setDisabled={setDisabled} goalsAndObjectivesId={id} />
            {/* <EditIcon
              setDisabled={setDisabled}
              setAddNew={setAddNew}
              // goalsAndObjectiveId={data.goalsAndObjectivesId}
              // targetValue={state.data.staffId}
            /> */}
          </Box>
        )}

        {/* {disabled && (
          <Typography sx={{ p: 1 }}>
            File No:{state.getData.fileNumber}
          </Typography>
        )} */}
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <DateInput
              id="date"
              value="Date"
              type="date"
              validationPattern={`^[a-zA-Z ]*$`}
              validationTitle="Digits are not allowed!"
              required
              autofill={state.date}
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <CYFMSDropdown
              id="status"
              value="Status"
              autofill={state.status}
              disabled={disabled}
              optionsList={Object.values(goalsAndObjectiveStatus).map(
                (status: any) => status.en
              )}
              required
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
                Supervisor
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
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              id="planPeriod"
              value="Plan Period"
              autofill={state.planPeriod}
              readOnly={disabled}
            />
          </Box>
        </Box>
        <Typography variant="body1" color="primary">
          Goals and Objectives
        </Typography>
        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5.3 1 0"
          id="goalsAndObjectives"
          value="Goals & Objectives"
          autofill={state.goalsAndObjectives}
          readOnly={disabled}
          required
        />
        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5.3 1 0"
          id="guidesAndEnablers"
          value="Guides & Enablers"
          autofill={state.guidesAndEnablers}
          readOnly={disabled}
        />
        <Typography variant="body1" color="primary">
          Review
        </Typography>
        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5.3 1 0"
          id="reviewComments"
          value="Review Comments"
          autofill={state.reviewComments}
          readOnly={disabled}
        />
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              id="reviewedBy"
              value="Reviewed By"
              autofill={state.reviewedBy}
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <DateInput
              id="reviewDate"
              value="Review Date"
              type="date"
              validationPattern={`^[a-zA-Z ]*$`}
              validationTitle="Digits are not allowed!"
              autofill={state.reviewDate}
              readOnly={disabled}
            />
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
        </Box>
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

export { Edit as GoalsAndObjectiveEdit };
