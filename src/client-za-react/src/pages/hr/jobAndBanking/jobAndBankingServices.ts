import useNavigate from "react-router-dom";
import type { FormEvent, ReactElement } from "react";
import axiosInstance from "../../../library/axiosInstance";
import axios from "axios";
import { Data } from "./jobAndBankingDatatypes";

export async function saveJobAndBanking(data: Data) {
  const res = await axiosInstance.put(`/staffservice/saveJobAndBanking`, data, {
    headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
  });
  return res;
}

export async function readJobAndBanking(id: number) {
  const res = await axiosInstance.get(`/staffservice/readJobAndBanking/${id}`, {
    headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
  });
  return res;
}
export async function doRemoveGoalsObjective(id: number) {
  const res = await axiosInstance.delete(
    `/staffservice/removeJobAndBanking/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
