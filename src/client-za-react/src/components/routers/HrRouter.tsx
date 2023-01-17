import { AttachmentsContextProvider } from "../../contexts/AttachmentsContext";

import HrPage from "../../pages/hr/hr/HrPage";
// import ContactInformation from "../../pages/hr/contactInformation/ContactInformation";
import JobBanking from "../../pages/hr/jobAndBanking/JobAndBankingPage";

import Inventory from "../../pages/hr/inventory/Inventory";
// import BackgroundCheck from "../../pages/hr/backgroundCheck/BackgroundCheck";
import Training from "../../pages/hr/trainings/TrainingsPage";
import GoalsObjectives from "../../pages/hr/goals&objectives/GoalsObjectives";
// import AddAttachmentPage from "../../pages/hr/attachments/AddAttachmentPage";
import AttachmentsPage from "../../pages/hr/attachments/Attachments";
// import EditAttachmentPage from "../../pages/hr/attachments/EditAttachmentPage";
// import ViewAttachmentPage from "../../pages/hr/attachments/ViewAttachmentPage";
import TrainingsPage from "../../pages/hr/trainings/TrainingsPage";
import ViewTrainingPage from "../../pages/hr/trainings/ViewTrainingPage";

import React from "react";
import { Route, Routes } from "react-router-dom";
import type { FC } from "react";

import ContactInformation from "../../pages/hr/contactInformation/ContactInformation";
import MedicalEmergency from "../../pages/hr/medicalEmergency/MedicalEmergency";
import { GoalsObjectiveAdd } from "../../pages/hr/goals&objectives/Add";
import { GoalsAndObjectiveEdit } from "../../pages/hr/goals&objectives/Edit";
import BackgroundCheck from "../../pages/hr/backgroundCheck/BackgroundCheck";
import { BackgroundCheckEdit } from "../../pages/hr/backgroundCheck/Edit";
import { BackgroundCheckAdd } from "../../pages/hr/backgroundCheck/Add";
import { TrainingAdd } from "../../pages/hr/trainings/Add";
import { TrainingEdit } from "../../pages/hr/trainings/Edit";
import Add from "../../pages/hr/attachments/Add";
import View from "../../pages/hr/attachments/View";
import Edit from "../../pages/hr/attachments/Edit";
import SearchPage from "../../components/hr/search/SearchPage";
import { AddMoreContextProvider } from "../../contexts/AddMoreContext";
import { ModuleContextProvider } from "../../contexts/ModuleContext";
import StaffPage from "../../pages/hr/staff/StaffPage";

/**
 * `CgRouter` is used in Popup of `CG` aka `Caregivers` module \s
 *  and holds all of it's associated routes.
 */
const HrRouter: FC = () => (
  <>
    <ModuleContextProvider>
      <Routes>
        <Route path="/" element={<HrPage />} />

        <Route path="staff/:id" element={<StaffPage />} />

        <Route
          path="contact_information/:id"
          element={<ContactInformation />}
        />
        <Route path="job&banking/:id" element={<JobBanking />} />
        <Route path="medical&emergency/:id" element={<MedicalEmergency />} />

        {/* <Route path="training" element={<Training />} /> */}
        <Route path="trainings/:id" element={<TrainingsPage />} />
        <Route path="trainings/add/:id" element={<TrainingAdd />} />
        {/* <Route path="trainings/view" element={<ViewTrainingPage />} /> */}
        <Route path="trainings/edit/:id/:childId" element={<TrainingEdit />} />
        <Route path="goals&objectives/:id" element={<GoalsObjectives />} />
        <Route
          path="goals&objectives/add/:id"
          element={<GoalsObjectiveAdd />}
        />
        <Route
          path="goals&objectives/edit/:id/:childId"
          element={<GoalsAndObjectiveEdit />}
        />
        <Route path="backgroundCheck/:id" element={<BackgroundCheck />} />
        <Route
          path="backgroundCheck/add/:id"
          element={<BackgroundCheckAdd />}
        />
        <Route
          path="backgroundCheck/edit/:id/:childId"
          element={<BackgroundCheckEdit />}
        />
        <Route path="contact_information" element={<ContactInformation />} />
      </Routes>
      <AttachmentsContextProvider>
        <Routes>
          <Route path="attachments/:id" element={<AttachmentsPage />} />
          <Route path="attachments/add/:id" element={<Add />} />
          <Route path="attachments/view/:id/:childId" element={<View />} />
          <Route path="attachments/edit/:id/:childId" element={<Edit />} />
        </Routes>
      </AttachmentsContextProvider>
      <Routes>
        {/* 
        <Route path="backgroundcheck" element={<BackgroundCheck />} /> */}
        <Route path="search/*" element={<SearchPage />} />
      </Routes>
      <AddMoreContextProvider>
        <Routes>
          <Route path="inventory/:id" element={<Inventory />} />
        </Routes>
      </AddMoreContextProvider>
    </ModuleContextProvider>
  </>
);

export default HrRouter;
