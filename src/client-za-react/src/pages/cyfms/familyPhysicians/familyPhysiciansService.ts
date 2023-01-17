import axiosInstance from "../../../library/axiosInstance";

import type { FamilyPhysician } from "./familyPhysiciansDatatypes";

export async function getFamilyPhysician(id: number) {
  const res = await axiosInstance.get(
    `participantservice/getAllFamilyPhysicians/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function postFamilyPhysician(data: FamilyPhysician) {
  const res = await axiosInstance.put(
    `participantservice/saveAllFamilyPhysicians`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function removeFamilyPhysician(id: number) {
  const res = await axiosInstance.delete(
    `/participantservice/removeFamilyPhysician/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
