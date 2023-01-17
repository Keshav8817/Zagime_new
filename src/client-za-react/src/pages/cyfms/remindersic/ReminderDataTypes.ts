export interface Data {
  icReminderId: number;
  fileDetailsId: number;
  fileNumber: number;
  reminderDto: {
    reminderId: number;
    assignedTo: string;
    regarding: any;
    subject: string;
    status: string;
    reminderDate: string;
    endDate: string;
    description: string;
    frequency: string;
  };
}
