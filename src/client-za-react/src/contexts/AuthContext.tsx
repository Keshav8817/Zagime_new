import { createContext, useState } from "react";
import type { FC, PropsWithChildren } from "react";

export interface AuthContextState {
  jwtToken: string;
  setJwtToken: any;
}

const AuthContext = createContext<AuthContextState>({
  jwtToken: "",
  setJwtToken: null,
});

export const AuthContextProvider: FC<PropsWithChildren> = (props) => {
  const [jwtToken, setJwtToken] = useState<string>("");
  return (
    <AuthContext.Provider
      value={{ jwtToken: jwtToken, setJwtToken: setJwtToken }}
    >
      {props.children}
    </AuthContext.Provider>
  );
};

export default AuthContext;
