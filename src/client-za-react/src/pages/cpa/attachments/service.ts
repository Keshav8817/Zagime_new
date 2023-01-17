import axiosInstance from "../../../library/axiosInstance";
import { Attachment } from "./AttachmentDataTypes";
export async function getCPAAttachments(cpaFileId: number) {
  console.log("nfnf", cpaFileId);
  const res = await axiosInstance.get(`cpa/attachments/read_one/${cpaFileId}`, {
    headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
  });

  return res;
}

export async function postCPAAttachments(data: any) {
  const res = await axiosInstance.put(`/cpa/attachments/save_one`, data, {
    headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
  });
  return res;
}

export async function searchCPAAttachments(cpaId: number) {
  const res = await axiosInstance.get(`cpa/attachments/read_all/${cpaId}`, {
    headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
  });
  return res;
}

export async function deleteCPAAttachments(cpaFileId: number) {
  const res = await axiosInstance.delete(
    `cpa/attachments/read_one/${cpaFileId}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
