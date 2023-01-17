export interface Data {
  fileDetailsId: number;
  patientCareInfoId: number;
  typeOfPatient: string;
  outpatient: {
    outpatientId: number;
    therapyOrCounseling: string;
    therapyTimePeriod: string;
    therapyLocation: string;
    reasonForTherapy: string;
    selfHelpGroup: string;
    selfHelpGroupPeriod: string;
    selfHelpGroupLocation: string;
  };
  inpatient: {
    inpatientId: number;
    hospitalizationRecord: string;
    hospitalizationReasons: string;
  };
}
