import { CYFSWMSSaveButton } from "../../../components/CYFSWMSButtons";
import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";
import HrLayout from "../../../components/hr/HrLayout";
import { onKeyDown } from "../../../library/app";
import { Data } from "./jobAndBankingDatatypes";
import { saveJobAndBanking, readJobAndBanking } from "./jobAndBankingServices";
import { Box, Typography } from "@mui/material";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { FC, FormEvent } from "react";
import EditModeButton from "../../../components/hr/Button/EditModeButton";
import ICInput from "../../../components/initialContact/Input";
import DateInput from "../../../components/initialContact/DateInput";
import CustomCheckbox from "../../../components/initialContact/CheckBox";

/**
 * `HR` aka `Human Resources` module.
 * Sub page: `JobAndBankingPage`.
 */
const JobAndBankingPage: FC = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [disabled, setDisabled] = useState(false);
  const [state, setState] = useState<Data>({
    staffJobAndBankingId: 0,
    staffId: Number(id),
    active: false,
    startDate: "",
    endDate: "",
    workPhone: "",
    workEmail: "",
    jobTitle: "",
    salary: 0,
    bankAccount: "",
    transit: "",
    bank: "",
    bankName: "",
    bankAddress: "",
  });

  useEffect(() => {
    readJobAndBanking(Number(id)).then(({ data }) => {
      setState(data);
      if (data.staffJobAndBankingId !== 0) {
        setDisabled(true);
      }
    });
  }, []);

  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const form: any = e.currentTarget;
    const formData: Data = {
      staffId: Number(id),
      staffJobAndBankingId: state.staffJobAndBankingId,
      active: state.active,
      startDate: form.startDate.value,
      endDate: form.endDate.value,
      workPhone: form.workPhone.value,
      workEmail: form.workEmail.value,
      jobTitle: form.jobTitle.value,
      salary: form.salary.value,
      bankAccount: form.bankAccount.value,
      transit: form.transit.value,
      bank: form.bank.value,
      bankName: form.bankName.value,
      bankAddress: form.bankAddress.value,
    };
    console.log(formData);
    saveJobAndBanking(formData).then(() => {
      navigate(`/hr/medical&emergency/${Number(id)}`);
    });
  };

  const handleCheckBoxOne = (event: any) => {
    setState({ ...state, active: event.target.checked });
  };
  return (
    <HrLayout>
      <Box
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
          "> div": { display: "flex", gap: "0 1rem" },
          "> div > div": { flex: "1 1 0" },
        }}
        onSubmit={submitHandler}
        onKeyDown={onKeyDown}
      >
        <>
          <EditModeButton
            id={state.staffJobAndBankingId}
            disabled={disabled}
            setDisabled={setDisabled}
            path={`../Staff/${id}`}
            fileDetailsPage={true}
            module="jobBanking"
          />
        </>
        <Typography variant="body1" color="primary">
          Job Information
        </Typography>
        <div>
          <div>
            <CustomCheckbox
              label="Active?"
              onChange={handleCheckBoxOne}
              checked={state.active}
              labelPlacement={"start"}
            />
          </div>
          <div></div>
        </div>
        <div>
          <div>
            <DateInput
              autofill={state.startDate}
              id="startDate"
              type="date"
              validationPattern={`^[a-zA-Z ]*$`}
              validationTitle="Digits are not allowed!"
              value="Start Date"
              readOnly={disabled}
            />
          </div>
          <div>
            <DateInput
              autofill={state.endDate}
              id="endDate"
              type="date"
              validationPattern={`^[a-zA-Z ]*$`}
              validationTitle="Digits are not allowed!"
              value="End Date"
              readOnly={disabled}
            />
          </div>
        </div>
        <div>
          <div>
            <ICInput
              autofill={state.workPhone}
              id="workPhone"
              validationPattern={`^[^a-zA-Z]*$`}
              validationTitle="Alphabets are not allowed!"
              value="Work Phone "
              readOnly={disabled}
            />
          </div>
          <div>
            <ICInput
              autofill={state.workEmail}
              id="workEmail"
              type="email"
              value="Work Email"
              readOnly={disabled}
            />
          </div>
        </div>
        <div>
          <div>
            <ICInput
              autofill={state.jobTitle}
              id="jobTitle"
              value="Job Title"
              readOnly={disabled}
            />
          </div>
          <div>
            <ICInput
              autofill={state.salary}
              id="salary"
              value="Salary"
              readOnly={disabled}
            />
          </div>
        </div>
        <Typography variant="body1" color="primary">
          Banking information
        </Typography>
        <div>
          <div>
            <ICInput
              autofill={state.bankAccount}
              id="bankAccount"
              value="Bank account"
              readOnly={disabled}
            />
          </div>
          <div>
            <ICInput
              autofill={state.transit}
              id="transit"
              value="Transit"
              readOnly={disabled}
            />
          </div>
        </div>
        <div>
          <div>
            <ICInput
              autofill={state.bank}
              id="bank"
              value="Bank"
              readOnly={disabled}
            />
          </div>
          <div>
            <ICInput
              autofill={state.bankName}
              id="bankName"
              value="Bank Name"
              readOnly={disabled}
            />
          </div>
        </div>
        <div>
          <TextArea
            formLabelFlex="1 1 0"
            outlinedInputFlex="5.3 1 0"
            autofill={state.bankAddress}
            id="bankAddress"
            value="Bank Address"
            readOnly={disabled}
          />
        </div>
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSSaveButton disabled={disabled} />
        </Box>
      </Box>
    </HrLayout>
  );
};

export default JobAndBankingPage;
