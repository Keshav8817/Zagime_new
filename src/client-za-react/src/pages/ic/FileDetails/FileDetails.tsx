import { CYFSWMSSaveButton } from "../../../components/CYFSWMSButtons";
import Input from "../../../components/Input";
import Dropdown from "../../../components/Dropdown";
import ICLayout from "../../../components/initialContact/ICLayout";

import { onKeyDown } from "../../../library/app";
import SearchIcon from "@mui/icons-material/Search";
import { Box, FormControl, FormLabel, OutlinedInput } from "@mui/material";
import React, { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { FormEvent, FC } from "react";
import { getAllFileDetails, postAllFileDetails } from "./service";
import SearchClientName from "../../../components/cyfms/searchClient/SearchClientName";
import { Data } from "./FileDetailsDataType";
import { getIcrStatusCodetable } from "../../../services/codetableService";
import ICInput from "../../../components/initialContact/Input";
import ICDropdown from "../../../components/initialContact/IcDropdown";
import EditModeButton from "../../../components/initialContact/Button";
import { ModuleDispatchContext } from "../../../contexts/ModuleContext";
import DateInput from "../../../components/initialContact/DateInput";
/**
 * `IC` aka `Initial Contact` module.
 * Sub page: `File Details`.
 * @returns `ReactElement`
 */
const FileDetails: any = () => {
  const navigate = useNavigate();
  const moduleDispatchContext = useContext(ModuleDispatchContext);
  const { id } = useParams();
  const [status, setStatus] = useState({});
  const [click, setClick] = useState(false);
  const [clientName, setClientName] = useState("");
  const [clientID, setClientId] = useState();
  const [disableClosingDate, setDisableClosingDate] = useState<boolean>(false);
  const [disabled, setDisabled] = useState(false);
  const [state, setState] = useState<Data>({
    fileDetailsId: Number(id),
    fileNumber: 0,
    clientName: "",
    startingDate: "",
    caseworker: "",
    status: "",
    dateClosed: "",
  });
  useEffect(() => {
    getIcrStatusCodetable().then((data) => {
      setStatus(data.data.valuesMap);
    });

    getAllFileDetails(Number(id))
      .then(({ data }) => {
        setState(data);
        setClientName(data.clientName);
        setClientId(data.participantId);
        setDisableClosingDate(true);
        if (data.fileDetailsId !== 0) {
          setDisabled(true);
        }
      })
      .catch(() => {
        navigate(`/initial_contact`);
      });
  }, []);

  // Handles the form data submission and other
  // activities.
  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const form: any = e.currentTarget;
    const formData: Data = {
      fileDetailsId: Number(id),
      fileNumber: state.fileNumber || 0,
      clientName: clientID,
      startingDate: form.startingDate.value,
      caseworker: form.caseWorker.value,
      status: form.status.value,
      dateClosed: form.closingDate.value,
    };
    postAllFileDetails(formData).then((res) => {
      // moduleDispatchContext({
      //   type: "toggle_tabsDisabled",
      //   tabsDisabled: false,
      // });
      navigate(`../referral_information/${res.data.fileDetailsId}`);
    });
  };
  // Handles the form data submi and other
  // activities.
  const changeHandler = (e: FormEvent) => {
    e.preventDefault();
    const form: any = e.currentTarget;
    if (form.status.value === "Closed") {
      setDisableClosingDate(false);
    } else {
      form.closingDate.value = "";
      setDisableClosingDate(true);
    }
  };

  const nextClickHandler = () => {
    navigate("../referral_information");
  };

  const handleSearch = () => {
    setClick(true);
  };

  return (
    <ICLayout>
      <Box
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
          "> div": { display: "flex", gap: "0 1rem" },
          "> div > div": { flex: "1 1 0" },
        }}
        onSubmit={submitHandler}
        onChange={changeHandler}
        onKeyDown={onKeyDown}
      >
        <div>
          <>
            <EditModeButton
              id={state.fileDetailsId}
              disabled={disabled}
              setDisabled={setDisabled}
              path="/initial_contact"
              fileDetailsPage={true}
              module="file_details"
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
                // readOnly={disabled}
                sx={{ borderRadius: 2, flexBasis: 0, flexGrow: 2 }}
                defaultValue={state.fileNumber}
                value={state?.fileNumber}
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
                readOnly={disabled}
                style={{ backgroundColor: "#ffffff" }}
                endAdornment={<SearchIcon onClick={handleSearch} />}
              />
            </FormControl>
          </div>
        </div>
        <div>
          <div>
            <DateInput
              autofill={state.startingDate}
              id="startingDate"
              value="Date"
              type="date"
              readOnly={disabled}
              required
            />
          </div>
          <div>
            <ICInput
              autofill={state.caseworker}
              id="caseWorker"
              value="Case Worker"
              readOnly={disabled}
              required
            />
          </div>
        </div>
        <div>
          <div>
            <Dropdown
              autofill={state.status}
              id="status"
              optionsList={Object.values(status).map(
                (status: any) => status.en
              )}
              value="Status"
              disabled={disabled}
            />
          </div>
          <div>
            <DateInput
              autofill={state.dateClosed}
              id="closingDate"
              maxDate={new Date().toISOString().substring(0, 10)}
              minDate="1900-01-01"
              value="Date Closed"
              disabled={disableClosingDate}
              type="date"
            />
          </div>
        </div>
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <>
            <CYFSWMSSaveButton disabled={disabled} />
          </>
        </Box>
      </Box>
      {click && (
        <SearchClientName
          click={click}
          setClick={setClick}
          moduleName="initialContact"
          searchId="fileDetails"
          setClientName={setClientName}
          setClientId={setClientId}
        />
      )}
    </ICLayout>
  );
};

export default FileDetails;
