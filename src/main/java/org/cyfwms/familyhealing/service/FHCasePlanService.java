package org.cyfwms.familyhealing.service;

import org.cyfwms.familyhealing.dto.FHCasePlanDto;

import java.util.List;

public interface FHCasePlanService {
    FHCasePlanDto saveCasePlan(FHCasePlanDto fhCasePlanDto);

    FHCasePlanDto readCasePlan(Long fhCasePlanId);

    List<FHCasePlanDto> readAllCasePlan(Long fhFileDetailsId);

    void removeCasePlan(Long fhCasePlanId);
}
