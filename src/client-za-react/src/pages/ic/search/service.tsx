import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./SearchDataType";

export async function getAllIcRecord(data: Data) {
  const res = await axiosInstance.get(
    `initialcontactservice/search/${data.clientName}/${data.fileNumber}/${data.caseworker}/${data.startingDate}/${data.status}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
