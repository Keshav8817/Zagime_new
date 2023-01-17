import {
  CYFSWMSNextButton,
  CYFSWMSSaveButton,
} from "../../../components/CYFSWMSButtons";
import Dropdown from "../../../components/Dropdown";
import { Box, OutlinedInput } from "@mui/material";
import React, { useContext, useEffect, useState } from "react";
import TextArea from "../../../components/TextArea";
import type { FormEvent, ReactElement } from "react";
import { saveCpa, readCpa } from "./Service";
import { useNavigate, useParams } from "react-router-dom";
import CPALayout from "../../../components/cpa/CPALayout";
import { Data } from "./CulturalProgramAndActivityDataTypes";
import {
  doGetCPACulturalStatusAPI,
  getCpaTypesCodetable,
} from "../../../services/codetableService";
import ICInput from "../../../components/cpa/Input";
import { ModuleDispatchContext } from "../../../contexts/ModuleContext";
import EditModeButton from "../../../components/cpa/EditModeButton";
import DateInput from "../../../components/initialContact/DateInput";

const CulturalProgramOrActivity: any = () => {
  const navigate = useNavigate();
  const moduleDispatchContext = useContext(ModuleDispatchContext);
  const [cpaTypesCodetable, setCpaTypesCodetable] = useState({});
  const [cpaCulturalStatus, setCpaCulturalStatus] = useState({});
  const [click, setClick] = useState(false);
  const [disabled, setDisabled] = useState(false);
  const { id } = useParams();
  const [state, setState] = useState<Data>({
    culturalProgramId: Number(id),
    referenceId: 0,
    name: "",
    type: "",
    status: "",
    caseworker: "",
    startDate: "",
    endDate: "",
    totalCost: "",
    totalParticipation: "",
    sessionDetails: "",
    costOrParticipationDetails: "",
    outcomes: "",
    notes: "",
  });
  useEffect(() => {
    getCpaTypesCodetable().then((response) => {
      setCpaTypesCodetable(response.data.valuesMap);
    });

    doGetCPACulturalStatusAPI().then((response) => {
      setCpaCulturalStatus(response.data.valuesMap);
    });

    readCpa(Number(id)).then(({ data }) => {
      setState(data);
      if (data.culturalProgramId !== 0) {
        setDisabled(true);
      }
    });
  }, []);
  // const handleSearch()=()=>{
  //   setClick(true);
  // }

  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const form: any = e.currentTarget;
    const formData: Data = {
      culturalProgramId: Number(id),
      referenceId: state.referenceId,
      name: form.name.value,
      type: form.type.value,
      status: form.status.value,
      caseworker: form.caseworker.value,
      startDate: form.startDate.value,
      endDate: form.endDate.value,
      totalCost: form.totalCost.value,
      totalParticipation: form.totalParticipation.value,
      sessionDetails: form.sessionDetails.value,
      costOrParticipationDetails: form.costOrParticipationDetails.value,
      outcomes: form.outcomes.value,
      notes: form.notes.value,
    };
    saveCpa(formData).then((res) => {
      moduleDispatchContext({
        type: "toggle_tabsDisabled",
        tabsDisabled: false,
      });
      navigate(`/cpa/participants/${res.data.culturalProgramId}`);
    });
  };

  return (
    <CPALayout>
      <Box
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
        }}
        onSubmit={submitHandler}
        //   onSubmit={(event) => submitHandler(event, navigate, data)}
        //  onKeyDown={onKeyDown}
      >
        <div>
          <>
            <EditModeButton
              id={state.culturalProgramId}
              disabled={disabled}
              setDisabled={setDisabled}
              path={"/cpa"}
            />
          </>
        </div>

        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Box
              sx={{
                display: "flex",
                flexDirection: "row",
                flexWrap: "wrap",
              }}
            >
              <Box sx={{ p: 1, flexBasis: 0, flexGrow: 1, color: "black" }}>
                Reference ID
              </Box>
              <OutlinedInput
                size="small"
                readOnly
                sx={{ borderRadius: 2, flexBasis: 0, flexGrow: 2 }}
                value={state.referenceId}
                style={{ backgroundColor: "#ffffff" }}
              />
            </Box>
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              id="name"
              value="Name"
              required
              readOnly={disabled}
              autofill={state.name}
              // disabled={editMode}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Dropdown
              id="type"
              autofill={state.type}
              value="Type"
              disabled={disabled}
              optionsList={Object.values(cpaTypesCodetable).map(
                (type: any) => type.en
              )}
              required
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Dropdown
              id="status"
              value="Status"
              disabled={disabled}
              autofill={state.status}
              optionsList={Object.values(cpaCulturalStatus).map(
                (type: any) => type.en
              )}
              required

              // disabled={editMode}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              autofill={state.caseworker}
              id="caseworker"
              value="Caseworker"
              readOnly={disabled}
              required
              // disabled={editMode}
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <DateInput
              autofill={state.startDate}
              id="startDate"
              value="Start Date"
              type="Date"
              readOnly={disabled}
              required
              //disabled={editMode}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <DateInput
              autofill={state.endDate}
              id="endDate"
              value="End Date"
              readOnly={disabled}
              type="Date"
              //  disabled={editMode}
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              autofill={state.totalParticipation}
              id="totalParticipation"
              value="Total Participation"
              readOnly={disabled}
              //disabled={editMode}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              autofill={state.totalCost}
              id="totalCost"
              value="Total Cost"
              readOnly={disabled}
              //disabled={editMode}
            />
          </Box>
        </Box>
        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5.3 1 0"
          autofill={state.sessionDetails}
          id="sessionDetails"
          value="Session Details"
          readOnly={disabled}
          //disabled={editMode}
        />
        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5.3 1 0"
          autofill={state.costOrParticipationDetails}
          id="costOrParticipationDetails"
          value="Participation / Cost Details"
          readOnly={disabled}
          // disabled={editMode}
        />
        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5.3 1 0"
          autofill={state.outcomes}
          id="outcomes"
          value="Outcomes"
          readOnly={disabled}
          // disabled={editMode}
        />
        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5.3 1 0"
          autofill={state.notes}
          id="notes"
          value="Notes"
          readOnly={disabled}
          //disabled={editMode}
        />
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <>
            <CYFSWMSSaveButton disabled={disabled} />
          </>
        </Box>
      </Box>
    </CPALayout>
  );
};

export default CulturalProgramOrActivity;
