import { createContext, useState } from "react";
import type { FC, PropsWithChildren } from "react";

/**
 * `AddMoreContextState` is the state of `AddMoreContext`.
 */
export interface AddMoreContextState {
  /** List */
  list: any[];
  /** List setter */
  setList: any;
}

/**
 * `AttachmentsContext` is used by page which have 'Add More' button.
 */
const AddMoreContext = createContext<AddMoreContextState>({
  list: [],
  setList: null,
});

/**
 * `AddMoreContextProvider` is specialized utlity component \
 * which provides dynamic behaviour to it.
 * @example
 * ```jsx
 * <AddMoreContextProvider>
 *   ...
 * </AddMoreContextProvider>
 * ```
 * @param props
 */
export const AddMoreContextProvider: FC<PropsWithChildren> = (props) => {
  const [list, setList] = useState<any[]>([]);
  return (
    <AddMoreContext.Provider value={{ list: list, setList: setList }}>
      {props.children}
    </AddMoreContext.Provider>
  );
};

export default AddMoreContext;
