export interface CriminalHistoryRecord {
  criminalHistoryId: number;
  criminalHistoryRecordId: number;
  arrestDate: string;
  charges: string;
  conviction: string;
  sentence: string;
}

export interface CriminalHistory {
  participantId: number;
  criminalHistoryId: number;
  criminalHistoryRecordList: CriminalHistoryRecord[];
  probation: boolean;
  parole: boolean;
  conditions: string;
  courtWorkerAndContactInfo: string;
}
