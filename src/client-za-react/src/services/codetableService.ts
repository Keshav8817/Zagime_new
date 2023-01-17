import axiosInstance from "../library/axiosInstance";
import type { AxiosResponse } from "axios";

/**
 * Codetable data type
 */
export interface Codetable {
  /** Name of codetable */
  type: string;
  /** Codetable values */
  valuesMap: {
    [value: string]: {
      en: string;
      fr?: string;
    };
  };
}

// CYFMS

export const getGendersCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/gender", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getMaritalStatusCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/maritalstatus", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getProvinceCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/province", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getEducationAndEmploymentCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/education", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getTypesOfEmployeeCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/typeofemployee", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getRolesCodetable = async (): Promise<AxiosResponse<Codetable>> =>
  await axiosInstance.get("dataservice/role", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getAppointmentsStatusCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/appoinmentstatus", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getRemindersStatusCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/reminderstatus", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getFrequencyCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/frequency", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

// ICR

export const getIcrStatusCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/initialContactStatus", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getIcrReferralCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/initialContactReferral", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getIcrRisksCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/risk", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getIcrPresentConcernsCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/presentConcerns", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getIcrMentalHealthOrSubstanceAbuseCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/mentalHealthOrSubstanceAbuse", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getIcrTypesOfPatientsCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/typeOfPatient", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getIcrContactMethodsCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/contactMethod", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

// CPA

export const getCpaTypesCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/culturaltype", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const doGetCPACulturalStatusAPI = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/culturalstatus", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

// FH

export const getFhReferralStatusCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/referralstatus", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getFhReferralReasonsCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/referralreasons", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getFhConsenTypeAPI = async (): Promise<AxiosResponse<Codetable>> =>
  await axiosInstance.get("dataservice/consenttype", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

// HR

export const getStaffStatusCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/staffstatus", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getTrainingsStatusCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/trainingstatus", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });

export const getGoalsAndObjectivesCodetable = async (): Promise<
  AxiosResponse<Codetable>
> =>
  await axiosInstance.get("dataservice/goalsobjectivestatus", {
    headers: { authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
  });
