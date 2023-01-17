package org.cyfwms.staff.service;

public interface StaffDeleteService {
    void removeContactInformation(Long staffContactInformationId);

    void removeJobAndBanking(Long staffJobAndBankingId);

    void removeMedicalEmergency(Long staffMedicalAndEmergencyId);

    void removeInventories(Long staffId);
}
