import { createContext } from "react";

/**
 * State of `TabContext`.
 */
export interface State {
  /**
   * Specified the active tab. It is 1-indexed,
   * i. e., it starts from 1.
   */
  tabNumber: number;
}

/**
 * `/view` page context.
 * Used on all modules' `/view` pages.
 */
const TabContext = createContext<State>({ tabNumber: 1 });

export default TabContext;
