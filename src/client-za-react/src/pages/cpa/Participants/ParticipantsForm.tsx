import {
  CYFSWMSNextButton,
  CYFSWMSSaveButton,
} from "../../../components/CYFSWMSButtons";
import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";
import EditIcon from "../../../components/cpa/participants/EditIcon";
import { onKeyDown } from "../../../library/app";
import SearchIcon from "@mui/icons-material/Search";
import { Box, FormControl, FormLabel, OutlinedInput } from "@mui/material";
import React, { useState } from "react";
import type { FC, FormEvent } from "react";
import CpaLayout from "../../../components/cpa/CPALayout";
import { Data } from "./ParticipantsDatatypes";
import { useNavigate } from "react-router-dom";
import { saveParticiapnts } from "./Service";
import { useSearchParams } from "react-router-dom";
import SearchClientName from "../../../components/cyfms/searchClient/SearchClientName";

const ParticipantsForm: FC<any> = ({ setAddNew, setDisabled, disabled }) => {
  const navigate = useNavigate();
  let [searchParams, setSearchParams] = useSearchParams();
  const [click, setClick] = useState(false);
  const [state, setState] = useState<Data>({
    participantCulturalProId: 0,
    culturalProgramId: 0,
    participant: "",
    role: "",
    notes: "",
  });
  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const form: any = e.currentTarget;
    const formData: Data = {
      participantCulturalProId: state.participantCulturalProId,
      culturalProgramId: 1,
      participant: form.name.participant,
      role: form.role.value,
      notes: form.notes.value,
    };
    saveParticiapnts(formData).then(() => {
      navigate("/cpa/attachments");
    });
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
          ></Box>
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
                //value={data.participant}
                style={{ backgroundColor: "#ffffff" }}
                // endAdornment={<SearchIcon onClick={handleSearch} />}
              />
            </FormControl>
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              // autofill={data.role}
              id="role"
              value="Role"
              type="text"
              //readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
        </Box>
        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5.3 1 0"
          id="notes"
          value="Notes"
          // autofill={data.notes}
          // readOnly={disabled}
        />
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <>
            <CYFSWMSSaveButton />
          </>
        </Box>
        {click && (
          <SearchClientName
            click={click}
            setClick={setClick}
            moduleName="cpaParticipant"
            // searchId="cpa"
          />
        )}
      </Box>
    </CpaLayout>
  );
};

export default ParticipantsForm;
