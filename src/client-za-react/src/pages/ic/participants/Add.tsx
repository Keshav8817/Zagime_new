import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import TextArea from "../../../components/TextArea";

import SearchIcon from "@mui/icons-material/Search";
import { Box, FormControl, FormLabel, OutlinedInput } from "@mui/material";
import React, { useState } from "react";
import type { FC, FormEvent } from "react";
import { Data } from "./ParticipantDataType";
import { postICParticipant } from "./service";
import SearchClientName from "../../../components/cyfms/searchClient/SearchClientName";
import IcLayout from "../../../components/initialContact/ICLayout";
import { useNavigate, useParams } from "react-router";
import ICInput from "../../../components/initialContact/Input";

const Add: FC<any> = ({ setAddNew, setDisabled, disabled }) => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [click, setClick] = useState(false);
  const [clientName, setClientName] = useState("");
  const [clientID, setClientId] = useState(null);
  const [state, setState] = useState<Data>({
    icParticipantId: 0,
    fileDetailsId: Number(id),
    participant: 0,
    role: "",
    notes: "",
  });

  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    if (!click) {
      const form = e.currentTarget as HTMLFormElement;
      const formData: Data = {
        icParticipantId: state.icParticipantId || 0,
        fileDetailsId: Number(id),
        participant: clientID,
        role: ((form as any).role as any).value as any,
        notes: form.notes.value,
      };
      postICParticipant(formData).then(() => {
        navigate(`../participants/${Number(id)}`);
      });
    }
  };

  const handleSearch = () => {
    setClick(true);
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
                value={clientName}
                size="small"
                style={{ backgroundColor: "#ffffff" }}
                endAdornment={<SearchIcon onClick={handleSearch} />}
              />
            </FormControl>
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput id="role" value="Role" type="text" autofill={state.role} />
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

export { Add as ParticipantAdd };
