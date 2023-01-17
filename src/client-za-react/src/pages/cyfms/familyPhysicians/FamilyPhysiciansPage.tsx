import {
  CYFSWMSAddButton,
  CYFSWMSSaveButton,
} from "../../../components/CYFSWMSButtons";
import CYFMSLayout from "../../../components/cyfms/CYFMSLayout";
import RecordList from "../../../components/cyfms/familyPhysicians/RecordList";
import {
  ModuleContext,
  ModuleDispatchContext,
} from "../../../contexts/ModuleContext";
import { onKeyDown } from "../../../library/app";
import {
  postFamilyPhysician,
  getFamilyPhysician,
} from "./familyPhysiciansService";
import { Box } from "@mui/material";
import React, { useContext, useEffect, useRef, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { FC } from "react";
import EditModeButton from "../../../components/cyfms/EditModeButton";
import { FamilyPhysician } from "./familyPhysiciansDatatypes";

/**
 * The FamilyPhysicians functional component.
 */
const FamilyPhysiciansPage: FC = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const moduleContext = useContext(ModuleContext);
  const [disabled, setDisabled] = useState(false);
  const formRef = useRef<HTMLFormElement>(null);
  const [data, setData] = useState([
    {
      participantId: Number(id),
      familyPhysicianId: 0,
      name: "",
      phone: "",
      cell: "",
      listOfMedication: "",
    } as FamilyPhysician,
  ]);

  const addFields = () => {
    let object: any = {
      participantId: Number(id),
      familyPhysicianId: 0,
      name: "",
      phone: "",
      cell: "",
      listOfMedication: "",
    };
    setData([...data, object]);
  };

  useEffect(() => {
    getFamilyPhysician(Number(id)).then(({ data }) => {
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
        familyPhysicianId: data[index].familyPhysicianId,
        name: data[index].name,
        phone: data[index].phone,
        cell: data[index].cell,
        listOfMedication: data[index].listOfMedication,
      };
    }
    postFamilyPhysician(formData).then(() => {
      navigate(`/cyfms/counselors/${id}`);
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
              module="family_physicians"
            />
          </>
        </div>
        <Box sx={{ display: "flex", flexDirection: "column", gap: "1rem 0" }}>
          <RecordList data={data} setData={setData} disabled={disabled} />
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

export default FamilyPhysiciansPage;
