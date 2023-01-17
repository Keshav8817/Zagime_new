import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./AppointmentDataType";

export async function getICAppointment(id: number) {
  const res = await axiosInstance.get(
    `initialcontactservice/readOneAppointment/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function postICAppointment(data: any) {
  const res = await axiosInstance.put(
    `initialcontactservice/saveICAppointment`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function searchICAppointment(id: number, data: any) {
  const res = await axiosInstance.get(
    `initialcontactservice/searchICAppointment/${id}/${data ? data : null}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function deleteICAppointment(id: number) {
  const res = await axiosInstance.delete(
    `initialcontactservice/deleteICAppointment/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
