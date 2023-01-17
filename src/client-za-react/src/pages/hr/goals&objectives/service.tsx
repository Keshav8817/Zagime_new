import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./GoalsObjectiveDataTypes";

export async function readGoalsAndObjective(id: number) {
  const res = await axiosInstance.get(`staff/read/goalsAndObjectives/${id}`, {
    headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
  });
  return res;
}

export async function saveGoalsAndObjective(data: Data) {
  const res = await axiosInstance.put(`/staff/save/goalsAndObjectives`, data, {
    headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
  });
  return res;
}

export async function readAllGoalsAndObjective(id: number) {
  const res = await axiosInstance.get(
    `staff/readAll/goalsAndObjectives/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function deleteGoalsAndObjective(id: number) {
  const res = await axiosInstance.delete(
    `staff/remove/goalsAndObjectives/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
