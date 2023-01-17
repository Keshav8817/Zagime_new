import axiosInstance from "../../../library/axiosInstance";

export async function getICReminder(id: number) {
  const res = await axiosInstance.get(
    `initialcontactservice/readICReminder/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function postICReminder(data: any) {
  const res = await axiosInstance.put(
    `initialcontactservice/saveICReminder`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function searchICReminder(id: number, data: any) {
  const res = await axiosInstance.get(
    `initialcontactservice/searchICReminder/${id}/${data ? data : null}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function deleteICReminder(id: number) {
  const res = await axiosInstance.delete(
    `initialcontactservice/removeICReminder/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
