import { AttachmentsContextProvider } from "../../contexts/AttachmentsContext";
import Appointments from "../../pages/ic/appointments/Appointments";
import Add from "../../pages/ic/attachments/Add";
import Attachments from "../../pages/ic/attachments/Attachments";
import Edit from "../../pages/ic/attachments/Edit";
import View from "../../pages/ic/attachments/View";
import ContactNotes from "../../pages/ic/contactNotes/ContactNotes";
import FileDetails from "../../pages/ic/FileDetails/FileDetails";
import IcPage from "../../pages/ic/ic/IcPage";
import IncidentReport from "../../pages/ic/IncidentReport/IncidentReport";
import Participants from "../../pages/ic/participants/Participants";
import PatientCareInformation from "../../pages/ic/PatientCareInformation/PatientCareInformation";
import PresentConcerns from "../../pages/ic/presentConcern/PresentConcerns";
import ReferralInformation from "../../pages/ic/referralInformation/ReferralInformation";
import Reminders from "../../pages/ic/reminders/Reminders";
import React from "react";
import { Route, Routes } from "react-router-dom";
import type { FC } from "react";
import { ParticipantEdit } from "../../pages/ic/participants/Edit";
import { ParticipantAdd } from "../../pages/ic/participants/Add";
import { IcContactNotesEdit } from "../../pages/ic/contactNotes/Edit";
import { IcContactNotesAdd } from "../../pages/ic/contactNotes/Add";
import { IcAppointmentAdd } from "../../pages/ic/appointments/Add";
import { IcAppointmentEdit } from "../../pages/ic/appointments/Edit";
import { ICReminderAdd } from "../../pages/ic/reminders/Add";
import { ICReminderEdit } from "../../pages/ic/reminders/Edit";
// import Edit from "../../pages/ic/participants/Edit";
import { ModuleContextProvider } from "../../contexts/ModuleContext";
import SearchPage from "../../pages/ic/search/SearchPage";

/**
 * `IcRouter` is used in Popup of `IC` aka `Initial Contact` \
 * module and holds all of it's associated routes.
 */
const IcRouter: FC = () => (
  <ModuleContextProvider>
    <Routes>
      <Route path="/" element={<IcPage />} />
      <Route path="file_details/:id" element={<FileDetails />} />
      <Route
        path="referral_information/:id"
        element={<ReferralInformation />}
      />
      <Route path="incident_report/:id" element={<IncidentReport />} />
      <Route path="present_concerns/:id" element={<PresentConcerns />} />
      <Route
        path="patient_care_information/:id"
        element={<PatientCareInformation />}
      />
      <Route path="search/*" element={<SearchPage />} />
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
      <Route path="participants/:id" element={<Participants />} />
      <Route path="participants/add/:id" element={<ParticipantAdd />} />
      <Route
        path="participants/edit/:id/:childId"
        element={<ParticipantEdit />}
      />
    </Routes>
    <Routes>
      <Route path="contact_notes/:id" element={<ContactNotes />} />
      <Route path="contact_notes/add/:id" element={<IcContactNotesAdd />} />
      <Route
        path="contact_notes/edit/:id/:childId"
        element={<IcContactNotesEdit />}
      />
    </Routes>
    <Routes>
      <Route path="appointment/:id" element={<Appointments />} />
      <Route path="appointment/add/:id" element={<IcAppointmentAdd />} />
      <Route
        path="appointment/edit/:id/:childId"
        element={<IcAppointmentEdit />}
      />
    </Routes>
    <Routes>
      <Route path="reminder/:id" element={<Reminders />} />
      <Route path="reminder/add/:id" element={<ICReminderAdd />} />
      <Route path="reminder/edit/:id/:childId" element={<ICReminderEdit />} />
    </Routes>
  </ModuleContextProvider>
);

export default IcRouter;
