import {
  CYFSWMSSaveButton,
  CYFSWMSNextButton,
} from "../../../components/CYFSWMSButtons";
import Input from "../../../components/Input";
import SearchClientName from "../../../components/cyfms/searchClient/SearchClientName";
import CYFMSDropdown from "../../../components/Dropdown";
import TextArea from "../../../components/TextArea";
import { onKeyDown } from "../../../library/app";
import SearchIcon from "@mui/icons-material/Search";

import {
  Box,
  OutlinedInput,
  FormLabel,
  FormControl,
  Typography,
} from "@mui/material";
import React, { SyntheticEvent, useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { FC, FormEvent } from "react";
import { Data } from "./fileDetailsDataType";

import Checkbox from "../../../components/Checkbox";
import CheckboxEnd from "../../../components/CheckboxEnd";
import FhLayout from "../../../components/fh/FhLayout";

import axiosInstance from "../../../library/axiosInstance";
import { readFileDetails, saveFileDetails } from "./FileDetailsService";
import Dropdown from "../../../components/Dropdown";
import { getFhReferralStatusCodetable } from "../../../services/codetableService";
import EditModeButton from "../../../components/fh/EditModeButton";
import ICInput from "../../../components/initialContact/Input";
import { ModuleDispatchContext } from "../../../contexts/ModuleContext";
import DateInput from "../../../components/initialContact/DateInput";

const FileDetails: FC = () => {
  const navigate = useNavigate();
  const moduleDispatchContext = useContext(ModuleDispatchContext);

  const [disableStatusType, setDisableStatusType] = useState<boolean>(false);

  const [click, setClick] = useState(false);
  const [clientName, setClientName] = useState("");
  const [clientID, setClientId] = useState();
  const [disabledStatus, setDisabledStatus] = useState(Boolean);
  const { id } = useParams();
  const [disabled, setDisabled] = useState(false);
  const [FhReferralStatusCodetable, setFhReferralStatusCodetable] =
    useState<any>([]);
  const [state, setState] = useState<Data>({
    fhFileDetailsId: Number(id) | 0,
    fileNo: 0,
    clientName: "",
    startDate: "",
    department: "",
    endDate: "",
    status: "",
    community: "",
    caseworker: "",
    date: "",
    reason: "",
    notes: "",
  });

  useEffect(() => {
    getFhReferralStatusCodetable().then((response) => {
      setFhReferralStatusCodetable(response.data.valuesMap);
    });
    readFileDetails(Number(id)).then(({ data }) => {
      setState(data);
      setClientName(data.clientName);
      setClientId(data.participantId);
      if (data.fhFileDetailsId !== 0) {
        setDisabled(true);
      }
      setDisableStatusType(true);
    });
  }, []);

  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const form: any = e.currentTarget;
    const formData: Data = {
      fhFileDetailsId: state.fhFileDetailsId,
      fileNo: 0,
      clientName: clientID,
      startDate: form.startDate.value,
      department: form.department.value,
      endDate: form.endDate.value,
      status: form.status.value,
      community: form.community.value,
      date: form.date.value,
      caseworker: form.caseworker.value,
      reason: form.reason.value,
      notes: form.notes.value,
    };
    console.log(formData);
    saveFileDetails(formData).then((res) => {
      console.log(res.data.fhFileDetailsId);
      moduleDispatchContext({
        type: "toggle_tabsDisabled",
        tabsDisabled: false,
      });
      // localStorage.setItem("fhFiledetailsId", String(res.data.fhFileDetailsId));
      navigate(`../referral/${res.data.fhFileDetailsId}`);
    });
  };

  const handleSearch = () => {
    console.log("click search");
    setClick(true);
  };

  return (
    <FhLayout>
      <Box
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
          "> div": { display: "flex", gap: "0 1rem" },
          "> div > div": { flex: "1 1 0" },
        }}
        onChange={(e: SyntheticEvent<HTMLFormElement>) => {
          e.preventDefault();
          const form: any = e.currentTarget;
          if (form.status.value === "Closed") {
            setDisableStatusType(false);
          } else {
            form.date.value = "";
            form.reason.value = "";
            form.notes.value = "";
            setDisableStatusType(true);
          }
        }}
        onSubmit={submitHandler}
        onKeyDown={onKeyDown}
      >
        <div>
          <>
            <EditModeButton
              id={state.fhFileDetailsId}
              disabled={disabled}
              setDisabled={setDisabled}
              fileDetailsPage={true}
              path={`/fh/${id}`}
              moduleName={"fileDetails"}
            />
          </>
        </div>
        <div>
          <div>
            <Box
              sx={{
                display: "flex",
                flexDirection: "row",
                flexWrap: "wrap",
              }}
            >
              <Box
                sx={{
                  p: 1,
                  flexBasis: 0,
                  flexGrow: 1,
                  color: "black",
                }}
              >
                File No.
              </Box>
              <OutlinedInput
                size="small"
                sx={{ borderRadius: 2, flexBasis: 0, flexGrow: 2 }}
                defaultValue={state.fileNo}
                value={state?.fileNo}
                style={{ backgroundColor: "#ffffff" }}
                readOnly
              />
            </Box>
          </div>
          <div>
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
                Client Name
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
          </div>
        </div>
        <div>
          <div>
            <Input
              autofill={state.startDate}
              id="startDate"
              type="date"
              value="Start Date"
              required
              readOnly={disabled}
            />
          </div>
          <div>
            <Input
              autofill={state.endDate}
              id="endDate"
              type="date"
              value="Expected End Date"
              readOnly={disabled}
            />
          </div>
        </div>
        <div>
          <div>
            <Dropdown
              autofill={state.status}
              id="status"
              value="Status"
              optionsList={Object.values(FhReferralStatusCodetable).map(
                (status: any) => status.en
              )}
              required
            />
          </div>
          <div>
            <ICInput
              autofill={state.community}
              id="community"
              value="Community"
              readOnly={disabled}
            />
          </div>
        </div>
        <div>
          <div>
            <ICInput
              autofill={state.department}
              id="department"
              value="Department"
              readOnly={disabled}
            />
          </div>
          <div>
            <ICInput
              autofill={state.caseworker}
              id="caseworker"
              value="Caseworker"
              readOnly={disabled}
            />
          </div>
        </div>
        <Typography variant="body1" color="primary">
          Closure Details
        </Typography>
        <div>
          <div>
            <DateInput
              autofill={state.date}
              id="date"
              type="date"
              value="Date"
              readOnly={disabled}
              disabled={disableStatusType}
            />
          </div>
          <div>
            <ICInput
              autofill={state.reason}
              id="reason"
              value="Reason"
              readOnly={disabled}
              disabled={disableStatusType}
            />
          </div>
        </div>
        <div>
          <TextArea
            formLabelFlex="1 1 0"
            outlinedInputFlex="5.3 1 0"
            autofill={state.notes}
            id="notes"
            value="Notes"
            readOnly={disabled}
            disabled={disableStatusType}
          />
        </div>
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSSaveButton disabled={disabled} />
        </Box>
        {/* <Box sx={{ display: "flex", justifyContent: "right" }}>
          {isInitiated ? (
            <>
              {edit ? (
                <>
                  <CYFSWMSSaveButton />
                </>
              ) : (
                <>
                  <CYFSWMSNextButton onClick={nextClickHandler} />
                </>
              )}
            </>
          ) : (
            <CYFSWMSSaveButton />
          )}
        </Box> */}
      </Box>
      {click && (
        <SearchClientName
          click={click}
          setClick={setClick}
          moduleName="familyHealing"
          searchId="fileDetails"
          setClientName={setClientName}
          setClientId={setClientId}
        />
      )}
    </FhLayout>
  );
};

export default FileDetails;
