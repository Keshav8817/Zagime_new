import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./IncidentReportDataType";

export async function getAllIncidentReports(id: number) {
  const res = await axiosInstance.get(
    `initialcontactservice/readAllIncidentReports/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function postIncidentReports(data: Data) {
  const res = await axiosInstance.put(
    `initialcontactservice/saveAllIncidentReports`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function removeIncidentReport(id: number) {
  const res = await axiosInstance.delete(
    `/initialContactService/removeIncidentReport/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
