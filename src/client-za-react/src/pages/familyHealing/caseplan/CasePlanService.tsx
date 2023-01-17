import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./CasePlanDataType";

export async function saveCasePlan(data: Data) {
  const res = await axiosInstance.put(
    `/familyHealing/service/saveCasePlan`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function readAllCasePlan(id: number) {
  const res = await axiosInstance.get(
    `/familyHealing/service/readAllCasePlan/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function doRemove(id: number) {
  const res = await axiosInstance.delete(
    `/familyHealing/service/removeCasePlan/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function readCasePlan(id: number) {
  const res = await axiosInstance.get(
    `familyHealing/service/readCasePlan/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );

  return res;
}
