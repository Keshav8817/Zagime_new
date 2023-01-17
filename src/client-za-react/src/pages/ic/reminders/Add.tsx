import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import Dropdown from "../../../components/Dropdown";
import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";
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
import IcLayout from "../../../components/initialContact/ICLayout";
import SearchClientName from "../../../components/cyfms/searchClient/SearchClientName";
import { Data } from "./ReminderDataTypes";
import { postICReminder } from "./service";
import { useNavigate, useParams } from "react-router";
import {
  getFrequencyCodetable,
  getRemindersStatusCodetable,
} from "../../../services/codetableService";
import { getAllFileDetails } from "../FileDetails/service";
const Add: FC<any> = ({ setAddNew, setDisabled, disabled, targetValue }) => {
  const { id } = useParams();
  const [click, setClick] = useState(false);
  const navigate = useNavigate();
  const [fileNumber, setFileNumber] = useState<Number>();
  const [state, setState] = useState<Data>({
    icReminderId: 0,
    fileDetailsId: Number(id),
    fileNumber: 0,
    reminderDto: {
      reminderId: 0,
      assignedTo: "",
      regarding: 0,
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
  const [clientID, setClientId] = useState(0);
  useEffect(() => {
    getRemindersStatusCodetable().then((data) => {
      setStatus(data.data.valuesMap);
    });
    getFrequencyCodetable().then((data) => {
      setFrequency(data.data.valuesMap);
    });
    getAllFileDetails(Number(id)).then(({ data }) => {
      setFileNumber(data.fileNumber);
    });
  }, []);

  const handleSearch = () => {
    if (!disabled) {
      setClick(true);
    }
  };

  const changeHandler: FormEventHandler<HTMLFormElement> = (event) => {
    const form = event.currentTarget;
    // START: When user selects a date in "Start Date" field then
    // set that selected date as min date in "End Date" field.
    form.endDate.min = form.reminderDate.value;
    // END:
  };

  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    if (!click) {
      const form = e.currentTarget as HTMLFormElement;
      const formData: Data = {
        icReminderId: state.icReminderId,
        fileDetailsId: Number(id),
        fileNumber: state.fileNumber,
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
      postICReminder(formData).then(({ data }) => {
        navigate(`../reminder/${id}`);
      });
    }
  };

  return (
    <>
      <IcLayout>
        <Box
          component="form"
          sx={{
            display: "flex",
            flexDirection: "column",
            gap: "1rem 0",
          }}
          onChange={changeHandler}
          onSubmit={submitHandler}
          onKeyDown={onKeyDown}
        >
          <Typography variant="body1">
            <b>Task information</b>
          </Typography>
          <Typography variant="body1">
            <>File No: {fileNumber}</>
          </Typography>

          <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
              <Input
                id="reminderDate"
                value="Reminder Date"
                type="date"
                required
              />
            </Box>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
              <Input id="assignedTo" value="Assigned To" />
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
                  value={clientName}
                  style={{ backgroundColor: "#ffffff" }}
                  endAdornment={<SearchIcon onClick={handleSearch} />}
                  required
                />
              </FormControl>
            </Box>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
              <Input
                id="subject"
                value="Subject"
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
                required
                autofill={""}
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
          />
          <h4>Recurrence</h4>
          <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
              <Dropdown
                id="frequency"
                value="Frequency"
                autofill={""}
                required
                optionsList={Object.values(frequency).map(
                  (status: any) => status.en
                )}
              />
            </Box>
            <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
              <Input id="endDate" value="End Date" type="date" required />
            </Box>
            {click && (
              <SearchClientName
                click={click}
                setClick={setClick}
                moduleName="initialcontactreminder"
                searchId="icReminderId"
              />
            )}
          </Box>
          <Box sx={{ display: "flex", justifyContent: "right" }}>
            <CYFSWMSNextButton disabled={disabled} />
          </Box>
          {click && (
            <SearchClientName
              click={click}
              setClick={setClick}
              moduleName="initialContact"
              searchId="fileDetails"
              setClientName={setClientName}
              setClientId={setClientId}
            />
          )}
        </Box>
      </IcLayout>
    </>
  );
};

export { Add as ICReminderAdd };
