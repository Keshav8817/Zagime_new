import axiosInstance from "../../../library/axiosInstance";
import type { AddMoreContextState } from "../../../contexts/AddMoreContext";
// import type { inventory } from "../../../pages/hr/inventory/inventoryDatatypes";
import type {
  ModuleContextState,
  ModuleDispatchContextState,
} from "../../../contexts/ModuleContext";
import type { inventory } from "../../../pages/hr/inventory/inventoryDatatypes";
/**
 * Callback of "Remove" button.
 * @param event - Mouse event object
 * @param staffInventoryId - Redux action(s) dispatcher
 * @param staffID - Staff ID
 * @param addMoreContext - "Add More" context
 * @param recordNumber - Record number
 */
export const handleRemoveRecord: AppMouseEventHandler<HTMLButtonElement> = (
  event,
  staffInventoryId: number,
  moduleContext: ModuleContextState<inventory[]>,
  moduleDispatchContext: ModuleDispatchContextState
) => {
  event.preventDefault();
  /** Call API ONLY when household member ID is valid. */
  axiosInstance
    .delete<inventory[]>(
      `/hrservice/inventory/remove_one/${staffInventoryId}`,
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
        moduleDispatchContext({
          type: "set_data",
          data: [
            {
              staffId: moduleContext.id,
              staffInventoryId: 0,
              item: "",
              serialNo: 0,
              from: "",
              to: "",
              inUse: false,
              notes: "",
            },
          ],
        });
      }
    });
  /** Remove record from UI. */
};
