import useNavigate from "react-router-dom";
import type { FormEvent, ReactElement } from "react";
import axiosInstance from "../../../library/axiosInstance";
import axios from "axios";
import { Data } from "./staffDatatypes";

export async function saveStaff(data: Data) {
  const res = await axiosInstance.put(`/staffservice/saveStaff`, data, {
    headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
  });
  return res;
}

export async function readStaff(id: number) {
  const res = await axiosInstance.get(`/staffservice/readStaff/${id}`, {
    headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
  });
  return res;
}
export async function doRemove(id: number) {
  const res = await axiosInstance.delete(
    `/staffservice/removeStaff/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
