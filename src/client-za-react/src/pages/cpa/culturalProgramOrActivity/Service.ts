import axios from "axios";
import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./CulturalProgramAndActivityDataTypes";

// export async function saveCpa(data: Data) {
//   const res = await axiosInstance
//     .put("/culturalprogandactservice/saveCulturalProgAndAct", data, {
//       headers: {
//         Authorization: "Bearer " + localStorage.getItem("jwtToken"),
//       },
//     })
//     .then((response) => response.data);
//   return res;
// }
export async function saveCpa(data: Data) {
  const res = await axiosInstance.put(
    `/culturalprogandactservice/saveCulturalProgAndAct`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function readCpa(id: number) {
  const res = await axiosInstance.get(
    `/culturalprogandactservice/readCulturalProgAndAct/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );

  return res;
}

export async function removeCulturalProgAndAct(id: number) {
  const res = await axiosInstance.delete(
    `/culturalprogandactservice/removeCulturalProgAndAct/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );

  return res;
}
