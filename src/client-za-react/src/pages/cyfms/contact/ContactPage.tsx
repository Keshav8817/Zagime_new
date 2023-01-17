import { CYFSWMSSaveButton } from "../../../components/CYFSWMSButtons";
import Input from "../../../components/Input";
import Dropdown from "../../../components/DropdownTwo";
import CYFMSLayout from "../../../components/cyfms/CYFMSLayout";
import {
  ModuleContext,
  ModuleDispatchContext,
} from "../../../contexts/ModuleContext";
import { onKeyDown } from "../../../library/app";
import { getContact, postContact } from "./contactService";
import { Box } from "@mui/material";
import React, { FormEvent, useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  Codetable,
  getProvinceCodetable,
} from "../../../services/codetableService";
import type { Contact } from "./contactDatatypes";
import type { FC } from "react";
import EditModeButton from "../../../components/cyfms/EditModeButton";

/**
 * The Contact functional component.
 */
const ContactPage: FC = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const moduleContext = useContext(ModuleContext);
  const moduleDispatchContext = useContext(ModuleDispatchContext);
  const [participantId, setParticipantId] = useState<number>(0);
  const [contactId, setContactId] = useState<number>(0);
  const [disabled, setDisabled] = useState(false);
  const [provinceCodetable, setProvinceCodetable] = useState<any>([]);
  const [data, setData] = useState({
    participantId: Number(id),
    participantContactId: 0,
    addressLine1: "",
    addressLine2: "",
    city: "",
    province: "",
    postalCode: "",
    homePhone: "",
    workPhone: "",
    cellPhone: "",
    emailAddress: "",
  });

  // useEffect(
  //   () =>
  //     handleEffect(moduleContext, moduleDispatchContext, setProvinceCodetable),
  //   []
  //   // [moduleContext.data?.participantId]
  // );

  useEffect(() => {
    getProvinceCodetable().then((response) =>
      setProvinceCodetable(response.data.valuesMap)
    );
    getContact(Number(id)).then(({ data }) => {
      setData(data);
      if (data.participantContactId !== 0) {
        setDisabled(true);
      }
    });
  }, []);

  const handleSubmit = (event: FormEvent) => {
    event.preventDefault();
    const form: any = event.currentTarget;
    const formData: Contact = {
      participantId: Number(id),
      participantContactId: data.participantContactId,
      addressLine1: form.addressLine1.value,
      addressLine2: form.addressLine2.value,
      city: form.city.value,
      province: form.province.value,
      postalCode: form.postalCode.value,
      homePhone: form.homePhone.value,
      workPhone: form.workPhone.value,
      cellPhone: form.cellPhone.value,
      emailAddress: form.emailAddress.value,
    };
    postContact(formData).then(() => {
      navigate(`/cyfms/household_members/${id}`);
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
        onSubmit={
          (event) => handleSubmit(event)
          //handleSubmit(event, navigate, moduleContext, moduleDispatchContext)
        }
        onKeyDown={onKeyDown}
      >
        <div>
          <>
            <EditModeButton
              id={data?.participantContactId}
              disabled={disabled}
              setDisabled={setDisabled}
              path={`/cyfms/register/${id}`}
              fileDetailsPage={true}
              module="contact"
            />
          </>
        </div>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              autofill={data?.addressLine1}
              id="addressLine1"
              value="Address Line 1"
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              autofill={data?.addressLine2}
              id="addressLine2"
              value="Address Line 2"
              readOnly={disabled}
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              autofill={data?.city}
              id="city"
              validationPattern={`^[a-zA-Z ]*$`}
              validationTitle="Digits are not allowed!"
              value="City"
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Dropdown
              autofill={data?.province}
              id="province"
              value="Province"
              optionsList={Object.values(provinceCodetable).map(
                (province: any) => province.en
              )}
              disabled={disabled}
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              autofill={data?.postalCode}
              id="postalCode"
              value="Postal Code"
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              autofill={data?.homePhone}
              id="homePhone"
              validationPattern={`^[^a-zA-Z]*$`}
              validationTitle="Alphabets are not allowed!"
              value="Home Phone"
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              autofill={data?.cellPhone}
              id="cellPhone"
              validationPattern={`^[^a-zA-Z]*$`}
              validationTitle="Alphabets are not allowed!"
              value="Cell Phone"
              readOnly={disabled}
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              autofill={data?.workPhone}
              id="workPhone"
              validationPattern={`^[^a-zA-Z]*$`}
              validationTitle="Alphabets are not allowed!"
              value="Work Phone"
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Input
              autofill={data?.emailAddress}
              id="emailAddress"
              value="Email Address"
              type="email"
              readOnly={disabled}
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSSaveButton disabled={disabled} />
        </Box>
      </Box>
    </CYFMSLayout>
  );
};

export default ContactPage;
