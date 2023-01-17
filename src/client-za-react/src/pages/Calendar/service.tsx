import axiosInstance from "../../library/axiosInstance";

export async function getAllCaledarData() {
  const res = await axiosInstance.get(
    `commonservice/appointment/getAllCalenderData`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function getAllAppointmentByDate(date: string) {
  const res = await axiosInstance.get(
    `commonservice/appointment/getAllCalenderDate/${date}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function getAllReminderByDate(date: string) {
  const res = await axiosInstance.get(
    `commonservice/reminder/getAllCalenderDate/${date}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
