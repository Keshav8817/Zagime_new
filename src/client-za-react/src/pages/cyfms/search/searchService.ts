import axiosInstance from "../../../library/axiosInstance";
import { getMaritalStatusCodetable } from "../../../services/codetableService";
import type { Search } from "./searchDatatypes";
import type { Dispatch, SetStateAction } from "react";

/**
 * Callback of useEffect hook.
 * @param setMaritalStatusCodetable - Set marital status codetable
 */
export const handleEffect = (
  setMaritalStatusCodetable: Dispatch<SetStateAction<any>>
) => {
  getMaritalStatusCodetable().then((response) => {
    setMaritalStatusCodetable(response.data.valuesMap);
  });
};

/**
 * Callback of onSubmit event.
 * @param event - form event object
 * @param setIsShown - TODO: ADD DESCRIPTION HERE
 */
export const handleSubmit: AppFormEventHandler<HTMLFormElement> = (
  event,
  setData: Dispatch<SetStateAction<Search[]>>,
  setIsShown: Dispatch<SetStateAction<boolean>>
) => {
  event.preventDefault();
  const formData: Search = {
    participantId: null,
    referenceId: event.currentTarget.referenceId.value || null,
    firstname: event.currentTarget.firstName.value || null,
    surname: event.currentTarget.lastName.value || null,
    middleName: event.currentTarget.middleName.value || null,
    dateOfBirth: event.currentTarget.dateOfBirth.value || null,
    maritalStatus: event.currentTarget.maritalStatus.value || null,
    city: event.currentTarget.city.value || null,
    workPhone: event.currentTarget.phoneNo.value || null,
  };
  axiosInstance
    .get(
      `participantservice/searchParticipants/${formData.referenceId}/${formData.firstname}/${formData.middleName}/${formData.surname}/${formData.dateOfBirth}/${formData.maritalStatus}/${formData.city}/${formData.workPhone}`,
      {
        headers: {
          authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
        },
      }
    )
    .then((response) => {
      setData(response.data);
      setIsShown(true);
    });
};
