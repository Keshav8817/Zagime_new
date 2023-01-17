import { AttachmentsContextProvider } from "../../contexts/AttachmentsContext";
import Add from "../../pages/cpa/attachments/Add";
import Attachments from "../../pages/cpa/attachments/Attachments";
import Edit from "../../pages/cpa/attachments/Edit";
import View from "../../pages/cpa/attachments/View";
import CulturalProgramOrActivity from "../../pages/cpa/culturalProgramOrActivity/CulturalProgramOrActivity";
import CpaPage from "../../pages/cpa/cpa/CpaPage";
import Participants from "../../pages/cpa/Participants/Participants";
import React from "react";
import { Route, Routes } from "react-router-dom";
import type { FC } from "react";
import { ParticipantEdit } from "../../pages/cpa/Participants/Edit";
import { ParticipantAdd } from "../../pages/cpa/Participants/Add";
import SearchPage from "../../pages/cpa/search/SearchPage";
import { ModuleContextProvider } from "../../contexts/ModuleContext";

/**
 * `CpaRouter` is used in Popup of `CPA` aka \
 * `Cultural Programs and Activities` module \
 *  and holds all of it's associated routes.
 */
const CpaRouter: FC = () => (
  <ModuleContextProvider>
    <Routes>
      <Route path="/" element={<CpaPage />} />
      {/* <Route path="add_cpa" element={<CulturalProgramOrActivity />} /> */}

      <Route path="add_cpa/:id" element={<CulturalProgramOrActivity />} />
    </Routes>
    <Routes>
      <Route path="participants/:id" element={<Participants />} />
      <Route path="participants/add/:id" element={<ParticipantAdd />} />
      <Route
        path="participants/edit/:id/:childId"
        element={<ParticipantEdit />}
      />
    </Routes>
    <AttachmentsContextProvider>
      <Routes>
        <Route path="attachments/:id" element={<Attachments />} />
        <Route path="attachments/add/:id" element={<Add />} />
        <Route path="attachments/view/:id/:childId" element={<View />} />
        <Route path="attachments/edit/:id/:childId" element={<Edit />} />
      </Routes>
    </AttachmentsContextProvider>
    <Routes>
      <Route path="search/*" element={<SearchPage />} />
    </Routes>
  </ModuleContextProvider>
);

export default CpaRouter;
