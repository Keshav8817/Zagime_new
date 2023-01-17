import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./ParticipantDataType";

export async function getICParticipant(id: number) {
  const res = await axiosInstance.get(
    `initialcontactservice/readICParticipant/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function postICParticipant(data: Data) {
  const res = await axiosInstance.put(
    `initialcontactservice/saveICParticipant`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function searchICParticipant(id: number, data: any) {
  const res = await axiosInstance.get(
    `initialcontactservice/participantICSearch/${id}/${data ? data : null}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function deleteICParticipant(id: number) {
  const res = await axiosInstance.delete(
    `initialcontactservice/removeICParticipant/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
