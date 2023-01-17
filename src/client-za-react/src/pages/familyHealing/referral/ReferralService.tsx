import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./ReferralDataType";

export async function saveReferral(data: Data) {
  const res = await axiosInstance.put(
    `/familyHealing/service/saveReferral`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function readAllReferral(id: number, data: Data) {
  const res = await axiosInstance.get(
    `/familyHealing/service/readReferral/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function doRemove(id: number) {
  const res = await axiosInstance.delete(
    `/familyHealingService/removeReferral/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
