import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";

import { onKeyDown } from "../../../library/app";
import SearchIcon from "@mui/icons-material/Search";
import { Box, FormControl, FormLabel, OutlinedInput } from "@mui/material";
import React, { useEffect, useState } from "react";
import type { FC, FormEvent } from "react";
import { useNavigate, useParams } from "react-router";
import { readParticiapnts, saveParticiapnts } from "./Service";
import CpaLayout from "../../../components/cpa/CPALayout";
import CPAInput from "../../../components/cpa/Input";
import EditIcon from "../../../components/cpa/participants/EditIcon";
import { Data } from "./ParticipantsDatatypes";
import SearchClientName from "../../../components/cyfms/searchClient/SearchClientName";

const Edit: FC<any> = ({ setAddNew }) => {
  const { childId, id } = useParams();
  const navigate = useNavigate();
  const [click, setClick] = useState(false);
  const [clientName, setClientName] = useState("");
  const [clientID, setClientId] = useState();
  const [disabled, setDisabled] = useState(true);
  const [state, setState] = useState<Data>({
    participantCulturalProId: Number(childId),
    culturalProgramId: Number(id),
    participant: 0,
    role: "",
    notes: "",
  });

  useEffect(() => {
    readParticiapnts(Number(childId)).then(({ data }) => {
      setState(data);
      setClientName(data.participant);
      setClientId(data.participantId);
    });
  }, []);

  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    if (!click) {
      const form = e.currentTarget as HTMLFormElement;
      const formData: Data = {
        participantCulturalProId: Number(childId),
        culturalProgramId: Number(id),
        participant: clientID,
        role: ((form as any).role as any).value as any,
        notes: form.notes.value,
      };
      saveParticiapnts(formData).then(() => {
        navigate(`/cpa/participants/${Number(id)}`);
      });
    }
  };

  const handleSearch = () => {
    if (!disabled) {
      setClick(true);
    }
  };

  return (
    <CpaLayout>
      <Box
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
        }}
        onSubmit={submitHandler}
        //onKeyDown={onKeyDown}
      >
        {disabled && (
          <Box
            sx={{
              display: "flex",
              justifyContent: "flex-end",
            }}
          >
            <EditIcon setDisabled={setDisabled} />
          </Box>
        )}
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <FormControl
              sx={{
                display: "flex",
                flexDirection: "row",
                flexWrap: "wrap",
              }}
            >
              <FormLabel
                sx={{ p: 1, flexBasis: 0, flexGrow: 1.06, color: "black" }}
              >
                Participant
              </FormLabel>
              <OutlinedInput
                sx={{
                  borderRadius: 2,
                  flexBasis: 0,
                  flexGrow: 2,
                }}
                size="small"
                readOnly={disabled}
                value={clientName}
                style={{ backgroundColor: "#ffffff" }}
                endAdornment={<SearchIcon onClick={handleSearch} />}
              />
            </FormControl>
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <CPAInput
              autofill={state.role}
              id="role"
              value="Role"
              type="text"
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
        </Box>
        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5.3 1 0"
          id="notes"
          value="Notes"
          autofill={state.notes}
          readOnly={disabled}
        />
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSNextButton disabled={disabled} />
        </Box>
        {click && (
          <SearchClientName
            click={click}
            setClick={setClick}
            moduleName="cpaParticipant"
            searchId="cpa"
            setClientName={setClientName}
            setClientId={setClientId}
          />
        )}
      </Box>
    </CpaLayout>
  );
};

export { Edit as ParticipantEdit };
