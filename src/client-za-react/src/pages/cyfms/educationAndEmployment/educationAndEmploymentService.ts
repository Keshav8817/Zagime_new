import axiosInstance from "../../../library/axiosInstance";
import {
  getEducationAndEmploymentCodetable,
  getTypesOfEmployeeCodetable,
} from "../../../services/codetableService";
import type {
  ModuleContextState,
  ModuleDispatchContextState,
} from "../../../contexts/ModuleContext";
import type { EducationAndEmployment } from "./educationAndEmploymentDatatypes";
import type { Dispatch, SetStateAction } from "react";
import { NavigateFunction, useParams } from "react-router-dom";

/**
 * Callback of useEffect hook.
 * @param moduleContext - Module context
 * @param moduleDispatchContext - Module actions dispatcher context
 * @param setEducationAndEmploymentCodetable - Set genders codetable
 * @param setTypesOfEmployeeCodetable - Set marital status codetable
 * @param setData -
 */
export const handleEffect = (
  data: EducationAndEmployment,
  setData: any,
  id: number,
  moduleContext: ModuleContextState<EducationAndEmployment>,
  moduleDispatchContext: ModuleDispatchContextState,
  setEducationAndEmploymentCodetable: Dispatch<SetStateAction<any>>,
  setTypesOfEmployeeCodetable: Dispatch<SetStateAction<any>>,
  setDisabledSchoolFields: Dispatch<SetStateAction<boolean>>,
  setDisabledDesiredProfession: Dispatch<SetStateAction<boolean>>,
  setDisabled: Dispatch<SetStateAction<boolean>>
) => {
  moduleDispatchContext({
    type: "set_data",
    data: {
      participantId: id,
      educationId: 0,
      employmentId: 0,
      attendingSchool: "",
      school: "",
      grade: "",
      employed: "",
      typeOfEmployment: "",
      desiredProfession: "",
    } as EducationAndEmployment,
  });
  getEducationAndEmploymentCodetable().then((response) => {
    setEducationAndEmploymentCodetable(response.data.valuesMap);
  });
  getTypesOfEmployeeCodetable().then((response) => {
    setTypesOfEmployeeCodetable(response.data.valuesMap);
  });
  axiosInstance
    .get(`participantservice/readEmploymentAndEducation/${id}`, {
      headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
    })
    .then((response) => {
      if (response.data.attendingSchool === "Yes") {
        setDisabledSchoolFields(false);
      }
      if (response.data.typeOfEmployment === "Job Search") {
        setDisabledDesiredProfession(false);
      }
      setData(response.data);
      moduleDispatchContext({ type: "set_data", data: response.data });
      if (data.participantId !== 0) {
        setDisabled(true);
      }
    });
};

/**
 * Callback of onChange event.
 * @param event - Form event object
 */
export const handleChange: AppFormEventHandler<HTMLFormElement> = (
  event,
  setDisabledSchoolFields: Dispatch<SetStateAction<boolean>>,
  setDisabledDesiredProfession: Dispatch<SetStateAction<boolean>>
) => {
  if (event.currentTarget.attendingSchool.value === "Yes") {
    setDisabledSchoolFields(false);
  } else {
    event.currentTarget.schoolName.value = "";
    event.currentTarget.schoolGrade.value = "";
    setDisabledSchoolFields(true);
  }
  if (event.currentTarget.typeOfEmployment.value === "Job Search") {
    setDisabledDesiredProfession(false);
  } else {
    event.currentTarget.desiredProfession.value = "";
    setDisabledDesiredProfession(true);
  }
};

/**
 * Callback of onSubmit event.
 * @param event - Form event object
 * @param navigate - React Router navigator
 * @param moduleContext - Module context
 * @param moduleDispatchContext - Module actions dispatcher context
 */
export const handleSubmit: AppFormEventHandler<HTMLFormElement> = (
  data,
  id,
  event,
  navigate: NavigateFunction,
  moduleContext: ModuleContextState<EducationAndEmployment>,
  moduleDispatchContext: ModuleDispatchContextState
) => {
  event.preventDefault();
  const form: any = event.currentTarget;
  const formData = {
    participantId: id,
    educationId: data.educationId,
    employmentId: data.employmentId,
    attendingSchool: form.attendingSchool.value,
    school: form.schoolName.value,
    grade: form.schoolGrade.value,
    employed: form.employed.value,
    typeOfEmployment: form.typeOfEmployment.value,
    desiredProfession: form.desiredProfession.value,
  };
  axiosInstance
    .put("participantservice/saveEmploymentAndEducation", formData, {
      headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
    })
    .then((response) => {
      moduleDispatchContext({ type: "set_data", data: response.data });
      navigate(`/cyfms/criminal_history/${id}`);
    });
};
export async function removeEducationAndEmployment(id: number) {
  const res = await axiosInstance.delete(
    `/participantservice/removeEducationAndEmployment/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
