import { createContext, useReducer } from "react";
import type { Dispatch, FC, PropsWithChildren } from "react";

/**
 * `ModuleContextState` is state of {@link ModuleContext}.
 */
export interface ModuleContextState<D> {
  /** Identity */
  id: number;
  /** Not to show cross button on registration */
  crossButton: boolean;
  /** Edit mode */
  editMode: boolean;
  /** Are tabs disabled? */
  tabsDisabled?: boolean;
  /** Data */
  data?: D;
}

/**
 * `ModuleContext` is used to share module related data among \
 * nested components of module.
 */
export const ModuleContext = createContext<ModuleContextState<any>>({
  id: 0,
  crossButton: true,
  editMode: false,
  tabsDisabled: true,
});

/**
 * `ModuleDispatchContextState` is state of {@link ModuleDispatchContext}.
 */
export type ModuleDispatchContextState = Dispatch<any>;

/**
 * `ModuleDispatchContext` is used to share action dispatcher among \
 * nested components of module.
 */
export const ModuleDispatchContext = createContext<ModuleDispatchContextState>(
  () => {}
);

const moduleReducer = (module_: ModuleContextState<any>, action: any) => {
  switch (action.type) {
    case "change_id": {
      module_.id = action.id;
      break;
    }
    case "toggle_crossButton": {
      module_ = { ...module_ };
      module_.crossButton = action.crossButton;
      break;
    }
    case "toggle_editMode": {
      module_ = { ...module_ };
      module_.editMode = action.editMode;
      break;
    }
    case "toggle_tabsDisabled": {
      module_.tabsDisabled = action.tabsDisabled;
      break;
    }
    case "set_data": {
      module_ = { ...module_ };
      module_.data = action.data;
      break;
    }
    case "clean_context": {
      module_ = {
        id: 0,
        crossButton: true,
        editMode: false,
        tabsDisabled: true,
        data: undefined,
      };
      break;
    }
  }
  return module_;
};

/**
 * `ModuleContextProvider` is specialized utlity component \
 * which provides dynamic behaviour to it.
 * @param props
 * @example
 * ```jsx
 * <ModuleContextProvider>
 *   ...
 * </ModuleContextProvider>
 * ```
 * @example
 * ```jsx
 * <ModuleContextProvider children={} />
 * ```
 */
export const ModuleContextProvider: FC<PropsWithChildren> = (props) => {
  const [module_, dispatch] = useReducer(moduleReducer, {
    id: 0,
    crossButton: true,
    editMode: false,
    tabsDisabled: true,
  });

  return (
    <ModuleDispatchContext.Provider value={dispatch}>
      <ModuleContext.Provider value={module_}>
        {props.children}
      </ModuleContext.Provider>
    </ModuleDispatchContext.Provider>
  );
};
