import axiosInstance from "../../../library/axiosInstance";
import type { inventory } from "./inventoryDatatypes";

export async function getInventory(id: number) {
  const res = await axiosInstance.get(`hrservice/inventory/read_all/${id}`, {
    headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
  });
  return res;
}

export async function PostInventory(data: inventory) {
  const res = await axiosInstance.put(`hrservice/inventory/save_all`, data, {
    headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
  });
  return res;
}

export async function removeInventory(id: number) {
  const res = await axiosInstance.delete(
    `hrservice/inventory/remove_one/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function removeStaffInvemtory(id: number) {
  const res = await axiosInstance.delete(
    `/staffservice/removeInventories/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
