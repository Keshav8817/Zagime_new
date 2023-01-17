import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./ProgressReportsDataType";

export async function saveProgressReport(data: Data) {
  const res = await axiosInstance.put(
    `/familyHealing/service/saveProgressReport`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function readAllProgressReport(id: number) {
  const res = await axiosInstance.get(
    `/familyHealing/service/readAllProgressReport/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function doRemove(id: number) {
  const res = await axiosInstance.delete(
    `/familyHealing/service/removeProgressReport/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function readProgressReport(id: number) {
  const res = await axiosInstance.get(
    `familyHealing/service/readProgressReport/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );

  return res;
}
