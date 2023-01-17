export interface AppointmentData {
  appointmentId: number;
  subject: string;
  status: string;
  date: string;
  participantId: number;
  icAppointmentId: number;
  cgProviderId: number;
}

export interface ReminderData {
  reminderId: number;
  subject: string;
  status: string;
  reminderDate: string;
  participantId: number;
  fileDetailsId: number;
  cgProviderId: number;
}
