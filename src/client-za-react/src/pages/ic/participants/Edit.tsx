import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import TextArea from "../../../components/TextArea";
import EditIcon from "../../../components/initialContact/participants/EditIcon";

import SearchIcon from "@mui/icons-material/Search";
import { Box, FormControl, FormLabel, OutlinedInput } from "@mui/material";
import React, { useEffect, useState } from "react";
import type { FC, FormEvent } from "react";
import { Data } from "./ParticipantDataType";
import { getICParticipant, postICParticipant } from "./service";
import SearchClientName from "../../../components/cyfms/searchClient/SearchClientName";
import IcLayout from "../../../components/initialContact/ICLayout";
import { useNavigate, useParams } from "react-router";
import ICInput from "../../../components/initialContact/Input";

const Edit: FC<any> = ({ setAddNew }) => {
  const { childId, id } = useParams();
  const navigate = useNavigate();
  const [click, setClick] = useState(false);
  const [clientName, setClientName] = useState("");
  const [clientID, setClientId] = useState();
  const [disabled, setDisabled] = useState(true);
  const [state, setState] = useState<Data>({
    icParticipantId: Number(childId),
    fileDetailsId: Number(id),
    participant: 0,
    role: "",
    notes: "",
  });

  useEffect(() => {
    getICParticipant(Number(childId)).then(({ data }) => {
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
        icParticipantId: Number(childId),
        fileDetailsId: Number(id),
        participant: clientID,
        role: ((form as any).role as any).value as any,
        notes: form.notes.value,
      };
      postICParticipant(formData).then(() => {
        navigate(`/initial_contact/participants/${Number(id)}`);
      });
    }
  };

  const handleSearch = () => {
    if (!disabled) {
      setClick(true);
    }
  };

  return (
    <IcLayout>
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
            <ICInput
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
            moduleName="initialContact"
            searchId="icParticipant"
            setClientName={setClientName}
            setClientId={setClientId}
          />
        )}
      </Box>
    </IcLayout>
  );
};

export { Edit as ParticipantEdit };
