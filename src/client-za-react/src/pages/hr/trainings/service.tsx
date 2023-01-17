import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./TrainingDatatypes";

export async function readTraining(id: number) {
  const res = await axiosInstance.get(`/hrservice/trainings/read_one/${id}`, {
    headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
  });
  return res;
}

export async function saveTraining(data: Data) {
  const res = await axiosInstance.put(`/hrservice/trainings/save_one`, data, {
    headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
  });
  return res;
}

export async function readAllTraining(id: number, data: any) {
  const res = await axiosInstance.get(`/hrservice/trainings/read_all/${id}`, {
    headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
  });
  return res;
}

export async function deleteTraining(id: number) {
  const res = await axiosInstance.delete(
    `/hrservice/trainings/remove_one/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
