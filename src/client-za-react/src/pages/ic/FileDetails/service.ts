import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./FileDetailsDataType";

export async function getAllFileDetails(id: number) {
  const res = await axiosInstance.get(
    `/initialcontactservice/readAllFileDetails/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function postAllFileDetails(data: Data) {
  const res = await axiosInstance.put(
    `initialcontactservice/saveAllFileDetails`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function getEditButton() {
  const res = await axiosInstance.get(`/initialcontactservice/edits`, {
    headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
  });
  return res;
}

export async function remove(id: number) {
  const res = await axiosInstance.delete(
    `/initialcontactservice/remove/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
