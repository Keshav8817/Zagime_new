package org.cyfwms.familyhealing.service;

import org.cyfwms.familyhealing.dto.FHReferralDto;

public interface FHReferralService {
    FHReferralDto readReferral(Long fhFileDetailsId);

    FHReferralDto saveReferral(FHReferralDto fhReferralDto);
}
