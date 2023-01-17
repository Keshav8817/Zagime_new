import { createContext, useState } from "react";
import type { FC, PropsWithChildren } from "react";

/**
 * `AttachmentsContextState` is the state of `AttachmentsContext`.
 */
export interface AttachmentsContextState {
  /**
   * Stores the current selected attachment index from the
   * list shown on `/attachments` to later `/view`, `/edit`
   * and `/delete` it.
   */
  attachment: any;
  /**
   * Sets the current selected attachment index from the
   * list shown on `/attachments` to later `/view`, `/edit`
   * and `/delete` it.
   * @returns void
   */
  setAttachment: any;
}

/**
 * `AttachmentsContext` is used by `/attachments` route and its sub \
 * routes: `/view`, `/edit` and `/delete` to share a common state.
 */
const AttachmentsContext = createContext<AttachmentsContextState>({
  attachment: undefined,
  setAttachment: undefined,
});

/**
 * `AttachmentsContextProvider` is specialized utlity component \
 * which provides dynamic behaviour to it.
 * @example
 * ```jsx
 * <AttachmentsContextProvider>
 *   ...
 * </AttachmentsContextProvider>
 * ```
 * @param props
 */
export const AttachmentsContextProvider: FC<PropsWithChildren> = (props) => {
  const [attachment, setAttachment] = useState();
  return (
    <AttachmentsContext.Provider
      value={{ attachment: attachment, setAttachment: setAttachment }}
    >
      {props.children}
    </AttachmentsContext.Provider>
  );
};

export default AttachmentsContext;
