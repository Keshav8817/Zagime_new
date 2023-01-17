import axiosInstance from "../../../library/axiosInstance";

import type { Counselor } from "./counselorsDatatypes";

export async function getCounselorCFSWorker(id: number) {
  const res = await axiosInstance.get(
    `participantservice/getAllCounselorCFSWorkers/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function postCounselorCFSWorker(data: Counselor) {
  const res = await axiosInstance.put(
    `participantservice/saveAllCounselorCFSWorkers`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function removeCounselorCFSWorker(id: number) {
  const res = await axiosInstance.delete(
    `/participantservice/removeCounselorCFSWorker/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
