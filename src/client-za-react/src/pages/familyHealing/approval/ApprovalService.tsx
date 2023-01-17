import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./ApprovalDataType";

export async function saveApproval(data: Data) {
  const res = await axiosInstance.put(
    `/familyHealing/service/saveApproval`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function readApproval(id: number, data: Data) {
  const res = await axiosInstance.get(
    `/familyHealing/service/readApproval/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function doRemoveApproval(id: number) {
  const res = await axiosInstance.delete(
    `/familyHealingService/removeApproval/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
