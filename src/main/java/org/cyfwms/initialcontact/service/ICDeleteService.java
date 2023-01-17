package org.cyfwms.initialcontact.service;

public interface ICDeleteService {
    void removeReferralInfo(Long referralInfoId);

    void removeIncidentReport(Long incidentReportId);

    void removePresentConcerns(Long presentConcernsId);

    void removePatientCareInfo(Long patientCareInfoId);
}
