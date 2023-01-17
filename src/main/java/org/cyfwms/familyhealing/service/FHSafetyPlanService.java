package org.cyfwms.familyhealing.service;

import org.cyfwms.familyhealing.dto.FHSafetyPlanDto;

import java.util.List;

public interface FHSafetyPlanService {
    FHSafetyPlanDto saveSafetyPlan(FHSafetyPlanDto fhSafetyPlanDto);

    FHSafetyPlanDto readSafetyPlan(Long fhSafetyPlanId);

    List<FHSafetyPlanDto> readAllSafetyPlan(Long fhFileDetailsId);

    void removeSafetyPlan(Long fhCasePlanId);
}
