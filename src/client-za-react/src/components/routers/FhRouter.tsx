import React from "react";
import { Route, Routes } from "react-router-dom";
import type { FC } from "react";
import FamilyHealingPage from "../../pages/familyHealing/fh/FamilyHealingPage";
import FileDetails from "../../pages/familyHealing/fileDetails/FileDetails";
import Referral from "../../pages/familyHealing/referral/Referral";
import History from "../../pages/familyHealing/history/History";
import Approval from "../../pages/familyHealing/approval/Approval";
import NeedsAssessment from "../../pages/familyHealing/needsAssessment/NeedsAssessment";
import CasePlan from "../../pages/familyHealing/caseplan/CasePlan";
import SafetyPlan from "../../pages/familyHealing/safetyplan/SafetyPlan";
import ProgressReports from "../../pages/familyHealing/progressReports/ProgressReports";
import Consent from "../../pages/familyHealing/consentforms/Consent";
import { NeedAssessmentAdd } from "../../pages/familyHealing/needsAssessment/Add";
import { NeedsAssessmentEdit } from "../../pages/familyHealing/needsAssessment/Edit";
import { CasePlanAdd } from "../../pages/familyHealing/caseplan/Add";
import SafetyPlanAdd from "../../pages/familyHealing/safetyplan/SafetyPlanAdd";
import ConsentAdd from "../../pages/familyHealing/consentforms/ConsentAdd";
import { ProgressReportsAdd } from "../../pages/familyHealing/progressReports/Add";
import { CasePlanEdit } from "../../pages/familyHealing/caseplan/Edit";
import { ProgressReportsEdit } from "../../pages/familyHealing/progressReports/Edit";
import SafetyPlanEdit from "../../pages/familyHealing/safetyplan/SafetyPlanEdit";
import ConsentEdit from "../../pages/familyHealing/consentforms/ConsentEdit";
import { ModuleContextProvider } from "../../contexts/ModuleContext";
import SearchPage from "../../pages/familyHealing/search/SearchPage";
import ConsentView from "../../pages/familyHealing/consentforms/ConsentView";

/**
 * `CgRouter` is used in Popup of `CG` aka `Caregivers` module \s
 *  and holds all of it's associated routes.
 */
const FhRouter: FC = () => (
  <ModuleContextProvider>
    <Routes>
      <Route path="*" element={<FamilyHealingPage />} />
      <Route path="fileDetails/:id" element={<FileDetails />} />
      <Route path="referral/:id" element={<Referral />} />
      <Route path="history/:id" element={<History />} />
      <Route path="approval/:id" element={<Approval />} />
      <Route path="needsAssessment/:id" element={<NeedsAssessment />} />
      <Route path="needsAssessment/add/:id" element={<NeedAssessmentAdd />} />
      <Route
        path="needsAssessment/edit/:id/:childId"
        element={<NeedsAssessmentEdit />}
      />
      <Route path="casePlan/:id" element={<CasePlan />} />
      <Route path="casePlan/add/:id" element={<CasePlanAdd />} />
      <Route path="casePlan/edit/:id/:childId" element={<CasePlanEdit />} />
      <Route path="SafetyPlan/:id" element={<SafetyPlan />} />
      <Route path="SafetyPlan/add/:id" element={<SafetyPlanAdd />} />
      <Route path="SafetyPlan/edit/:id/:childId" element={<SafetyPlanEdit />} />
      <Route path="consentForms/:id" element={<Consent />} />
      <Route path="consentForms/add/:id" element={<ConsentAdd />} />
      <Route path="consentForms/edit/:id/:childId" element={<ConsentEdit />} />
      <Route path="consentForms/view/:id/:childId" element={<ConsentView />} />
      <Route path="progressReports/:id" element={<ProgressReports />} />
      <Route path="progressReports/add/:id" element={<ProgressReportsAdd />} />

      <Route
        path="progressReports/edit/:id/:childId"
        element={<ProgressReportsEdit />}
      />
      {["fileDetails", "fileDetails/:id"].map((path, index) => (
        <Route path={path} element={<FileDetails />} />
      ))}
      <Route path="search*" element={<SearchPage />} />
    </Routes>
  </ModuleContextProvider>
);

export default FhRouter;
