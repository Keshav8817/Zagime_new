import { CYFSWMSSaveButton } from "../../../components/CYFSWMSButtons";
import Dropdown from "../../../components/Dropdown";
import Input from "../../../components/Input";
import HrLayout from "../../../components/hr/HrLayout";
import { onKeyDown } from "../../../library/app";
import { saveStaff, readStaff } from "./staffService";
import React, { FormEvent, useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { FC } from "react";
import { Box, FormControl, FormLabel, OutlinedInput } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import { setConstantValue } from "typescript";
import { Data } from "./staffDatatypes";
import { getStaffStatusCodetable } from "../../../services/codetableService";
import EditModeButton from "../../../components/hr/Button/EditModeButton";
import ICInput from "../../../components/initialContact/Input";
import { ModuleDispatchContext } from "../../../contexts/ModuleContext";
import SearchClientName from "../../../components/hr/searchClient/searchCient";
import DateInput from "../../../components/initialContact/DateInput";
/**
 * `HR` aka `Human Resources` module.
 * Sub page: `StaffPage`.
 */
const StaffPage: FC = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [disabled, setDisabled] = useState(false);
  const [staffStatusCodetable, setStaffStatusCodetable] = useState<any>([]);
  const [click, setClick] = useState(false);
  const [clientID, setClientId] = useState();
  const [clientName, setClientName] = useState("");
  const moduleDispatchContext = useContext(ModuleDispatchContext);
  const [state, setState] = useState<Data>({
    staffId: Number(id),
    firstName: "",
    middleName: "",
    lastName: "",
    dateOfBirth: "",
    department: "",
    status: "",
    workLocation: "",
    supervisor: "",
    employeeId: "",
  });

  useEffect(() => {
    readStaff(Number(id)).then(({ data }) => {
      setState(data);
      setClientName(data.supervisor);
      console.log(data);
      setClientId(data.superviserId);
      if (data.staffId !== 0) {
        setDisabled(true);
      }
    });
  }, []);
  useEffect(() => {
    getStaffStatusCodetable().then((response) => {
      setStaffStatusCodetable(response.data.valuesMap);
    });
  }, []);
  const handleSearch = () => {
    setClick(true);
  };
  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const form: any = e.currentTarget;
    const formData: Data = {
      staffId: Number(id),
      firstName: form.firstName.value,
      middleName: form.middleName.value,
      lastName: form.lastName.value,
      dateOfBirth: form.dateOfBirth.value,
      department: form.department.value,
      status: form.status.value,
      workLocation: form.worklocation.value,
      supervisor: clientID,
      employeeId: form.employeeId.value,
    };
    console.log(formData);
    saveStaff(formData).then((res) => {
      moduleDispatchContext({
        type: "toggle_tabsDisabled",
        tabsDisabled: false,
      });
      navigate(`../contact_information/${res.data.staffId}`);
    });
  };
  return (
    <HrLayout>
      <Box
        component="form"
        sx={{
          height: "100%",
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
          "> div": { display: "flex", gap: "0 1rem" },
          "> div > div": { flex: "1 1 0" },
        }}
        onKeyDown={onKeyDown}
        onSubmit={submitHandler}
      >
        <>
          <EditModeButton
            id={state.staffId}
            disabled={disabled}
            setDisabled={setDisabled}
            path="/hr"
            fileDetailsPage={true}
            module="staff"
          />
        </>
        <div>
          <div>
            <ICInput
              autofill={state.employeeId}
              id="employeeId"
              value="Employee Id"
              // disabled={state.disableData}
              readOnly={disabled}
              required
            />
          </div>
          <div></div>
        </div>
        <div>
          <div>
            <ICInput
              autofill={state.firstName}
              id="firstName"
              value="First Name"
              required
              //readOnly={state.disableData}
              readOnly={disabled}
            />
          </div>
          <div>
            <ICInput
              autofill={state.middleName}
              id="middleName"
              value="Middle Name"
              //readOnly={state.disableData}
              readOnly={disabled}
            />
          </div>
        </div>
        <div>
          <div>
            <ICInput
              autofill={state.lastName}
              id="lastName"
              value="Last Name"
              required
              // readOnly={state.disableData}
              readOnly={disabled}
            />
          </div>
          <div>
            <DateInput
              autofill={state.dateOfBirth}
              id="dateOfBirth"
              value="Date of Birth"
              validationPattern={`^[a-zA-Z ]*$`}
              validationTitle="Digits are not allowed!"
              type="date"
              required
              // readOnly={state.disableData}
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
              // readOnly={state.disableData}
              readOnly={disabled}
            />
          </div>
          <div>
            <Dropdown
              autofill={state.status}
              id="status"
              value="Status"
              optionsList={Object.values(staffStatusCodetable).map(
                (type: any) => type.en
              )}
              // readOnly={state.disableData}
              disabled={disabled}
            />
          </div>
        </div>
        <div>
          <div>
            <ICInput
              autofill={state.workLocation}
              id="worklocation"
              value="Work Location"
              // readOnly={state.disableData}
              readOnly={disabled}
            />
          </div>
          <div>
            {/* <ICInput
              autofill={state.supervisor}
              id="supervisor"
              value="Supervisor"
              // readOnly={state.disableData}
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
          </div>
        </div>

        <Box sx={{ justifyContent: "right" }}>
          <CYFSWMSSaveButton disabled={disabled} />
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
    </HrLayout>
  );
};

export default StaffPage;
