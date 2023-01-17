import axiosInstance from "../../../library/axiosInstance";
import type { HouseholdMember } from "./householdMembersDatatypes";

export async function getHouseHoldMember(id: number) {
  const res = await axiosInstance.get(
    `participantservice/getAllHouseholdMembers/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function PostHouseHoldMember(data: HouseholdMember) {
  const res = await axiosInstance.put(
    `participantservice/saveAllHouseholdMembers`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function removeHouseHoldMember(id: number) {
  const res = await axiosInstance.delete(
    `/participantservice/removeAddMoreHouseholdMember/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function removeAllHouseHoldMember(id: number) {
  const res = await axiosInstance.delete(
    `/participantservice/removeHouseHoldMember/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
