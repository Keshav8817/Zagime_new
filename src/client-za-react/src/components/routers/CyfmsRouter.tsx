import { AddMoreContextProvider } from "../../contexts/AddMoreContext";
import { AttachmentsContextProvider } from "../../contexts/AttachmentsContext";
import { ModuleContextProvider } from "../../contexts/ModuleContext";
import AppointmentsPage from "../../pages/cyfms/appointments/Appointments";
import AddAttachmentPage from "../../pages/cyfms/attachments/AddAttachmentPage";
import AttachmentsPage from "../../pages/cyfms/attachments/AttachmentsPage";
import EditAttachmentPage from "../../pages/cyfms/attachments/EditAttachmentPage";
import ViewAttachmentPage from "../../pages/cyfms/attachments/ViewAttachmentPage";
import ContactPage from "../../pages/cyfms/contact/ContactPage";
import CounselorsPage from "../../pages/cyfms/counselors/CounselorsPage";
import CriminalHistoryPage from "../../pages/cyfms/criminalHistory/CriminalHistoryPage";
import CyfmsPage from "../../pages/cyfms/cyfms/CyfmsPage";
import EducationAndEmploymentPage from "../../pages/cyfms/educationAndEmployment/EducationAndEmploymentPage";
import FamilyPhysiciansPage from "../../pages/cyfms/familyPhysicians/FamilyPhysiciansPage";
import HouseholdMembersPage from "../../pages/cyfms/householdMembers/HouseholdMembersPage";
import OtherInformationPage from "../../pages/cyfms/otherInformation/OtherInformationPage";
import Reminders from "../../pages/cyfms/reminders/Reminders";
import RegisterPage from "../../pages/cyfms/register/RegisterPage";
import SearchPage from "../../pages/cyfms/search/SearchPage";
import React from "react";
import { Route, Routes } from "react-router-dom";
import type { FC } from "react";
import { CyfmsAppointmentAdd } from "../../pages/cyfms/appointments/Add";
import { CyfmsAppointmentEdit } from "../../pages/cyfms/appointments/Edit";
import { CyfmsReminderAdd } from "../../pages/cyfms/reminders/Add";
import { CyfmsReminderEdit } from "../../pages/cyfms/reminders/Edit";

/**
 * `CyfmsRouter` is used in Popup of `CYFMS` aka \
 * `Child, Youth, and Family Management Services` \
 * module and holds all the nested routes.
 */
const CyfmsRouter: FC = () => (
  <ModuleContextProvider>
    <Routes>
      <Route path="/" element={<CyfmsPage />} />
      <Route path="register/:id" element={<RegisterPage />} />
      <Route path="contact/:id" element={<ContactPage />} />
      <Route path="household_members/:id" element={<HouseholdMembersPage />} />
      <Route
        path="education_and_employment/:id"
        element={<EducationAndEmploymentPage />}
      />
      <Route path="family_physicians/:id" element={<FamilyPhysiciansPage />} />
      <Route path="counselors/:id" element={<CounselorsPage />} />
      <Route path="other_information/:id" element={<OtherInformationPage />} />
    </Routes>
    <AttachmentsContextProvider>
      <Routes>
        <Route path="attachments/:id" element={<AttachmentsPage />} />
        <Route path="attachments/add/:id" element={<AddAttachmentPage />} />
        <Route
          path="attachments/view/:id/:childId"
          element={<ViewAttachmentPage />}
        />
        <Route
          path="attachments/edit/:id/:childId"
          element={<EditAttachmentPage />}
        />
      </Routes>
      <Routes>
        <Route path="appointment/:id" element={<AppointmentsPage />} />
        <Route path="appointment/add/:id" element={<CyfmsAppointmentAdd />} />
        <Route
          path="appointment/edit/:id/:childId"
          element={<CyfmsAppointmentEdit />}
        />
      </Routes>
      <Routes>
        <Route path="reminder/:id" element={<Reminders />} />
        <Route path="reminder/add/:id" element={<CyfmsReminderAdd />} />
        <Route
          path="reminder/edit/:id/:childId"
          element={<CyfmsReminderEdit />}
        />
      </Routes>
    </AttachmentsContextProvider>
    <Routes>
      <Route path="search" element={<SearchPage />} />
    </Routes>
    <AddMoreContextProvider>
      <Routes>
        <Route path="criminal_history/:id" element={<CriminalHistoryPage />} />
      </Routes>
    </AddMoreContextProvider>
  </ModuleContextProvider>
);

export default CyfmsRouter;
