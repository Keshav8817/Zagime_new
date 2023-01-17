import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./HistorYDatatype";

export async function saveHistory(data: Data) {
  const res = await axiosInstance.put(
    `familyHealing/service/saveHistory`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function readHistory(id: number, data: Data) {
  const res = await axiosInstance.get(
    `/familyHealing/service/readHistory/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function doRemoveHistory(id: number) {
  const res = await axiosInstance.delete(
    `/familyHealingService/removeHistory/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
