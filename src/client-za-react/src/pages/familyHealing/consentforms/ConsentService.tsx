import axiosInstance from "../../../library/axiosInstance";
import { Data } from "./ConsentDatatype";

export async function saveConsentForms(data: Data) {
  const res = await axiosInstance.put(
    `/familyHealing/service/saveConsentForms`,
    data,
    {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("jwtToken"),
        "Content-Type": "multipart/form-data",
      },
    }
  );
  return res;
}
export async function readAllConsentForms(id: number) {
  const res = await axiosInstance.get(
    `/familyHealing/service/readAllConsentForms/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function doRemove(id: number) {
  const res = await axiosInstance.delete(
    `/familyHealing/service/removeConsentForms/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );
  return res;
}
export async function readConsentForms(id: number) {
  const res = await axiosInstance.get(
    `familyHealing/service/readConsentForms/${id}`,

    {
      headers: { Authorization: "Bearer " + localStorage.getItem("jwtToken") },
    }
  );

  return res;
}
