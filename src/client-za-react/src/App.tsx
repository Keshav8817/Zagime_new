import "@fontsource/roboto/300.css";
import "@fontsource/roboto/400.css";
import "@fontsource/roboto/500.css";
import "@fontsource/roboto/700.css";
// import { persistor, store } from "./library/store";
import theme from "./library/theme";
import Home from "./pages/Home";
import Login from "./pages/Login";
import NotFound404 from "./pages/NotFound404";
import Calendar_ from "./pages/Calendar/Calendar_";
import CyfmsRouter from "./components/routers/CyfmsRouter";
import CpaRouter from "./components/routers/CpaRouter";
import HrRouter from "./components/routers/HrRouter";
import IcRouter from "./components/routers/IcRouter";
import CpaSearchPage from "./pages/cpa/search/SearchPage";
//import CpaViewPage from "./pages/cpa/view/ViewPage";
import CyfmsSearchPage from "./pages/cyfms/search/SearchPage";
// import CyfmsViewPage from "./pages/cyfms/view/ViewPage";
// import HrSearchPage from "./pages/hr/search/SearchPage";
// import HrViewPage from "./pages/hr/view/ViewPage";
import IcSearchPage from "./pages/ic/search/SearchPage";
// import IcViewPage from "./pages/ic/view/ViewPage";
import { ThemeProvider } from "@mui/material/styles";
import React, { StrictMode } from "react";
// import { Provider } from "react-redux";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
// import { PersistGate } from "redux-persist/integration/react";
import type { FC } from "react";
import FhRouter from "./components/routers/FhRouter";
import FhSearchPage from "./pages/familyHealing/search/SearchPage";

/**
 * `App` is primary router of CYFWMS application.
 * @returns `ReactElement`
 */
const App: FC = () => {
  return (
    <StrictMode>
      {/* <Provider store={store}> */}
      {/* <PersistGate loading={null} persistor={persistor}> */}
      <ThemeProvider theme={theme}>
        <BrowserRouter>
          <Routes>
            <Route path="*" element={<NotFound404 />} />
            <Route path="/" element={<Navigate to="login" />} />
            <Route path="home" element={<Home />} />
            <Route path="login" element={<Login />} />
            <Route path="calendar/*" element={<Calendar_ />} />
            <Route path="cyfms/*" element={<CyfmsRouter />} />
            {/* <Route path="cyfms/search/*" element={<CyfmsSearchPage />} /> */}
            {/* <Route path="cyfms/view/*" element={<CyfmsViewPage />} /> */}
            <Route path="initial_contact/*" element={<IcRouter />} />
            {/* <Route path="initial_contact/search/*" element={<IcSearchPage />} /> */}
            {/*<Route path="initial_contact/view/*" element={<IcViewPage />} />*/}
            <Route path="cpa/*" element={<CpaRouter />} />
            {/* <Route path="cpa/search/*" element={<CpaSearchPage />} /> */}
            {/* <Route path="cpa/view/*" element={<CpaViewPage />} /> */}
            <Route path="hr/*" element={<HrRouter />} />
            {/* <Route path="hr/search/*" element={<HrSearchPage />} /> */}
            {/* <Route path="hr/view/*" element={<HrViewPage />} /> */}
            <Route path="fh/*" element={<FhRouter />} />
            {/* <Route path="fh/search*" element={<FhSearchPage />} /> */}
          </Routes>
        </BrowserRouter>
      </ThemeProvider>
      {/* </PersistGate> */}
      {/* </Provider> */}
    </StrictMode>
  );
};

export default App;
