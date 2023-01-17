import axiosInstance from "../../../library/axiosInstance";
import { Attachment } from "./AttachmentDataTypes";

export async function getICAttachments(id: number) {
  const res = await axiosInstance.get(
    `initialcontactservice/attachments/read_one/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function postICAttachments(data: any) {
  const res = await axiosInstance.put(
    `initialcontactservice/attachments/save_one`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function searchICAttachments(id: number) {
  const res = await axiosInstance.get(
    `initialcontactservice/attachments/read_all/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function deleteICAttachments(id: number) {
  const res = await axiosInstance.delete(
    `initialcontactservice/attachments/remove_one/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
