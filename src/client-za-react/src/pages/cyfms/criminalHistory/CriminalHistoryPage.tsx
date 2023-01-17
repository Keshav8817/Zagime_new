import {
  CYFSWMSAddButton,
  CYFSWMSNextButton,
} from "../../../components/CYFSWMSButtons";
import Input from "../../../components/Input";
import CYFMSLayout from "../../../components/cyfms/CYFMSLayout";
import TextArea from "../../../components/TextArea";
import RecordList from "../../../components/cyfms/criminalHistory/RecordList";
import { onKeyDown } from "../../../library/app";

import { Box, FormGroup } from "@mui/material";
import React, { useEffect, useRef, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type {
  CriminalHistory,
  CriminalHistoryRecord,
} from "./criminalHistoryDatatypes";
import type { FC } from "react";
import {
  getCriminalHistory,
  postCriminalHistory,
} from "./criminalHistoryService";
import CustomCheckbox from "../../../components/initialContact/CheckBox";
import EditModeButton from "../../../components/cyfms/EditModeButton";

/**
 * The CriminalHistory functional component.
 */
const CriminalHistoryPage: FC = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [disabled, setDisabled] = useState(false);
  const formRef = useRef<HTMLFormElement>(null);
  const [data, setData] = useState<CriminalHistory>({
    participantId: Number(id),
    criminalHistoryId: 0,
    criminalHistoryRecordList: [
      {
        criminalHistoryRecordId: 0,
        criminalHistoryId: 0,
        arrestDate: "",
        charges: "",
        conviction: "",
        sentence: "",
      },
    ],
    probation: false,
    parole: false,
    conditions: "",
    courtWorkerAndContactInfo: "",
  });

  useEffect(() => {
    getCriminalHistory(Number(id)).then(({ data }) => {
      if (data.criminalHistoryId !== undefined) {
        setDisabled(true);
        setData(data);
      }
    });
  }, []);

  //Add-More Fields
  const addFields = () => {
    let object: CriminalHistoryRecord = {
      criminalHistoryRecordId: 0,
      criminalHistoryId: 0,
      arrestDate: "",
      charges: "",
      conviction: "",
      sentence: "",
    };
    let addRecord: CriminalHistory = {
      participantId: Number(id),
      criminalHistoryId: data.criminalHistoryId,
      criminalHistoryRecordList: [...data.criminalHistoryRecordList, object],
      probation: data.parole,
      parole: data.probation,
      conditions: data.conditions,
      courtWorkerAndContactInfo: data.courtWorkerAndContactInfo,
    };
    setData(addRecord);
  };

  const submitHandler = (e: any) => {
    e.preventDefault();
    const records: any = [];
    for (
      let index = 0;
      index < data.criminalHistoryRecordList.length;
      ++index
    ) {
      records[index] = {
        criminalHistoryRecordId:
          data.criminalHistoryRecordList[index].criminalHistoryRecordId,
        arrestDate: data.criminalHistoryRecordList[index].arrestDate,
        charges: data.criminalHistoryRecordList[index].charges,
        conviction: data.criminalHistoryRecordList[index].conviction,
        sentence: data.criminalHistoryRecordList[index].sentence,
      };
    }
    const formData: CriminalHistory = {
      participantId: Number(id),
      criminalHistoryId: data.criminalHistoryId,
      probation: data.probation,
      criminalHistoryRecordList: records,
      parole: data.parole,
      conditions: e.currentTarget.conditions.value,
      courtWorkerAndContactInfo:
        e.currentTarget.courtWorkersAndContactInformation.value,
    };
    postCriminalHistory(formData).then((response) => {
      navigate(`/cyfms/family_physicians/${id}`);
    });
  };
  const handleCheckBoxOne = (event: any) => {
    setData({ ...data, probation: event.target.checked });
  };

  const handleCheckBoxTwo = (event: any) => {
    setData({ ...data, parole: event.target.checked });
  };

  return (
    <CYFMSLayout>
      <Box
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
        }}
        onSubmit={submitHandler}
        ref={formRef}
        onKeyDown={onKeyDown}
      >
        <div>
          <>
            <EditModeButton
              id={data.criminalHistoryId}
              disabled={disabled}
              setDisabled={setDisabled}
              path={`/cyfms/register/${id}`}
              fileDetailsPage={true}
              module="criminal_history"
            />
          </>
        </div>
        <Box sx={{ display: "flex", flexDirection: "column", gap: "1rem 0" }}>
          <RecordList data={data} setData={setData} disabled={disabled} />
        </Box>
        <Box>
          <CYFSWMSAddButton disabled={disabled} onClick={addFields} />
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 20rem" }}>
          <FormGroup sx={{ flexBasis: 0, flexGrow: 0 }}>
            <CustomCheckbox
              label="Probation"
              checked={data.probation}
              disabled={disabled}
              onChange={handleCheckBoxOne}
              labelPlacement={"end"}
            />
            <CustomCheckbox
              label="Parole"
              checked={data.parole}
              onChange={handleCheckBoxTwo}
              labelPlacement={"end"}
              disabled={disabled}
            />
          </FormGroup>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              autofill={data.conditions}
              id="conditions"
              value="Condition(s)"
              readOnly={disabled}
            />
          </Box>
        </Box>
        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5.1 1 0"
          autofill={data.courtWorkerAndContactInfo}
          id="courtWorkersAndContactInformation"
          value="Court Worker(s) And Contact Information"
          readOnly={disabled}
        />
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSNextButton disabled={disabled} />
        </Box>
      </Box>
    </CYFMSLayout>
  );
};

export default CriminalHistoryPage;
