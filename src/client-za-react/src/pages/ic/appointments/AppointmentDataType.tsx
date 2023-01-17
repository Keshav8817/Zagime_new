export interface Data {
  icappointmentId: number;
  fileDetailsId: number;
  fileDetailsNo: number;
  appointmentDto: {
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
