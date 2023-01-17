import axiosInstance from "../../../library/axiosInstance";
import type {
  ModuleContextState,
  ModuleDispatchContextState,
} from "../../../contexts/ModuleContext";
import type { HouseholdMember } from "../../../pages/cyfms/householdMembers/householdMembersDatatypes";

/**
 * Callback of "Remove" button.
 * @param event - Mouse event object
 * @param householdMemberId - Household Member Id
 * @param moduleContext - Module context
 * @param moduleDispatchContext - Module actions dispatcher context
 */
export const handleRemoveRecord: AppMouseEventHandler<HTMLButtonElement> = (
  event,
  householdMemberId: number,
  moduleContext: ModuleContextState<HouseholdMember[]>,
  moduleDispatchContext: ModuleDispatchContextState
) => {
  event.preventDefault();
  axiosInstance
    .delete<HouseholdMember[]>(
      `participantservice/removeAddMoreHouseholdMember/${householdMemberId}`,
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
              householdMemberId: 0,
              name: "",
              gender: "",
              dateOfBirth: "",
              relationship: "",
              residing: "",
            } as HouseholdMember,
          ],
        });
      }
    });
};
