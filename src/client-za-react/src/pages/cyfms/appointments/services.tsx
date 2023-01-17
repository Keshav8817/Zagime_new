import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./AppointmentsDatatypes";

export async function getAppointments(id: number) {
  const res = await axiosInstance.get(
    `/participantservice/readOneAppointment/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function postAppointments(data: Data) {
  const res = await axiosInstance.put(
    `/participantservice/saveParticipantAppointment`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function searchAppointments(id: number, data: any) {
  const res = await axiosInstance.get(
    `/participantservice/searchParticipantAppointent/${id}/${
      data ? data : null
    }`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function deleteAppointments(id: number) {
  const res = await axiosInstance.delete(
    `/participantservice/deleteParticipantAppointment/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
