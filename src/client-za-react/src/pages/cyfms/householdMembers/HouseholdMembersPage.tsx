import {
  CYFSWMSAddButton,
  CYFSWMSSaveButton,
} from "../../../components/CYFSWMSButtons";
import CYFMSLayout from "../../../components/cyfms/CYFMSLayout";
import RecordList from "../../../components/cyfms/householdMembers/RecordList";

import { onKeyDown } from "../../../library/app";
import {
  getHouseHoldMember,
  PostHouseHoldMember,
} from "./householdMembersService";
import { Box } from "@mui/material";
import React, { useEffect, useRef, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { FC } from "react";
import EditModeButton from "../../../components/cyfms/EditModeButton";
import { HouseholdMember } from "./householdMembersDatatypes";
import { getGendersCodetable } from "../../../services/codetableService";

/**
 * The HouseholdMembers functional component.
 */
const HouseholdMembersPage: FC = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [status, setStatus] = useState({});
  const [disabled, setDisabled] = useState(false);
  const formRef = useRef<HTMLFormElement>(null);
  const [data, setData] = useState([
    {
      participantId: Number(id),
      householdMemberId: 0,
      name: "",
      gender: "",
      dateOfBirth: "",
      relationship: "",
      residing: "",
    } as HouseholdMember,
  ]);

  const addFields = () => {
    let object: any = {
      participantId: Number(id),
      householdMemberId: 0,
      name: "",
      gender: "",
      dateOfBirth: "",
      relationship: "",
      residing: "",
    };
    setData([...data, object]);
  };

  useEffect(() => {
    getGendersCodetable().then((data) => {
      setStatus(data.data.valuesMap);
    });
    getHouseHoldMember(Number(id)).then(({ data }) => {
      if (data.length > 0) {
        setData(data);
        setDisabled(true);
      }
    });
  }, []);

  const submithandler = (event: any) => {
    event.preventDefault();

    const formData: any = [];
    for (let index = 0; index < data.length; ++index) {
      formData[index] = {
        participantId: data[index].participantId,
        householdMemberId: data[index].householdMemberId,
        name: data[index].name,
        gender: data[index].gender,
        dateOfBirth: data[index].dateOfBirth,
        relationship: data[index].relationship,
        residing: data[index].residing,
      };
    }
    PostHouseHoldMember(formData).then(() => {
      navigate(`/cyfms/education_and_employment/${id}`);
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
        onSubmit={submithandler}
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
              module="household_members"
            />
          </>
        </div>
        <Box sx={{ display: "flex", flexDirection: "column", gap: "1rem 0" }}>
          <RecordList
            data={data}
            setData={setData}
            status={status}
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

export default HouseholdMembersPage;
