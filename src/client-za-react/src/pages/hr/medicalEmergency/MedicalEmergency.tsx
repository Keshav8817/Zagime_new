import CgLayout from "../../../components/hr/HrLayout";
import SearchIcon from "@mui/icons-material/Search";
import { Box, Typography } from "@mui/material";
import { useNavigate, useParams } from "react-router-dom";
import { onKeyDown } from "../../../library/app";
import React, { useEffect, useState } from "react";
import { Data } from "./medicalEmergencyDatatypes";
import type { FC, FormEvent } from "react";
import TextArea from "../../../components/TextArea";
import EditModeButton from "../../../components/hr/Button/EditModeButton";
import {
  saveMedicalEmergency,
  readMedicalEmergency,
} from "./medicalEmergencyService";
import Input from "../../../components/Input";
import { CYFSWMSSaveButton } from "../../../components/CYFSWMSButtons";
import ICInput from "../../../components/initialContact/Input";

const MedicalEmergency: FC = () => {
  const navigate = useNavigate();
  const [value, setValue] = useState("");
  const { id } = useParams();
  const [disabled, setDisabled] = useState(false);
  const [click, setClick] = useState(false);
  const [state, setState] = useState<Data>({
    staffMedicalAndEmergencyId: 0,
    notes: "",
    name: "",
    address: "",
    phone: "",
    email: "",
    relationship: "",
    alternativeContactMethod: "",
    staffId: Number(id),
  });

  useEffect(() => {
    readMedicalEmergency(Number(id)).then(({ data }) => {
      setState(data);
      if (data.staffMedicalAndEmergencyId !== 0) {
        setDisabled(true);
      }
    });
  }, []);

  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const form: any = e.currentTarget;
    const formData: Data = {
      staffMedicalAndEmergencyId: state.staffMedicalAndEmergencyId,
      notes: form.notes.value,
      name: form.name.value,
      address: form.address.value,
      phone: form.phone.value,
      email: form.email.value,
      relationship: form.relationship.value,
      alternativeContactMethod: form.alternateContactMethod.value,
      staffId: Number(id),
    };
    console.log(formData);
    saveMedicalEmergency(formData).then(() => {
      navigate(`../inventory/${Number(id)}`);
    });
  };

  return (
    <CgLayout>
      <Box
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
          "> div": { display: "flex", gap: "0 1rem" },
          "> div > div": { flex: "1 1 0" },
        }}
        // onSubmit={(event: FormEvent<HTMLFormElement>) =>
        //   handleSubmit(event, navigate, dispatch, staffId, state)
        // }
        onKeyDown={onKeyDown}
        onSubmit={submitHandler}
      >
        <>
          <EditModeButton
            id={state.staffMedicalAndEmergencyId}
            disabled={disabled}
            setDisabled={setDisabled}
            path={`../Staff/${id}`}
            fileDetailsPage={true}
            module="medicalEmergency"
          />
        </>
        <Typography variant="body1" color="primary">
          Medical Information
        </Typography>
        <div>
          <TextArea
            formLabelFlex="1 1 0"
            outlinedInputFlex="5.3 1 0"
            autofill={state.notes}
            id="notes"
            value="Notes"
            readOnly={disabled}
          />
        </div>

        <Typography variant="body1" color="primary">
          Emergency contact
        </Typography>
        <div>
          <div>
            <ICInput
              autofill={state.name}
              id="name"
              value="Name"
              readOnly={disabled}
            />
          </div>
          <div>
            <ICInput
              autofill={state.address}
              id="address"
              value="Address"
              readOnly={disabled}
            />
          </div>
        </div>
        <div>
          <div>
            <ICInput
              autofill={state.phone}
              id="phone"
              validationPattern={`^[^a-zA-Z]*$`}
              validationTitle="Alphabets are not allowed!"
              value="Phone"
              readOnly={disabled}
            />
          </div>
          <div>
            <ICInput
              autofill={state.email}
              id="email"
              type="email"
              value="Email"
              readOnly={disabled}
            />
          </div>
        </div>
        <div>
          <div>
            <ICInput
              autofill={state.relationship}
              id="relationship"
              value="Relationship"
              readOnly={disabled}
            />
          </div>
          <div></div>
        </div>
        <div>
          <TextArea
            formLabelFlex="1 1 0"
            outlinedInputFlex="5.3 1 0"
            autofill={state.alternativeContactMethod}
            id="alternateContactMethod"
            value="Alternate Contact Method"
            readOnly={disabled}
          />
        </div>
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSSaveButton disabled={disabled} />
        </Box>
      </Box>
    </CgLayout>
  );
};

export default MedicalEmergency;
