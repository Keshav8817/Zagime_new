import axios from "axios";
import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./ParticipantsDatatypes";

export async function saveParticiapnts(data: Data) {
  const res = await axiosInstance
    .put("/culturalprogandactservice/saveParticipantCulturalProg", data, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("jwtToken"),
      },
    })
    .then((response) => response.data);
  return res;
}

export async function readParticiapnts(id: number) {
  const res = await axiosInstance.get(
    `culturalprogandactservice/readParticipantsCulturalAndAct/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function searchCPAParticipant(id: number, data: any) {
  const res = await axiosInstance.get(
    `culturalprogandactservice/participantCulturalProgSearch/${id}/${
      data ? data : null
    }`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}

export async function deleteCPAParticipant(id: number) {
  const res = await axiosInstance.delete(
    `culturalprogandactservice/removeParticipantCulturalProg/${id}`,
    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
