import axiosInstance from "../../../library/axiosInstance";
import type {
  ModuleContextState,
  ModuleDispatchContextState,
} from "../../../contexts/ModuleContext";
import type { OtherInformation } from "./otherInformationDatatypes";
import type { Dispatch, SetStateAction } from "react";
import type { NavigateFunction } from "react-router-dom";

/**
 * Callback of useEffect hook.
 * @param moduleContext - Module context
 * @param moduleDispatchContext - Module actions dispatcher context
 */
export const handleEffect = (
  moduleContext: ModuleContextState<OtherInformation>,
  moduleDispatchContext: ModuleDispatchContextState,
  setData: Dispatch<SetStateAction<OtherInformation>>,
  setDisabled: Dispatch<SetStateAction<boolean>>,
  id: any
) => {
  axiosInstance
    .get(`participantservice/readParticipantOtherInformation/${id}`, {
      headers: {
        authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
      },
    })
    .then((response) => {
      setData(response.data);
      if (response.data.participantOtherInfoId !== 0) {
        setDisabled(true);
      }
    });
};

/**
 * Callback of onSubmit event.
 * @param event - form event object
 * @param navigate - React Router navigator
 * @param moduleContext - Module context
 * @param moduleDispatchContext - Module actions dispatcher context
 * @param data
 */
export const handleSubmit: AppFormEventHandler<HTMLFormElement> = (
  event,
  navigate: NavigateFunction,
  moduleContext: ModuleContextState<OtherInformation>,
  moduleDispatchContext: ModuleDispatchContextState,
  data: OtherInformation,
  id: any
) => {
  event.preventDefault();
  const formData: OtherInformation = {
    participantId: Number(id),
    participantOtherInfoId: data.participantOtherInfoId,
    strength: event.currentTarget.strengths.value,
    weakness: event.currentTarget.weaknesses.value,
    skills: event.currentTarget.skills.value,
    experiences: event.currentTarget.experiences.value,
    effectiveCopingSkills: event.currentTarget.effectiveCopingSkills.value,
  };
  axiosInstance
    .put<OtherInformation>(
      "participantservice/saveParticipantOtherInformation",
      formData,
      {
        headers: {
          authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
        },
      }
    )
    .then((response) => {
      navigate(`/cyfms/attachments/${id}`);
    });
};
export async function removeParticipantOtherInformation(id: number) {
  const res = await axiosInstance.delete(
    `/participantservice/removeParticipantOtherInformation/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
