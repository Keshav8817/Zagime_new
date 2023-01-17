import useNavigate from "react-router-dom";
import type { FormEvent, ReactElement } from "react";
import axiosInstance from "../../../library/axiosInstance";
import axios from "axios";
import { Data } from "./medicalEmergencyDatatypes";

export async function saveMedicalEmergency(data: Data) {
  const res = await axiosInstance.put(
    `/staffservice/saveMedicalAndEmergency`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function readMedicalEmergency(id: number) {
  const res = await axiosInstance.get(
    `/staffservice/readMedicalAndEmergency/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function doRemoveMedicalEmergency(id: number) {
  const res = await axiosInstance.delete(
    `/staffservice/removeMedicalAndEmergency/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
