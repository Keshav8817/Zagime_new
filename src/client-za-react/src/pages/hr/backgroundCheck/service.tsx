import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./BackgroundCheckDatatypes";

export async function readBackgroundCheck(id: number) {
  const res = await axiosInstance.get(
    `staffservice/readBackgroundCheck/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function saveBackgroundCheck(data: Data) {
  const res = await axiosInstance.put(
    `/staffservice/saveBackgroundCheck`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function readAllBackgroundCheck(id: number) {
  const res = await axiosInstance.get(
    `staffservice/readAllBackgroundCheck/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function deleteBackgroundCheck(id: number) {
  const res = await axiosInstance.delete(
    `staffservice/removeBackgroundCheck/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
