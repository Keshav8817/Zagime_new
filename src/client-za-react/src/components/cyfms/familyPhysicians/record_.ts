import axiosInstance from "../../../library/axiosInstance";
import type {
  ModuleContextState,
  ModuleDispatchContextState,
} from "../../../contexts/ModuleContext";
import type { FamilyPhysician } from "../../../pages/cyfms/familyPhysicians/familyPhysiciansDatatypes";

/**
 * Callback of "Remove" button.
 * @param event - Mouse event object
 * @param familyPhysicianId - Family Physician Id
 * @param moduleContext - Module context
 * @param moduleDispatchContext - Module actions dispatcher context
 */
export const handleRemoveRecord: AppMouseEventHandler<HTMLButtonElement> = (
  event,
  familyPhysicianId: number,
  moduleContext: ModuleContextState<FamilyPhysician[]>,
  moduleDispatchContext: ModuleDispatchContextState
) => {
  event.preventDefault();
  axiosInstance
    .delete<FamilyPhysician[]>(
      `participantservice/removeAddMoreFamilyPhysician/${familyPhysicianId}`,
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
              familyPhysicianId: 0,
              name: "",
              phone: "",
              cell: "",
              listOfMedication: "",
            } as FamilyPhysician,
          ],
        });
      }
    });
};
