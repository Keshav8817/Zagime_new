import { CYFSWMSSaveButton } from "../../../components/CYFSWMSButtons";
import TextArea from "../../../components/TextArea";

import { onKeyDown } from "../../../library/app";
import { Box, SelectChangeEvent } from "@mui/material";
import React, { useEffect, useState } from "react";
import type { FC, FormEvent } from "react";
import { Data } from "./ReferralDataType";
import FhLayout from "../../../components/fh/FhLayout";

import { useNavigate, useParams } from "react-router";

import { readAllReferral, saveReferral } from "./ReferralService";
import { getFhReferralReasonsCodetable } from "../../../services/codetableService";
import EditModeButton from "../../../components/fh/EditModeButton";
import ICInput from "../../../components/initialContact/Input";
import DateInput from "../../../components/initialContact/DateInput";
import ICMultiSelectDropdown from "../../../components/initialContact/ICMultiSelectDropdown";

const Referral: FC = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [FhReferralReason, setFhReferralReason] = useState({});
  const [disabled, setDisabled] = useState(false);
  const [state, setState] = useState<Data>({
    fhReferralId: 0,
    fhFileDetailsId: Number(id) | 0,
    referralDate: "",
    referredBy: "",
    reason: "",
    pleaseDescribe: "",
    notes: "",
  });
  const [reasons, setReasons] = React.useState<string[]>([]);

  useEffect(() => {
    readAllReferral(Number(id), state).then(({ data }) => {
      setState(data);
      setReasons(JSON.parse(data.reason.replace(/\n/, "")));
      if (data.fhReferralId !== 0) {
        setDisabled(true);
      }
    });
  }, []);
  useEffect(() => {
    getFhReferralReasonsCodetable().then((response) => {
      setFhReferralReason(response.data.valuesMap);
    });
  }, []);
  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const form = e.currentTarget as HTMLFormElement;
    const formData: Data = {
      fhFileDetailsId: state.fhFileDetailsId,
      fhReferralId: state.fhReferralId,
      referralDate: form.referralDate.value,
      referredBy: form.referredBy.value,
      reason: JSON.stringify(reasons),
      pleaseDescribe: form.describe.value,
      notes: form.notes.value,
    };
    saveReferral(formData).then((res) => {
      navigate(`../history/${res.data.fhFileDetailsId}`);
    });
  };

  const handleChange = (event: SelectChangeEvent<typeof reasons>) => {
    const {
      target: { value },
    } = event;
    setReasons(typeof value === "string" ? value.split(",") : value);
  };

  return (
    <FhLayout>
      <Box
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
        }}
        onSubmit={submitHandler}
        onKeyDown={onKeyDown}
      >
        <div>
          <>
            <EditModeButton
              id={state.fhReferralId}
              disabled={disabled}
              setDisabled={setDisabled}
              fileDetailsPage={true}
              path={`/fh/fileDetails/${id}`}
              moduleName={"referral"}
            />
          </>
        </div>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <DateInput
              autofill={state.referralDate}
              id="referralDate"
              value="Referral Date"
              required
              type="date"
              readOnly={disabled}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <ICInput
              autofill={state.referredBy}
              id="referredBy"
              value="Referred By"
              required
              readOnly={disabled}
            />
          </Box>
        </Box>

        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 4 }}>
            <ICMultiSelectDropdown
              value={reasons}
              width={342.67}
              list={setReasons}
              readOnly={disabled}
              onChange={handleChange}
              id="reasons"
              label="Reason(s)"
              optionsList={Object.values(FhReferralReason).map(
                (substance: any) => substance.en
              )}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 0.82 }}></Box>
        </Box>

        <TextArea
          autofill={state.pleaseDescribe}
          formLabelFlex="1 1 0"
          outlinedInputFlex="5.22 1 0"
          id="describe"
          value="Please Describe"
          readOnly={disabled}
        />

        <TextArea
          autofill={state.notes}
          formLabelFlex="1 1 0"
          outlinedInputFlex="5.22 1 0"
          id="notes"
          value="Notes"
          readOnly={disabled}
        />
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSSaveButton disabled={disabled} />
        </Box>
      </Box>
    </FhLayout>
  );
};

export default Referral;
