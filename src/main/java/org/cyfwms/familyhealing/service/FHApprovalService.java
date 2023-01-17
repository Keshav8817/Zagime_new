package org.cyfwms.familyhealing.service;

import org.cyfwms.familyhealing.dto.FHApprovalDto;

public interface FHApprovalService {
    FHApprovalDto readApproval(Long fhFileDetailsId);

    FHApprovalDto saveApproval(FHApprovalDto fhApprovalDto);
}
