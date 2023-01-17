export interface Data {
  participantAppointmentId: number;
  participantId: number;
  referenceId: number;
  appointmentdto: {
    appointmentId: number;
    subject: string;
    status: string;
    date: string;
    time: string;
    location: string;
    duration: string;
    client: any;
    caseworker: string;
    recurringAppointment: string;
    frequency: string;
    endDate: string;
    notes: string;
  };
}
