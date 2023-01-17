import Checkbox from "../../../components/Checkbox";
import { CYFSWMSSaveButton } from "../../../components/CYFSWMSButtons";
import FileInput from "../../../components/FileInput";
import Input from "../../../components/Input";
import CYFMSDropdown from "../../../components/Dropdown";
import CYFMSLayout from "../../../components/cyfms/CYFMSLayout";
import {
  ModuleContext,
  ModuleDispatchContext,
} from "../../../contexts/ModuleContext";
import { onKeyDown } from "../../../library/app";
import { handleEffect, handleSubmit } from "./registerService";
import DeleteIcon from "@mui/icons-material/Delete";
import { Box, Typography } from "@mui/material";
import React, { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { Register } from "./registerDatatypes";
import type { FC } from "react";
import EditModeButton from "../../../components/cyfms/EditModeButton";

/**
 * The Register functional component.
 */
const RegisterPage: FC = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const moduleContext = useContext(ModuleContext);
  const moduleDispatchContext = useContext(ModuleDispatchContext);
  const [disabled, setDisabled] = useState(false);
  const [gendersCodetable, setGendersCodetable] = useState<any>([]);
  const [maritalstatusCodetable, setMaritalStatusCodetable] = useState<any>([]);
  const [data, setData] = useState<Register>({
    participantId: Number(id),
    referenceId: 0,
    firstname: "",
    middleName: "",
    surname: "",
    dateOfBirth: "",
    gender: "",
    maritalStatus: "",
    participantImageId: 0,
    image: "",
    type: "",
    participantImageName: "",
  });

  useEffect(
    () =>
      handleEffect(
        moduleContext,
        moduleDispatchContext,
        setGendersCodetable,
        setMaritalStatusCodetable,
        data,
        setData,
        setDisabled
      ),
    []
  );

  return (
    <CYFMSLayout>
      <Box
        component="form"
        sx={{
          height: "100%",
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
          "> div": { display: "flex", gap: "0 1rem" },
          "> div > div": { flex: "1 1 0" },
        }}
        onSubmit={(event) =>
          handleSubmit(
            event,
            navigate,
            moduleContext,
            moduleDispatchContext,
            data
          )
        }
        onKeyDown={onKeyDown}
      >
        <div>
          <>
            <EditModeButton
              id={data.participantId}
              disabled={disabled}
              setDisabled={setDisabled}
              path={"/cyfms"}
              fileDetailsPage={true}
              module="register"
            />
          </>
        </div>
        <Typography paddingLeft={1}>
          Reference ID : {data.referenceId}
        </Typography>
        <div>
          <div>
            <Input
              autofill={data.firstname}
              id="firstName"
              value="First Name"
              validationPattern={`^[a-zA-Z ]*$`}
              validationTitle="Digits are not allowed!"
              required
              readOnly={disabled}
            />
          </div>
          <div>
            <Input
              autofill={data.middleName}
              id="middleName"
              value="Middle Name"
              validationPattern={`^[a-zA-Z ]*$`}
              validationTitle="Digits are not allowed!"
              readOnly={disabled}
            />
          </div>
        </div>
        <div>
          <div>
            <Input
              autofill={data.surname}
              id="lastName"
              value="Last Name"
              validationPattern={`^[a-zA-Z ]*$`}
              validationTitle="Digits are not allowed!"
              required
              readOnly={disabled}
            />
          </div>
          <div>
            <Input
              autofill={data.dateOfBirth}
              id="dateOfBirth"
              maxDate={new Date().toISOString().substring(0, 10)}
              minDate="1900-01-01"
              type="date"
              value="Date of Birth"
              required
              readOnly={disabled}
            />
          </div>
        </div>
        <div>
          <div>
            <CYFMSDropdown
              autofill={data.gender}
              id="gender"
              optionsList={Object.values(gendersCodetable).map(
                (gender: any) => gender.en
              )}
              value="Gender"
              required
              // disabled={/*state.editMode*/ false}
              disabled={disabled}
            />
          </div>
          <div>
            <CYFMSDropdown
              autofill={data.maritalStatus}
              id="maritalStatus"
              optionsList={Object.values(maritalstatusCodetable).map(
                (status: any) => status.en
              )}
              value="Marital Status"
              // disabled={/*state.editMode*/ false}
              disabled={disabled}
            />
          </div>
        </div>
        <div>
          <div>
            <FileInput id="imageFile" value="Photograph" readOnly={disabled} />
          </div>
          <div>
            <Checkbox
              id="removeProfilePicture"
              disabled={disabled}
              icon={<DeleteIcon />}
              checkedIcon={<DeleteIcon color="error" />}
            />
          </div>
        </div>
        <Box sx={{ justifyContent: "right" }}>
          <CYFSWMSSaveButton disabled={disabled} />
        </Box>
      </Box>
    </CYFMSLayout>
  );
};

export default RegisterPage;
