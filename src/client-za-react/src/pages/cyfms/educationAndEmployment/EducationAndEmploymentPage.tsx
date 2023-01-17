import { CYFSWMSSaveButton } from "../../../components/CYFSWMSButtons";
import Input from "../../../components/Input";
import CYFMSDropdown from "../../../components/Dropdown";
import CYFMSLayout from "../../../components/cyfms/CYFMSLayout";
import {
  ModuleContext,
  ModuleDispatchContext,
} from "../../../contexts/ModuleContext";
import { onKeyDown } from "../../../library/app";
import {
  handleChange,
  handleEffect,
  handleSubmit,
} from "./educationAndEmploymentService";
import { Box, Typography } from "@mui/material";
import React, { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import type { EducationAndEmployment } from "./educationAndEmploymentDatatypes";
import type { FC } from "react";
import EditModeButton from "../../../components/cyfms/EditModeButton";
import ICInput from "../../../components/initialContact/Input";

/**
 * The EducationAndEmployment functional component.
 */
const EducationAndEmploymentPage: FC = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const moduleContext = useContext(ModuleContext);
  const moduleDispatchContext = useContext(ModuleDispatchContext);
  const [disabled, setDisabled] = useState(false);
  const [educationAndEmploymentCodetable, setEducationAndEmploymentCodetable] =
    useState<any>([]);
  const [typesOfEmployeeCodetable, setTypesOfEmployeeCodetable] = useState<any>(
    []
  );
  const [data, setData] = useState<EducationAndEmployment>({
    participantId: Number(id),
    educationId: 0,
    employmentId: 0,
    attendingSchool: "",
    school: "",
    grade: "",
    employed: "",
    typeOfEmployment: "",
    desiredProfession: "",
  });
  const [disabledSchoolFields, setDisabledSchoolFields] =
    useState<boolean>(true);
  const [disabledDesiredProfession, setDisabledDesiredProfession] =
    useState<boolean>(true);

  useEffect(
    () =>
      handleEffect(
        data,
        setData,
        Number(id),
        moduleContext,
        moduleDispatchContext,
        setEducationAndEmploymentCodetable,
        setTypesOfEmployeeCodetable,
        setDisabledSchoolFields,
        setDisabledDesiredProfession,
        setDisabled
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
            data,
            Number(id),
            event,
            navigate,
            moduleContext,
            moduleDispatchContext
          )
        }
        onChange={(event) =>
          handleChange(
            event,
            setDisabledSchoolFields,
            setDisabledDesiredProfession
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
              path={`/cyfms/register/${id}`}
              fileDetailsPage={true}
              module="education_and_employment"
            />
          </>
        </div>
        <Box sx={{ display: "flex", flexDirection: "column", gap: "1rem 0" }}>
          <Box>
            <Typography variant="body1" color="primary">
              Education
            </Typography>
            <Box
              sx={{ display: "flex", flexDirection: "column", gap: "1rem 0" }}
            >
              <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                  <CYFMSDropdown
                    autofill={data?.attendingSchool}
                    id="attendingSchool"
                    value="Attending School?"
                    optionsList={Object.values(
                      educationAndEmploymentCodetable
                    ).map((education: any) => education.en)}
                    // disabled={/*editMode*/ false}
                    disabled={disabled}
                  />
                </Box>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
              </Box>
              <Box
                sx={{
                  display: "flex",
                  flexWrap: "wrap",
                  gap: "1rem",
                  flexGrow: 5,
                }}
              >
                <Box sx={{ flexBasis: 0, flexGrow: 0.5 }}>
                  <ICInput
                    autofill={data?.school}
                    disabled={disabledSchoolFields}
                    id="schoolName"
                    value="School"
                    validationPattern={`^[a-zA-Z0-9 ]*$`}
                    validationTitle="Digits are not allowed!"
                    readOnly={disabled}
                  />
                </Box>
                <Box sx={{ flexBasis: 0, flexGrow: 0.5 }}>
                  <ICInput
                    autofill={data?.grade}
                    disabled={disabledSchoolFields}
                    id="schoolGrade"
                    value="Grade"
                    readOnly={disabled}
                  />
                </Box>
              </Box>
            </Box>
          </Box>
          <Box>
            <Typography variant="body1" color="primary">
              Employment
            </Typography>
            <Box
              sx={{ display: "flex", flexDirection: "column", gap: "1rem 0" }}
            >
              <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                  <CYFMSDropdown
                    autofill={data?.employed}
                    id="employed"
                    value="Employed?"
                    optionsList={Object.values(
                      educationAndEmploymentCodetable
                    ).map((education: any) => education.en)}
                    // disabled={/*editMode*/ false}
                    disabled={disabled}
                  />
                </Box>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                  <CYFMSDropdown
                    autofill={data?.typeOfEmployment}
                    id="typeOfEmployment"
                    value="Type of Employment"
                    optionsList={Object.values(typesOfEmployeeCodetable).map(
                      (employee: any) => employee.en
                    )}
                    // disabled={/*editMode*/ false}
                    disabled={disabled}
                  />
                </Box>
              </Box>
              <Box
                sx={{
                  display: "flex",
                  flexWrap: "wrap",
                  gap: "0 1rem",
                }}
              >
                <Box sx={{ flexBasis: 0, flexGrow: 0.999999 }}>
                  <ICInput
                    autofill={moduleContext.data?.desiredProfession}
                    disabled={disabledDesiredProfession}
                    id="desiredProfession"
                    validationPattern={`^[a-zA-Z ]*$`}
                    validationTitle="Digits are not allowed!"
                    value="Desired Profession"
                    readOnly={disabled}
                  />
                </Box>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}></Box>
              </Box>
            </Box>
          </Box>
        </Box>
        <Box sx={{ display: "flex", justifyContent: "right" }}>
          <CYFSWMSSaveButton disabled={disabled} />
        </Box>
      </Box>
    </CYFMSLayout>
  );
};

export default EducationAndEmploymentPage;
