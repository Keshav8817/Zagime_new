import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./fileDetailsDataType";

export async function saveFileDetails(data: Data) {
  const res = await axiosInstance.put(
    `/familyHealing/service/saveFileDetails`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function readFileDetails(id: number) {
  const res = await axiosInstance.get(
    `/familyHealing/service/readFileDetails/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function doRemove(id: number) {
  const res = await axiosInstance.delete(
    `/familyHealing/service/removeFileDetails/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
