export interface Data {
  participantId: number;

  participantReminderId: number;
  referenceId: number;
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
