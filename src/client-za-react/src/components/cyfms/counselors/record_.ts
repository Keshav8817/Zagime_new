import axiosInstance from "../../../library/axiosInstance";
import type {
  ModuleContextState,
  ModuleDispatchContextState,
} from "../../../contexts/ModuleContext";
import type { Counselor } from "../../../pages/cyfms/counselors/counselorsDatatypes";

/**
 * Callback of "Remove" button.
 * @param event - Mouse event object
 * @param counselorId - Counselor Id
 * @param moduleContext - Module context
 * @param moduleDispatchContext - Module actions dispatcher context
 */
export const handleRemoveRecord: AppMouseEventHandler<HTMLButtonElement> = (
  event,
  counselorId: number,
  moduleContext: ModuleContextState<Counselor[]>,
  moduleDispatchContext: ModuleDispatchContextState
) => {
  event.preventDefault();
  axiosInstance
    .delete<Counselor[]>(
      `participantservice/removeAddMoreCounselorCFSWorker/${counselorId}`,
      {
        headers: {
          authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
        },
      }
    )
    .then((response) => {
      if (response.data.length > 0) {
        moduleDispatchContext({ type: "set_data", data: response.data });
      } else {
        // Insert an empty record if all are removed
        moduleDispatchContext({
          type: "set_data",
          data: [
            {
              participantId: moduleContext.id,
              counselorCFSWorkerId: 0,
              role: "",
              name: "",
              startDate: "",
              endDate: "",
              contactInformation: "",
            } as Counselor,
          ],
        });
      }
    });
};
