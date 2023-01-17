import { Typography } from "@mui/material";
import { Box } from "@mui/system";
import React, { FormEvent, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";
import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import EditModeButton from "../../../components/fh/EditModeButton";
import FhLayout from "../../../components/fh/FhLayout";
import TextArea from "../../../components/TextArea";
import axiosInstance from "../../../library/axiosInstance";
import { saveReferral } from "../referral/ReferralService";
import { Data } from "./HistorYDatatype";
import { readHistory, saveHistory } from "./HistoryService";
const History = () => {
  const navigate = useNavigate();
  const { id } = useParams();

  // useEffect(() => {
  //   dispatch(doGet(state.data.fhFileDetailsId));
  // }, []);
  const [state, setState] = useState<Data>({
    fhFileDetailsId: Number(id) | 0,
    emotional: "",
    family: "",
    fhHistoryId: 0,
  });
  const [disabled, setDisabled] = useState(false);
  useEffect(() => {
    readHistory(Number(id), state).then(({ data }) => {
      setState(data);
      if (data.fhHistoryId !== 0) {
        setDisabled(true);
      }
    });
  }, []);

  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const form = e.currentTarget as HTMLFormElement;
    const formData: Data = {
      fhFileDetailsId: state.fhFileDetailsId,
      emotional: form.emotional.value,
      family: form.family.value,
      fhHistoryId: state.fhHistoryId,
    };
    console.log("shubh", formData);
    saveHistory(formData).then((res) => {
      navigate(`../approval/${res.data.fhFileDetailsId}`);
    });
  };

  return (
    <>
      <FhLayout>
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
          // onKeyDown={onKeyDown}
        >
          <div>
            <>
              <EditModeButton
                id={state.fhHistoryId}
                disabled={disabled}
                setDisabled={setDisabled}
                fileDetailsPage={true}
                path={`/fh/referral/${id}`}
                moduleName={"History"}
              />
            </>
          </div>
          <Typography
            sx={{ marginBottom: "10px", marginLeft: "35px" }}
            variant="body1"
          >
            Please describe any observations regarding individual behavior and
            emotional history
          </Typography>

          <TextArea
            formLabelFlex="0 1 0"
            outlinedInputFlex="5.3 1 0"
            id="emotional"
            autofill={state.emotional}
            readOnly={disabled}
          />
          <Box className="mb-4"></Box>
          <Typography
            sx={{ marginBottom: "10px", marginLeft: "35px" }}
            variant="body1"
          >
            Please describe any information regarding family history
          </Typography>

          <TextArea
            formLabelFlex="0 1 0"
            outlinedInputFlex="5.3 1 0"
            id="family"
            autofill={state.family}
            readOnly={disabled}
          />
          <Box
            sx={{ display: "flex", justifyContent: "right", marginTop: "20px" }}
          >
            <CYFSWMSNextButton disabled={disabled} />
          </Box>
        </Box>
      </FhLayout>
    </>
  );
};

export default History;
