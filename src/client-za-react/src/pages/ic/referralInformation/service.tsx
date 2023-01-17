import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./ReferralInfoDataType";

export async function getAllReferralInfo(id: number) {
  const res = await axiosInstance.get(
    `initialcontactservice/readAllReferralInfo/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function postAllReferralInfo(data: Data) {
  const res = await axiosInstance.put(
    `initialcontactservice/saveAllReferralInfo`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );

  return res;
}
export async function removeReferralInfo(id: number) {
  const res = await axiosInstance.delete(
    `/initialContactService/removeReferralInfo/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
