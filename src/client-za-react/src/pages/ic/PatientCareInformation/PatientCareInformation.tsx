// import { onKeyDown } from "../../library/app";
// import { useAppDispatch, useAppSelector } from "../../library/hooks";
import { Box } from "@mui/material";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { FormEvent, ReactElement } from "react";
import { Data } from "./PatientCareInformationDataType";
import ICLayout from "../../../components/initialContact/ICLayout";
import Inpatient from "../../../components/initialContact/patientCareInformation/Inpatient";
import Outpatient from "../../../components/initialContact/patientCareInformation/Outpatient";
import { CYFSWMSSaveButton } from "../../../components/CYFSWMSButtons";
import { getIcrTypesOfPatientsCodetable } from "../../../services/codetableService";
import { getAllPatientCareInfo, postPatientCareInfo } from "./service";
import EditModeButton from "../../../components/initialContact/Button";
import Dropdown from "../../../components/Dropdown";

/**
 * The PatientCareInformation functional component.
 * @returns PatientCareInformation component skeleton.
 */
const PatientCareInformation = (): ReactElement => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [typeOfPatient, setTypeOfPatient] = useState("");
  const [disabled, setDisabled] = useState(false);
  const [state, setState] = useState<Data>({
    fileDetailsId: Number(id),
    patientCareInfoId: 0,
    typeOfPatient: "",
    outpatient: {
      outpatientId: 0,
      therapyOrCounseling: "",
      therapyTimePeriod: "",
      therapyLocation: "",
      reasonForTherapy: "",
      selfHelpGroup: "",
      selfHelpGroupPeriod: "",
      selfHelpGroupLocation: "",
    },
    inpatient: {
      inpatientId: 0,
      hospitalizationRecord: "",
      hospitalizationReasons: "",
    },
  });
  const [patient, setPatient] = useState({});

  useEffect(() => {
    getIcrTypesOfPatientsCodetable().then((data) => {
      setPatient(data.data.valuesMap);
    });

    getAllPatientCareInfo(Number(id)).then(({ data }) => {
      if (Object.keys(data).length !== 0) {
        setTypeOfPatient(data.typeOfPatient);
        setState(data);

        if (data.patientCareInfoId !== 0) {
          setDisabled(true);
        }
      }
    });
  }, []);
  // Handles the form data submission and other
  // activities.
  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const form: any = e.currentTarget;
    if (typeOfPatient === "Inpatient") {
      const myData: any = {
        fileDetailsId: Number(id),
        patientCareInfoId: state.patientCareInfoId
          ? state.patientCareInfoId
          : 0,
        typeOfPatient: "Inpatient",
        inpatient: {
          inpatientId: state.inpatient?.inpatientId
            ? state.inpatient.inpatientId
            : 0,
          hospitalizationRecord: form.hospitalizationRecord
            ? form.hospitalizationRecord.value
            : state.inpatient.hospitalizationRecord,
          hospitalizationReasons: form.hospitalizationReasons
            ? form.hospitalizationReasons.value
            : state.inpatient.hospitalizationReasons,
        },
      };
      postPatientCareInfo(myData).then(() => {
        navigate(`../file_details/${id}`);
      });
    } else if (typeOfPatient === "Outpatient") {
      const myData: any = {
        fileDetailsId: Number(id),
        patientCareInfoId: state.patientCareInfoId
          ? state.patientCareInfoId
          : 0,
        typeOfPatient: "Outpatient",
        outpatient: {
          outpatientId: state.outpatient?.outpatientId
            ? state.outpatient.outpatientId
            : 0,
          therapyOrCounseling: form.therapyOrCounseling.value
            ? form.therapyOrCounseling.value
            : state.outpatient.therapyOrCounseling,
          therapyTimePeriod: form.therapyTimePeriod
            ? form.therapyTimePeriod.value
            : state.outpatient.therapyTimePeriod,
          therapyLocation: form.therapyLocation
            ? form.therapyLocation.value
            : state.outpatient.therapyLocation,
          reasonForTherapy: form.reasonForTherapy
            ? form.reasonForTherapy.value
            : state.outpatient.reasonForTherapy,
          selfHelpGroup: form.selfHelpGroup
            ? form.selfHelpGroup.value
            : state.outpatient.selfHelpGroup,
          selfHelpGroupPeriod: form.selfHelpGroupPeriod
            ? form.selfHelpGroupPeriod.value
            : state.outpatient.selfHelpGroupPeriod,
          selfHelpGroupLocation: form.selfHelpGroupLocation
            ? form.selfHelpGroupLocation.value
            : state.outpatient.selfHelpGroupLocation,
        },
      };

      postPatientCareInfo(myData).then(() => {
        navigate(`../file_details/${id}`);
      });
    } else if (typeOfPatient === "") {
      const myData: any = {
        fileDetailsId: Number(id),
        patientCareInfoId: state.patientCareInfoId
          ? state.patientCareInfoId
          : 0,
      };

      postPatientCareInfo(myData).then(() => {
        navigate(`../file_details/${id}`);
      });
    }
  };

  // Handles the form fields' value changes
  // and other activities.
  const changeHandler = (e: FormEvent) => {
    e.preventDefault();
    const form: any = e.currentTarget;
    if (form.typeOfPatient.value === "Inpatient") {
      setTypeOfPatient("Inpatient");
    } else if (form.typeOfPatient.value === "Outpatient") {
      setTypeOfPatient("Outpatient");
    } else {
      setTypeOfPatient("");
    }
  };

  return (
    <ICLayout>
      <Box
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
        }}
        onSubmit={submitHandler}
        onChange={changeHandler}
      >
        <div>
          <>
            <EditModeButton
              id={state.patientCareInfoId}
              setDisabled={setDisabled}
              disabled={disabled}
              path={`/initial_contact/file_details/${id}`}
            />
          </>
        </div>
        <Box sx={{ display: "flex", flexDirection: "column", gap: "1rem 0" }}>
          <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
              <Dropdown
                autofill={state.typeOfPatient}
                id="typeOfPatient"
                value="Type of Patient"
                disabled={disabled}
                optionsList={Object.values(patient).map(
                  (status: any) => status.en
                )}
              />
            </Box>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
          </Box>
          {typeOfPatient === "Outpatient" ? (
            <Outpatient data={state} disabled={disabled} />
          ) : (
            ""
          )}
          {typeOfPatient === "Inpatient" ? (
            <Inpatient data={state} disabled={disabled} />
          ) : (
            ""
          )}
          {typeOfPatient === "" ? "" : ""}
        </Box>
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSSaveButton disabled={disabled} />
        </Box>
      </Box>
    </ICLayout>
  );
};

export default PatientCareInformation;
