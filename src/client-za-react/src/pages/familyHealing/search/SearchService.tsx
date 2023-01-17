import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./SearchDatatype";

export async function doGetAllSearch(data: Data) {
  const res = await axiosInstance.get(
    `familyHealing/service/searchFileDetails/${data.clientName}/${data.fileNumber}/${data.status}/${data.community}/${data.caseworker}/${data.startDate}`,
    { headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") } }
  );

  return res;
}
