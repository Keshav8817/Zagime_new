import axiosInstance from "../../../library/axiosInstance";

export async function getHrReminder(id: number) {
  const res = await axiosInstance.get(
    `participantservice/readParticipantReminder/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function postHrReminder(data: any) {
  const res = await axiosInstance.put(
    `participantservice/saveParticipantReminder`,
    data,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function searchHrReminder(id: number, data: any) {
  const res = await axiosInstance.get(
    `participantservice/searchParticipantReminder/${id}/${data ? data : null}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function deleteHrReminder(id: number) {
  const res = await axiosInstance.delete(
    `participantservice/removeParticipantReminder/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
