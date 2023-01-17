import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import Dropdown from "../../../components/Dropdown";
import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";
import EditIcon from "../../../components/cyfms/remainders/EditIcon";
import { onKeyDown } from "../../../library/app";
import SearchIcon from "@mui/icons-material/Search";
import {
  Box,
  FormControl,
  FormLabel,
  OutlinedInput,
  Typography,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import type { FC, FormEvent, FormEventHandler } from "react";

import SearchClientName from "../../../components/cyfms/searchClient/SearchClientName";
import { Data } from "./RemindersDatatypes";
import { getHrReminder, postHrReminder } from "./service";
import { useNavigate, useParams } from "react-router";
import {
  getFrequencyCodetable,
  getRemindersStatusCodetable,
} from "../../../services/codetableService";
import { getIdentity } from "../register/registerService";
import CYFMSLayout from "../../../components/cyfms/CYFMSLayout";
const Edit: FC<any> = ({ setAddNew, targetValue }) => {
  const navigate = useNavigate();
  const { id, childId, childId1 } = useParams();
  const [click, setClick] = useState(false);
  const [disabled, setDisabled] = useState(true);
  const [referenceId, setReferenceId] = useState<Number>();
  const [state, setState] = useState({
    participantReminderId: Number(childId),
    participantId: Number(id),
    referenceId: 0,
    reminderDto: {
      reminderId: 0,
      assignedTo: "",
      regarding: "",
      subject: "",
      status: "",
      reminderDate: "",
      endDate: "",
      description: "",
      frequency: "",
    },
  });
  const [status, setStatus] = useState({});
  const [frequency, setFrequency] = useState({});
  const [clientName, setClientName] = useState("");
  const [clientID, setClientId] = useState();
  useEffect(() => {
    getRemindersStatusCodetable().then((data) => {
      setStatus(data.data.valuesMap);
    });
    getFrequencyCodetable().then((data) => {
      setFrequency(data.data.valuesMap);
    });
    getHrReminder(Number(childId)).then(({ data }) => {
      setState(data);
      console.log(data);
      setClientId(data.reminderDto.participantId);
      setClientName(data.reminderDto.regarding);
    });
    getIdentity(Number(id)).then(({ data }) => {
      setReferenceId(data.referenceId);
    });
  }, []);

  const handleSearch = () => {
    if (!disabled) {
      setClick(true);
    }
  };

  const changeHandler: FormEventHandler<HTMLFormElement> = (event) => {
    const form = event.currentTarget as HTMLFormElement;
    const formData: Data = {
      participantReminderId: Number(childId),
      participantId: Number(id),
      referenceId: state.referenceId,
      reminderDto: {
        reminderId: Number(childId1),
        assignedTo: form.assignedTo.value,
        regarding: clientID,
        subject: form.subject.value,
        status: form.status.value,
        reminderDate: form.reminderDate.value,
        endDate: form.endDate.value,
        description: form.description.value,
        frequency: form.frequency.value,
      },
    };
    setState(formData);
  };

  useEffect(() => {
    getFrequencyCodetable().then((data) => {
      setFrequency(data.data.valuesMap);
    });
    getRemindersStatusCodetable().then((data) => {
      setStatus(data.data.valuesMap);
    });
  }, []);

  const submitHandler = (e: FormEvent) => {
    e.preventDefault();

    if (!click) {
      const form = e.currentTarget as HTMLFormElement;
      const formData: Data = {
        participantReminderId: Number(childId),
        participantId: Number(id),
        referenceId: state.referenceId,
        reminderDto: {
          reminderId: state.reminderDto.reminderId,
          assignedTo: form.assignedTo.value,
          regarding: clientID,
          subject: form.subject.value,
          status: form.status.value,
          reminderDate: form.reminderDate.value,
          endDate: form.endDate.value,
          description: form.description.value,
          frequency: form.frequency.value,
        },
      };
      postHrReminder(formData).then(() => {
        navigate(`../reminder/${id}`);
      });
    }
  };

  return (
    <>
      <CYFMSLayout>
        <Box
          component="form"
          sx={{
            display: "flex",
            flexDirection: "column",
            gap: "1rem 0",
          }}
          // onChange={changeHandler}
          onSubmit={submitHandler}
          onKeyDown={onKeyDown}
        >
          {disabled && (
            <Box
              sx={{
                display: "flex",
                justifyContent: "flex-end",
              }}
            >
              <EditIcon
                setDisabled={setDisabled}
                participantReminderId={state.participantReminderId}
              />
            </Box>
          )}
          <Typography variant="body1">
            <b>Task information</b>
          </Typography>

          <Typography sx={{ p: 1 }}>
            <>Reference ID: {referenceId}</>
          </Typography>

          <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
              <Input
                id="reminderDate"
                value="Reminder Date"
                type="date"
                required
                autofill={state.reminderDto.reminderDate}
                readOnly={disabled}
              />
            </Box>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
              <Input
                id="assignedTo"
                value="Assigned To"
                autofill={state.reminderDto.assignedTo}
                readOnly={disabled}
              />
            </Box>
          </Box>
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
                  Regarding
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
                  //   required
                />
              </FormControl>
            </Box>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
              <Input
                id="subject"
                value="Subject"
                autofill={state.reminderDto.subject}
                readOnly={disabled}
                required
              />
            </Box>
          </Box>
          <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
              <Dropdown
                id="status"
                value="Status"
                autofill={state.reminderDto.status}
                readOnly={disabled}
                required
                optionsList={Object.values(status).map(
                  (status: any) => status.en
                )}
              />
            </Box>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
          </Box>
          <TextArea
            formLabelFlex="1 1 0"
            outlinedInputFlex="5.3 1 0"
            id="description"
            value="Description"
            autofill={state.reminderDto.description}
            readOnly={disabled}
          />
          <h4>Reccurance</h4>
          <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
              <Dropdown
                id="frequency"
                value="Frequency"
                autofill={state.reminderDto.frequency}
                readOnly={disabled}
                required
                optionsList={Object.values(frequency).map(
                  (status: any) => status.en
                )}
              />
            </Box>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
              <Input
                id="endDate"
                value="End Date"
                type="date"
                required
                autofill={state.reminderDto.endDate}
                readOnly={disabled}
              />
            </Box>
            {click && (
              <SearchClientName
                click={click}
                setClick={setClick}
                moduleName="initialcontactreminder"
                searchId="icReminderId"
                setClientName={setClientName}
                setClientId={setClientId}
              />
            )}
          </Box>
          <Box sx={{ display: "flex", justifyContent: "right" }}>
            <CYFSWMSNextButton disabled={disabled} />
          </Box>
        </Box>
      </CYFMSLayout>
    </>
  );
};

export { Edit as CyfmsReminderEdit };
