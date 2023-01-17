import { CYFSWMSSaveButton } from "../../../components/CYFSWMSButtons";
import CYFMSLayout from "../../../components/cyfms/CYFMSLayout";
import TextArea from "../../../components/TextArea";
import {
  ModuleContext,
  ModuleDispatchContext,
} from "../../../contexts/ModuleContext";
import { onKeyDown } from "../../../library/app";
import { handleEffect, handleSubmit } from "./otherInformationService";
import { Box, Typography } from "@mui/material";
import React, { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { OtherInformation } from "./otherInformationDatatypes";
import type { FC } from "react";
import EditModeButton from "../../../components/cyfms/EditModeButton";

/**
 * The OtherInformation functional component.
 */
const OtherInformationPage: FC = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const moduleContext = useContext(ModuleContext);
  const [disabled, setDisabled] = useState(false);
  const moduleDispatchContext = useContext(ModuleDispatchContext);

  const [participantId, setParticipantId] = useState<number>(0);
  const [data, setData] = useState<OtherInformation>({
    participantId: Number(id),
    participantOtherInfoId: 0,
    strength: "",
    weakness: "",
    skills: "",
    experiences: "",
    effectiveCopingSkills: "",
  });

  useEffect(
    () =>
      handleEffect(
        moduleContext,
        moduleDispatchContext,
        setData,
        setDisabled,
        id
      ),
    []
  );
  return (
    <CYFMSLayout>
      <Box
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
        }}
        onSubmit={(event) =>
          handleSubmit(
            event,
            navigate,
            moduleContext,
            moduleDispatchContext,
            data,
            id
          )
        }
        onKeyDown={onKeyDown}
      >
        <div>
          <>
            <EditModeButton
              id={data.participantOtherInfoId}
              disabled={disabled}
              setDisabled={setDisabled}
              path={`/cyfms/register/${id}`}
              module="other_information"
            />
          </>
        </div>

        <Typography variant="body1" color="primary">
          Other Information
        </Typography>
        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5 1 0"
          autofill={data.strength}
          id="strengths"
          value="Strengths"
          readOnly={disabled}
        />
        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5 1 0"
          autofill={data.weakness}
          id="weaknesses"
          value="Weaknesses"
          readOnly={disabled}
        />
        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5 1 0"
          autofill={data.skills}
          id="skills"
          value="Skills"
          readOnly={disabled}
        />
        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5 1 0"
          autofill={data.experiences}
          id="experiences"
          value="Experiences"
          readOnly={disabled}
        />
        <TextArea
          formLabelFlex="1 1 0"
          outlinedInputFlex="5 1 0"
          autofill={data.effectiveCopingSkills}
          id="effectiveCopingSkills"
          value="Effective Coping Skills"
          readOnly={disabled}
        />
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSSaveButton disabled={disabled} />
        </Box>
      </Box>
    </CYFMSLayout>
  );
};

export default OtherInformationPage;
