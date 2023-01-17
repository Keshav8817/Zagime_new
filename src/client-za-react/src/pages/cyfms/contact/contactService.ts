import axiosInstance from "../../../library/axiosInstance";
import { Contact } from "./contactDatatypes";

export async function getContact(id: number) {
  const res = await axiosInstance.get(
    `participantservice/readParticipantContact/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function postContact(data: Contact) {
  const res = await axiosInstance.put(
    `participantservice/saveParticipantContact`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function removeParticipantContact(id: number) {
  const res = await axiosInstance.delete(
    `/participantservice/removeParticipantContact/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
