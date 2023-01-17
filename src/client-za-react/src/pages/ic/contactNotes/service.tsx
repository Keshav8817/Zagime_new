import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./ContactNotesDataType";

export async function getICContactNotes(id: number) {
  const res = await axiosInstance.get(
    `initialcontactservice/readAllContactNotes/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function postICContactNotes(data: Data) {
  const res = await axiosInstance.put(
    `initialcontactservice/saveAllContactNotes`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function searchICContactNotes(id: number, data: any) {
  const res = await axiosInstance.get(
    `initialcontactservice/searchContactNotes/${id}/${data ? data : null}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function deleteICContactNotes(id: number) {
  const res = await axiosInstance.delete(
    `initialcontactservice/removeContactNotes/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
