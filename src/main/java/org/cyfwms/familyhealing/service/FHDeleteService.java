package org.cyfwms.familyhealing.service;

public interface FHDeleteService {
    void removeReferral(Long fhReferralId);

    void removeApproval(Long fhApprovalId);

    void removeHistory(Long fhHistoryId);
}
