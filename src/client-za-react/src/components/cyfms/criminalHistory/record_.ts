import axiosInstance from "../../../library/axiosInstance";
import type { AddMoreContextState } from "../../../contexts/AddMoreContext";
import type { CriminalHistoryRecord } from "../../../pages/cyfms/criminalHistory/criminalHistoryDatatypes";

/**
 * Callback of "Remove" button.
 * @param event - Mouse event object
 * @param criminalHistoryId - Criminal History Id
 * @param criminalHistoryRecordId - Criminal History Record Id
 * @param addMoreContext - "Add More" context
 * @param recordNumber - Record number
 */
export const handleRemoveRecord: AppMouseEventHandler<HTMLButtonElement> = (
  event,
  criminalHistoryId: number,
  criminalHistoryRecordId: number,
  addMoreContext: AddMoreContextState,
  recordNumber: number
) => {
  event.preventDefault();
  axiosInstance
    .delete(
      `participantservice/removeAddMoreCriminalHistory/${criminalHistoryRecordId}`,
      {
        headers: {
          authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
        },
      }
    )
    .then((response) => {
      /** Remove record from UI. */
      addMoreContext.setList(response.data);
      // addMoreContext.list.splice(recordNumber - 1, 1);
      // if (addMoreContext.list.length === 0) {
      //   addMoreContext.setList([
      //     {
      //       criminalHistoryId: criminalHistoryId,
      //       criminalHistoryRecordId: 0,
      //       arrestDate: "",
      //       charges: "",
      //       conviction: "",
      //       sentence: "",
      //     } as CriminalHistoryRecord,
      //   ]);
      // } else {
      //   addMoreContext.setList(addMoreContext.list.splice(recordNumber - 1, 1));
      // }
    });
};
