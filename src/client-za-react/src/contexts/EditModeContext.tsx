import { createContext, useState } from "react";
import type { FC, PropsWithChildren } from "react";

/**
 * `EditModeContextState` is the state of `EditModeContext`.
 */
export interface EditModeContextState {
  /**
   * Stores the current selected attachment index from the
   * list shown on `/attachments` to later `/view`, `/edit`
   * and `/delete` it.
   */
  editMode: any;
  /**
   * Sets the current selected attachment index from the
   * list shown on `/attachments` to later `/view`, `/edit`
   * and `/delete` it.
   * @returns void
   */
  setEditMode: any;
  viewMode: any;
  setViewMode: any;
}

const EditModeContext = createContext<EditModeContextState>({
  editMode: undefined,
  setEditMode: undefined,
  viewMode: undefined,
  setViewMode: undefined,
});

/**
 * `EditModeContextProvider` is specialized utlity component \
 * which provides dynamic behaviour to it.
 * @example
 * ```jsx
 * <EditModeContextProvider>
 *   ...
 * </EditModeContextProvider>
 * ```
 * @param props
 */
export const EditModeContextProvider: FC<PropsWithChildren> = (props) => {
  const [editMode, setEditMode] = useState(localStorage.getItem(""));
  const [viewMode, setViewMode] = useState();

  return (
    <EditModeContext.Provider
      value={{
        editMode: editMode,
        setEditMode: setEditMode,
        viewMode: viewMode,
        setViewMode: setViewMode,
      }}
    >
      {props.children}
    </EditModeContext.Provider>
  );
};

export default EditModeContext;
