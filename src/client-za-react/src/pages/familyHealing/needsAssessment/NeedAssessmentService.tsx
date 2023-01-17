import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./NeedAssessmentDataType";

export async function saveNeedAssessment(data: Data) {
  const res = await axiosInstance.put(
    `/familyHealing/service/saveNeedAssessment`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function readAllNeedAssessment(id: number) {
  const res = await axiosInstance.get(
    `/familyHealing/service/readAllNeedAssessment/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function doRemove(id: number) {
  const res = await axiosInstance.delete(
    `/familyHealing/service/removeNeedAssessment/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function readNeedAssessment(id: number) {
  const res = await axiosInstance.get(
    `familyHealing/service/readNeedAssessment/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );

  return res;
}
