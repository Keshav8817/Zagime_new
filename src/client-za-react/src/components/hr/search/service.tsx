import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./SearchDatatypes";

export async function getAllHrRecord(data: Data) {
  const res = await axiosInstance.get(
    `/staffservice/searchStaffs/${data.firstName}/${data.lastName}/${data.middleName}/${data.workLocation}/${data.supervisor}/${data.active}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
