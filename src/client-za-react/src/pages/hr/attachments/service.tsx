import axiosInstance from "../../../library/axiosInstance";
import { Attachment } from "./AttachmentsDatatypes";

export async function getHrAttachments(id: number) {
  const res = await axiosInstance.get(
    `staffservice/attachments/read_one/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function postHrAttachments(data: any) {
  const res = await axiosInstance.put(
    `staffservice/attachments/save_one`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function searchHrAttachments(id: number) {
  const res = await axiosInstance.get(
    `staffservice/attachments/read_all/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function deleteHrAttachments(id: number) {
  const res = await axiosInstance.delete(
    `staffservice/attachments/remove_one/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
