import {
  CYFSWMSAddButton,
  CYFSWMSSaveButton,
} from "../../../components/CYFSWMSButtons";
import CYFMSLayout from "../../../components/cyfms/CYFMSLayout";
import RecordList from "../../../components/cyfms/counselors/RecordList";
import {
  ModuleContext,
  ModuleDispatchContext,
} from "../../../contexts/ModuleContext";
import { onKeyDown } from "../../../library/app";
import {
  getCounselorCFSWorker,
  postCounselorCFSWorker,
} from "./counselorsService";
import { Box } from "@mui/material";
import React, { useContext, useEffect, useRef, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { FC } from "react";
import EditModeButton from "../../../components/cyfms/EditModeButton";
import { Counselor } from "./counselorsDatatypes";
import { getRolesCodetable } from "../../../services/codetableService";

/**
 * The Counselors functional component.
 */
const CounselorsPage: FC = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const moduleContext = useContext(ModuleContext);
  const [disabled, setDisabled] = useState(false);
  const formRef = useRef<HTMLFormElement>(null);
  const [rolesCodetable, setRolesCodetable] = useState<any>([]);

  const [data, setData] = useState([
    {
      participantId: Number(id),
      counselorCFSWorkerId: 0,
      role: "",
      name: "",
      startDate: "",
      endDate: "",
      contactInformation: "",
    } as Counselor,
  ]);

  const addFields = () => {
    let object: any = {
      participantId: Number(id),
      counselorCFSWorkerId: 0,
      role: "",
      name: "",
      startDate: "",
      endDate: "",
      contactInformation: "",
    };
    setData([...data, object]);
  };

  useEffect(() => {
    getRolesCodetable().then((response) => {
      setRolesCodetable(response.data.valuesMap);
    });
    getCounselorCFSWorker(Number(id)).then(({ data }) => {
      if (data.length > 0) {
        setData(data);
        setDisabled(true);
      }
    });
  }, []);

  const submitHandler = (event: any) => {
    event.preventDefault();

    const formData: any = [];
    for (let index = 0; index < data.length; ++index) {
      formData[index] = {
        participantId: Number(id),
        counselorCFSWorkerId: data[index].counselorCFSWorkerId,
        role: data[index].role,
        name: data[index].name,
        startDate: data[index].startDate,
        endDate: data[index].endDate,
        contactInformation: data[index].contactInformation,
      };
    }
    postCounselorCFSWorker(formData).then(() => {
      navigate(`/cyfms/other_information/${id}`);
    });
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
              id={data[0].participantId}
              disabled={disabled}
              setDisabled={setDisabled}
              path={`/cyfms/register/${id}`}
              fileDetailsPage={true}
              module="counselors"
            />
          </>
        </div>
        <Box sx={{ display: "flex", flexDirection: "column", gap: "1rem 0" }}>
          <RecordList
            data={data}
            setData={setData}
            rolesCodetable={rolesCodetable}
            disabled={disabled}
          />
        </Box>
        <Box>
          <CYFSWMSAddButton disabled={disabled} onClick={addFields} />
        </Box>
        <Box sx={{ display: "flex", gap: "0 1rem", justifyContent: "right" }}>
          <CYFSWMSSaveButton disabled={disabled} />
        </Box>
      </Box>
    </CYFMSLayout>
  );
};

export default CounselorsPage;
