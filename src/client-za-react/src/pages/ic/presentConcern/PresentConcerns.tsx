import { CYFSWMSNextButton } from "../../../components/CYFSWMSButtons";
import ICLayout from "../../../components/initialContact/ICLayout";
import TextArea from "../../../components/TextArea";
import { onKeyDown } from "../../../library/app";
import { Box, SelectChangeEvent } from "@mui/material";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { FormEvent, ReactElement } from "react";
import { getAllPresentConcerns, postPresentConcerns } from "./service";
import ICMultiSelectDropdown from "../../../components/initialContact/ICMultiSelectDropdown";
import { Data } from "./presentConcernDataType";
import {
  getIcrMentalHealthOrSubstanceAbuseCodetable,
  getIcrPresentConcernsCodetable,
} from "../../../services/codetableService";
import EditModeButton from "../../../components/initialContact/Button";

const PresentConcerns = (): ReactElement => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [state, setState] = useState({
    fileDetailsId: Number(id),
    presentConcernsId: 0,
    selectPresentConcerns: "",
    situation: "",
    substanceAbuse: "",
    explainMentalHealth: "",
  });
  const [presentConcern, setPresentConcerns] = useState({});
  const [disabled, setDisabled] = useState(false);
  const [mentalHealthOrSubstanceAbuse, setmentalHealthOrSubstanceAbuse] =
    useState({});
  const [substanceAbuse, setSubstanceAbuse] = React.useState<string[]>([]);
  const [selectPresentConcerns, setSelectPresentConcerns] = React.useState<
    string[]
  >([]);

  useEffect(() => {
    getIcrPresentConcernsCodetable().then((data) => {
      setPresentConcerns(data.data.valuesMap);
    });
    getIcrMentalHealthOrSubstanceAbuseCodetable().then((data) => {
      setmentalHealthOrSubstanceAbuse(data.data.valuesMap);
    });
    getAllPresentConcerns(state.fileDetailsId).then(({ data }) => {
      setState(data);
      setSelectPresentConcerns(
        JSON.parse(data.selectPresentConcerns.replace(/\n/, ""))
      );
      setSubstanceAbuse(JSON.parse(data.substanceAbuse.replace(/\n/, "")));
      if (data.presentConcernsId !== 0) {
        setDisabled(true);
      }
    });
  }, []);

  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const form: any = e.currentTarget;
    const formData: Data = {
      fileDetailsId: Number(id),
      presentConcernsId: state.presentConcernsId,
      selectPresentConcerns: JSON.stringify(selectPresentConcerns),
      situation: form.situation.value,
      substanceAbuse: JSON.stringify(substanceAbuse),
      explainMentalHealth: form.explainMentalHealth.value,
    };
    postPresentConcerns(formData).then(() => {
      navigate(`../patient_care_information/${Number(id)}`);
    });
  };

  const handleChange = (
    event: SelectChangeEvent<typeof selectPresentConcerns>
  ) => {
    const {
      target: { value },
    } = event;
    setSelectPresentConcerns(
      typeof value === "string" ? value.split(",") : value
    );
  };

  const handleChange1 = (event: SelectChangeEvent<typeof substanceAbuse>) => {
    const {
      target: { value },
    } = event;
    setSubstanceAbuse(typeof value === "string" ? value.split(",") : value);
  };

  return (
    <ICLayout>
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
              id={state.presentConcernsId}
              setDisabled={setDisabled}
              disabled={disabled}
              module="present_concerns"
              path={`/initial_contact/file_details/${id}`}
            />
          </>
        </div>
        <Box sx={{ display: "flex", flexDirection: "column", gap: "1rem 0" }}>
          <Box>
            <Box
              sx={{ display: "flex", flexDirection: "column", gap: "1rem 0" }}
            >
              <Box
                sx={{
                  display: "flex",
                  flexWrap: "wrap",
                  gap: "1 1rem",
                }}
              >
                <Box sx={{ flexBasis: 0, flexGrow: 2, marginRight: "-240px" }}>
                  <ICMultiSelectDropdown
                    value={selectPresentConcerns}
                    onChange={handleChange}
                    readOnly={disabled}
                    list={setSelectPresentConcerns}
                    id="selectPresentConcerns"
                    label="Please Select Present Concerns"
                    optionsList={Object.values(presentConcern).map(
                      (substance: any) => substance.en
                    )}
                  />
                </Box>
                <Box sx={{ flexBasis: 0, flexGrow: 1.15 }}></Box>
              </Box>
              <TextArea
                readOnly={disabled}
                formLabelFlex="1 1 0"
                outlinedInputFlex="5.3 1 0"
                autofill={state.situation}
                id="situation"
                value="Briefly Explain Situation"
              />
              <Box
                sx={{
                  display: "flex",
                  flexWrap: "wrap",
                  gap: "0 1rem",
                }}
              >
                <Box sx={{ flexBasis: 0, flexGrow: 2, marginRight: "-240px" }}>
                  <ICMultiSelectDropdown
                    id="substanceAbuse"
                    value={substanceAbuse}
                    onChange={handleChange1}
                    list={setSubstanceAbuse}
                    readOnly={disabled}
                    label="Mental Health or Alcohol / Substance Abuse"
                    optionsList={Object.values(
                      mentalHealthOrSubstanceAbuse
                    ).map((substance: any) => substance.en)}
                  />
                </Box>
                <Box sx={{ flexBasis: 0, flexGrow: 1.12 }}></Box>
              </Box>
              <TextArea
                readOnly={disabled}
                formLabelFlex="1 1 0"
                outlinedInputFlex="5.3 1 0"
                autofill={state.explainMentalHealth}
                id="explainMentalHealth"
                value="Briefly Explain"
              />
            </Box>
          </Box>
          <Box>
            <Box
              sx={{ display: "flex", flexDirection: "column", gap: "1rem 0" }}
            ></Box>
          </Box>
        </Box>
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSNextButton disabled={disabled} />
        </Box>
      </Box>
    </ICLayout>
  );
};

export default PresentConcerns;
