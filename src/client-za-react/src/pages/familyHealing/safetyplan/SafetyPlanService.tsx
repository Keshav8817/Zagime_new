import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./SafetyPlanDataType";

export async function saveSafetyPlan(data: Data) {
  const res = await axiosInstance.put(
    `/familyHealing/service/saveSafetyPlan`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function readAllSafetyPlan(id: number) {
  const res = await axiosInstance.get(
    `/familyHealing/service/readAllSafetyPlan/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function doRemove(id: number) {
  const res = await axiosInstance.delete(
    `/familyHealing/service/removeSafetyPlan/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function readSafetyPlan(id: number) {
  const res = await axiosInstance.get(
    `familyHealing/service/readSafetyPlan/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );

  return res;
}
