import axiosInstance from "../../../library/axiosInstance";
import {
  doGetCPACulturalStatusAPI,
  getCpaTypesCodetable,
} from "../../../services/codetableService";
import type { Dispatch, SetStateAction } from "react";
import { Search } from "./searchDatatypes";

/**
 * Callback of useEffect hook.
 */
export const handleEffect = (
  setCulturalStatus: Dispatch<SetStateAction<any>>,
  setCpaTypesCodetable: Dispatch<SetStateAction<any>>
) => {
  doGetCPACulturalStatusAPI().then((response) => {
    setCulturalStatus(response.data.valuesMap);
  });
  getCpaTypesCodetable().then((response) => {
    setCpaTypesCodetable(response.data.valuesMap);
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
    culturalProgramId: 0,
    referenceId: event.currentTarget.referenceId.value || null,
    name: event.currentTarget.name.value || null,
    type: event.currentTarget.type.value || null,
    caseworker: event.currentTarget.caseworker.value || null,
    startDate: event.currentTarget.startDate.value || null,
    status: event.currentTarget.status.value || null,
  };
  axiosInstance
    .get(
      `culturalprogandactservice/culturalProgAndActSearch/${formData.referenceId}/${formData.name}/${formData.type}/${formData.caseworker}/${formData.startDate}/${formData.status}`,
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
