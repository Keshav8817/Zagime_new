import useNavigate from "react-router-dom";
import type { FormEvent, ReactElement } from "react";
import axiosInstance from "../../../library/axiosInstance";
import axios from "axios";
import { Data } from "./contactInformationDatatypes";

export async function saveContactInformation(data: Data) {
  const res = await axiosInstance.put(
    `/staffservice/saveContactInformation`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function readContactInformation(id: number) {
  const res = await axiosInstance.get(
    `/staffservice/readContactInformation/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function doRemoveIC(id: number) {
  const res = await axiosInstance.delete(
    `/staffservice/removeContactInformation/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
