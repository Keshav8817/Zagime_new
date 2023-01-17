import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import Dropdown from "../../../components/DropdownTwo";
import HrLayout from "../../../components/hr/HrLayout";
import { onKeyDown } from "../../../library/app";
import { Data } from "./contactInformationDatatypes";
import Input from "../../../components/Input";
import {
  saveContactInformation,
  readContactInformation,
} from "./contactInformationServices";
import { Box } from "@mui/material";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { FC, FormEvent } from "react";
import { format } from "path/win32";
import { getProvinceCodetable } from "../../../services/codetableService";
import EditModeButton from "../../../components/hr/Button/EditModeButton";
import ICInput from "../../../components/initialContact/Input";

/**
 * `HR` aka `Human Resource` module.
 * Sub page: `ContactInformationPage`.
 */
const ContactInformation: FC = () => {
  const navigate = useNavigate();
  const [disabled, setDisabled] = useState(false);
  const [province, setProvince] = useState<any>([]);
  let { id } = useParams();
  const [state, setState] = useState<Data>({
    staffContactInformationId: 0,
    addressLine1: "",
    addressLine2: "",
    city: "",
    province: "",
    postalCode: "",
    emailAddress: "",
    cellPhone: "",
    homePhone: "",
    staffId: Number(localStorage.getItem("staffId")) | 0,
  });
  useEffect(() => {
    readContactInformation(Number(id)).then(({ data }) => {
      setState(data);
      if (data.staffContactInformationId !== 0) {
        setDisabled(true);
      }
    });
  }, []);

  useEffect(() => {
    getProvinceCodetable().then((response) => {
      setProvince(response.data.valuesMap);
    });
  }, []);

  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const form: any = e.currentTarget;
    const formData: Data = {
      staffId: Number(id),
      staffContactInformationId: state.staffContactInformationId,
      addressLine1: form.addressLine1.value,
      addressLine2: form.addressLine2.value,
      city: form.city.value,
      province: form.province.value,
      postalCode: form.postalCode.value,
      emailAddress: form.emailAddress.value,
      cellPhone: form.cellPhone.value,
      homePhone: form.homePhone.value,
    };
    console.log(formData);
    saveContactInformation(formData).then(() => {
      navigate(`../job&banking/${Number(id)}`);
    });
  };

  return (
    <HrLayout>
      <Box
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
        }}
        // onSubmit={(event: FormEvent<HTMLFormElement>) =>
        //   handleSubmit(event, navigate, dispatch, staffId, state.data)
        // }
        onKeyDown={onKeyDown}
        onSubmit={submitHandler}
      >
        <>
          <EditModeButton
            id={state.staffContactInformationId}
            disabled={disabled}
            setDisabled={setDisabled}
            path={`../Staff/${id}`}
            fileDetailsPage={true}
            module="contactInformation"
          />
        </>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              autofill={state.addressLine1}
              id="addressLine1"
              value="Address Line 1"
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              autofill={state.addressLine2}
              id="addressLine2"
              value="Address Line 2"
              readOnly={disabled}
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              autofill={state.city}
              readOnly={disabled}
              id="city"
              validationPattern={`^[a-zA-Z ]*$`}
              validationTitle="Digits are not allowed!"
              value="City"
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Dropdown
              id="province"
              value="Province"
              autofill={state.province}
              // optionsList={["list1", "list2", "list3"]}
              optionsList={Object.values(province).map((type: any) => type.en)}
              disabled={disabled}
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              autofill={state.postalCode}
              id="postalCode"
              value="Postal Code"
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              autofill={state.emailAddress}
              id="emailAddress"
              type="email"
              value="Email Address"
              readOnly={disabled}
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              autofill={state.homePhone}
              id="homePhone"
              validationPattern={`^[^a-zA-Z]*$`}
              validationTitle="Alphabets are not allowed!"
              value="Cell Phone"
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              autofill={state.cellPhone}
              id="cellPhone"
              validationPattern={`^[^a-zA-Z]*$`}
              validationTitle="Alphabets are not allowed!"
              value="Home Phone"
              readOnly={disabled}
            />
          </Box>
        </Box>
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSNextButton disabled={disabled} />
        </Box>
      </Box>
    </HrLayout>
  );
};

export default ContactInformation;
