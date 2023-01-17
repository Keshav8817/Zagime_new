import axiosInstance from "../../../library/axiosInstance";
import type { CriminalHistory } from "./criminalHistoryDatatypes";

export async function getCriminalHistory(id: number) {
  const res = await axiosInstance.get(
    `participantservice/readCriminalHistory/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function postCriminalHistory(data: CriminalHistory) {
  const res = await axiosInstance.put(
    `participantservice/saveCriminalHistory`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function remove(id: number) {
  const res = await axiosInstance.delete(
    `participantservice/removeAddMoreCriminalHistory/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function removeCriminalHistory(id: number) {
  const res = await axiosInstance.delete(
    `participantservice/removeCriminalHistory/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
