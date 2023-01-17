import axiosInstance from "../../../library/axiosInstance";
import {
  getGendersCodetable,
  getMaritalStatusCodetable,
} from "../../../services/codetableService";
import type {
  ModuleContextState,
  ModuleDispatchContextState,
} from "../../../contexts/ModuleContext";
import type { Register } from "./registerDatatypes";
import { Dispatch, SetStateAction, useState } from "react";
import type { NavigateFunction } from "react-router-dom";

/**
 * Callback of useEffect hook.
 * @param moduleContext - Module context
 * @param moduleDispatchContext - Module actions dispatcher context
 * @param setGendersCodetable - Set genders codetable
 * @param setMaritalStatusCodetable - Set marital status codetable
 * @param data - Register
 * @param setData - Set the Register
 */
export const handleEffect = (
  moduleContext: ModuleContextState<Register>,
  moduleDispatchContext: ModuleDispatchContextState,
  setGendersCodetable: Dispatch<SetStateAction<any>>,
  setMaritalStatusCodetable: Dispatch<SetStateAction<any>>,
  data: Register,
  setData: Dispatch<SetStateAction<Register>>,
  setDisabled: Dispatch<SetStateAction<boolean>>
) => {
  getGendersCodetable().then((response) => {
    setGendersCodetable(response.data.valuesMap);
  });
  getMaritalStatusCodetable().then((response) => {
    setMaritalStatusCodetable(response.data.valuesMap);
  });
  axiosInstance
    .get(`participantservice/readParticipantIdentity/${data.participantId}`, {
      headers: {
        authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
      },
    })
    .then((response) => {
      setData(response.data);
      if (data.participantId !== 0) {
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
  moduleContext: ModuleContextState<Register>,
  moduleDispatchContext: ModuleDispatchContextState,
  data: Register
) => {
  event.preventDefault();
  const formData = new FormData();
  formData.append("participantId", String(data.participantId));
  formData.append("referenceId", String(data.referenceId));
  formData.append("firstName", event.currentTarget.firstName.value);
  formData.append("middleName", event.currentTarget.middleName.value);
  formData.append("lastName", event.currentTarget.lastName.value);
  formData.append("dateOfBirth", event.currentTarget.dateOfBirth.value);
  formData.append("gender", event.currentTarget.gender.value);
  formData.append("maritalStatus", event.currentTarget.maritalStatus.value);
  formData.append(
    "removeProfilePicture",
    String(event.currentTarget.removeProfilePicture.checked)
  );
  formData.append(
    "participantImageId",
    `${data.participantImageId ? data.participantImageId : 0}`
  );
  formData.append("image", event.currentTarget.imageFile.files[0]);

  axiosInstance
    .put<Register>("participantservice/saveParticipantIdentity", formData, {
      headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
    })
    .then((response) => {
      moduleDispatchContext({
        type: "change_id",
        id: response.data.participantId,
      });
      moduleDispatchContext({
        type: "toggle_tabsDisabled",
        tabsDisabled: false,
      });
      moduleDispatchContext({
        type: "set_data",
        data: response.data,
      });
      navigate(`/cyfms/contact/${response.data.participantId}`);
    });
};
export async function removeParticipant(id: number) {
  const res = await axiosInstance.delete(
    `/participantservice/removeParticipant/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function getIdentity(id: number) {
  const res = await axiosInstance.get(
    `participantservice/readParticipantIdentity/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
